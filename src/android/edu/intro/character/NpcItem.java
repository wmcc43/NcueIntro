package android.edu.intro.character;

import com.google.android.maps.GeoPoint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.edu.intro.R;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class NpcItem extends MapItem
{
	private Context mContext;
	private Object game;
	private int npc_index;
	private String account;
	private GeoPoint playerpoint,npcpoint;
	private LocationManager mlocationmanager;//gps manager
	private int INITIAL_LATITUDE=24082358;
	private int INITIAL_LONGITUDE=120558472;
	public NpcItem(Drawable defaultMarker, Context context,Object g, int index,String act,GeoPoint point)
	{
		super(defaultMarker);
		mContext=context;
		game=g;
		npc_index = index;
		account=act;
		npcpoint=point;
		playerpoint=new GeoPoint(0,0);
		
		mlocationmanager=(LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
		mlocationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,mlocationListener);
	}
	@Override
    public boolean onTap(int pIndex)
    {
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(R.string.L_activity_title);
		dialog.setMessage(R.string.L_activity_Ques);
		dialog.setPositiveButton
		(
				"YES",
				new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialoginterface, int i)
					{
						Bundle bundle = new Bundle();
						bundle.putInt("npc_index", npc_index);
						bundle.putString("act",account);
						Intent start=new Intent();
          				start.setClass(mContext,game.getClass());
          				start.putExtras(bundle);
          				mContext.startActivity(start);
          			}
				}
		);
		dialog.setNegativeButton
		(
				"NO",
				new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialoginterface, int i)
					{
						
					}
				}
		);
		if(!playerpoint.equals(new GeoPoint(0,0)))
		{
			if(GetDistance(npcpoint, playerpoint)<=20)
				dialog.show();
			else
			{
				Toast notonpoint=Toast.makeText(mContext, "You're not on the point", Toast.LENGTH_LONG);
				notonpoint.show();
			}
		}
		else
		{
			Toast toast=new Toast(mContext);
			Toast.makeText(mContext, "GPS does not get position yet.", Toast.LENGTH_LONG);
			toast.show();
		}
		return true;
     }
	
	public double GetDistance(GeoPoint gp1,GeoPoint gp2)
	{
		    double Lat1r = ConvertDegreeToRadians(gp1.getLatitudeE6()/1E6);
		    double Lat2r = ConvertDegreeToRadians(gp2.getLatitudeE6()/1E6);
		    double Long1r= ConvertDegreeToRadians(gp1.getLongitudeE6()/1E6);
		    double Long2r= ConvertDegreeToRadians(gp2.getLongitudeE6()/1E6);
		  
		    double R = 6371;  //地球半徑(KM) 
		    double d = Math.acos(Math.sin(Lat1r)*Math.sin(Lat2r)+
		               Math.cos(Lat1r)*Math.cos(Lat2r)*
		               Math.cos(Long2r-Long1r))*R;
		    return d*1000;
	}
	
	private double ConvertDegreeToRadians(double degrees)
	{
		  	return (Math.PI/180)*degrees;
	}
	
	private LocationListener mlocationListener=new LocationListener()
	{
		public void onLocationChanged(Location location)
		{
			INITIAL_LATITUDE=(int)(location.getLatitude()*1000000);//X
			INITIAL_LONGITUDE=(int)(location.getLongitude()*1000000);//Y
			playerpoint=new GeoPoint(INITIAL_LATITUDE,INITIAL_LONGITUDE);
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
}
