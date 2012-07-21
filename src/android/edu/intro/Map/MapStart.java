package android.edu.intro.Map;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.maps.*;

import android.edu.intro.*;
import android.edu.intro.character.MapItem;
import android.edu.intro.character.NameItem;
import android.edu.intro.character.NpcItem;
import android.edu.intro.game.GameCollection;


public class MapStart extends MapActivity 
{
	private List<Overlay> mapOverlays;//躺在地圖上所有物件的集合物件(lay嘛~)
	
	private String account;
	private Drawable drawplayer;
	private OverlayItem player_lay;
	private MapItem player;//player的物件,藍色點點
	
	private LocationManager mlocationmanager;//gps manager
	private MapController mc;
	private MapView mapview;
	private int INITIAL_ZOOM_LEVEL=19;//設定起始的google map放大比例為level 19，數字越大地圖於越詳細，反之越為簡陋
	private int INITIAL_LATITUDE=24082358;
	private int INITIAL_LONGITUDE=120558472;
	
	private int npc_count=0;//npc個數的counter
	private String npcX[];//npc X座標
	private String npcY[];//npc Y座標
	private Drawable[] drawnpc;
	private GeoPoint[] npcpoint;//npc的座標點物件
	private OverlayItem[] npc_lay;
	private NameItem[] npcname;
	private Canvas[] canvas;
	private NpcItem[] npc;
	private String[] npctitle;
	private GameCollection game=new GameCollection();
	private boolean[] complete,aftercomplete;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        getData();
        mapViewInitial();//啟動google map+GPSmanager啟動
        getNpcCount();//由R.java內部去計算npc的個數
        mapItemIconInitial();//初始化npc,player以及npc,player的圖片
        addNpconMap();//將npc貼上地圖
    }
	
	private void getData() 
	{
		Bundle getdata=this.getIntent().getExtras();
		account=getdata.getString("act");
	}

	private void mapViewInitial()
	{
		mapview=(MapView)findViewById(R.id.mapview);
        mapview.setBuiltInZoomControls(true);
        mapview.setSatellite(false);
        mapview.setStreetView(true);
        mlocationmanager=(LocationManager)getSystemService(LOCATION_SERVICE);
		mlocationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,mlocationListener);
		mc=mapview.getController();
		mc.setZoom(INITIAL_ZOOM_LEVEL);
		
		GeoPoint point=new GeoPoint(INITIAL_LATITUDE,INITIAL_LONGITUDE);//GPS的點
		mc.animateTo(point);//將地圖移動到GPS的點
	}
	
	private void getNpcCount()
	{
		int npcstartnum=R.string.npc001;
		while(npcstartnum!=0)
		{
			npc_count++;
			npcstartnum+=3;
			if(npcstartnum>R.string.npc011)
				break;
		}
		npctitle=new String[npc_count];
		npcstartnum=R.string.npc001;
		int i=0;
		while(npcstartnum!=0)
		{
			npctitle[i]=getString(npcstartnum);
			npcstartnum+=3;
			i++;
			if(npcstartnum>R.string.npc011)
				break;
		}
	}
	
	private void mapItemIconInitial()
	{
		mapOverlays=mapview.getOverlays();
		drawnpc=new Drawable[npc_count];
		npc=new NpcItem[npc_count];
		drawplayer=this.getResources().getDrawable(R.drawable.location);
		
		player=new MapItem(drawplayer);
		game.getCompleteMission(account);
		complete=game.getMissionComplete();
		for(int i=0;i<npc_count;i++)
		{
			if(game.getGameComplete(i))
				drawnpc[i]=this.getResources().getDrawable(R.drawable.npc);
			else
				drawnpc[i]=this.getResources().getDrawable(R.drawable.npc_nonecomplete);
		}
	}
	
	private void addNpconMap()
	{
		npcX=new String[npc_count];
		npcY=new String[npc_count];
		npcpoint=new GeoPoint[npc_count];
		npc_lay=new OverlayItem[npc_count];
		npcname=new NameItem[npc_count];
		canvas=new Canvas[npc_count];
		
		int npcXInitial=R.string.x001;
		int npcYInitial=R.string.y001;
		
		for(int i=0;i<npc_count;i++)
		{
			npcX[i]=getString(npcXInitial+3*i);
			npcY[i]=getString(npcYInitial+3*i);
			npcpoint[i]=new GeoPoint(Integer.parseInt(npcX[i]),Integer.parseInt(npcY[i]));
			npc_lay[i]=new OverlayItem(npcpoint[i], npctitle[i], npctitle[i]);
			npc[i]=new NpcItem(drawnpc[i],this,game.getGame(i),i,account,npcpoint[i]);
			npc[i].addOverlay(npc_lay[i]);
			npcname[i]=new NameItem(npc_lay[i].getTitle().toString(),npcpoint[i]);
			mapOverlays.add(npc[i]);
			mapOverlays.add(npcname[i]);
		}
	}
	protected void onResume()//當重新啟動程式，再度要求GPS作更新
	{
		super.onResume();
		if(mlocationmanager!=null)
		{
			mlocationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,mlocationListener);
		}
		game.getCompleteMission(account);
		aftercomplete=game.getMissionComplete();
		boolean flag=false;
		for(int i=0;i<complete.length;i++)
		{
			if(complete[i]!=aftercomplete[i])
			{
				flag=true;
				mapOverlays.remove(i);
				npc[i]=new NpcItem(this.getResources().getDrawable(R.drawable.npc), this, game.getGame(i), i, account,npcpoint[i]);
				npc[i].addOverlay(npc_lay[i]);
				mapOverlays.add(i, npc[i]);
			}
		}
		if(flag)
		{
			complete=aftercomplete.clone();
			flag=false;
		}
	}
	
	protected void onPause()
	{
		super.onPause();
		if(mlocationmanager!=null)
		{
			mlocationmanager.removeUpdates(mlocationListener);
		}
	}
	
	protected boolean isRouteDisplayed()
	{
		return false;
	}
	
	private LocationListener mlocationListener=new LocationListener()
	{
		public void onLocationChanged(Location location)
		{
			if(player.contains(player_lay))//測試player的點有沒有存在地圖
				player.removeOverlay(player_lay);//有的話移除掉
			
			INITIAL_LATITUDE=(int)(location.getLatitude()*1000000);//X
			INITIAL_LONGITUDE=(int)(location.getLongitude()*1000000);//Y
			GeoPoint point=new GeoPoint(INITIAL_LATITUDE,INITIAL_LONGITUDE);//GPS的點
			mc.animateTo(point);//將地圖移動到GPS的點
			player_lay=new OverlayItem(point, "Player", "");//將layitem設定為player且在GPS的點
			player.addOverlay(player_lay);//然後這邊再重新加上player的點到List裡面
			mapOverlays.add(player);//將player丟到地圖上
		}
		public void onProviderDisabled(String provider) 
		{
			
		}
		public void onProviderEnabled(String provider) 
		{
			
		}
		public void onStatusChanged(String provider, int status, Bundle extras)
		{
			
		}
	};
	
	public int getLatitude()
	{
		return  INITIAL_LATITUDE;
	}
	
	public int getLongitude()
	{
		return INITIAL_LONGITUDE;
	}
}
