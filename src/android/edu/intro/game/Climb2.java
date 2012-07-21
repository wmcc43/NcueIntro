package android.edu.intro.game;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.edu.intro.R;
import android.edu.intro.network.client;
import android.os.Bundle; 
import android.os.Handler;
import android.view.View; 
import android.widget.Button; 
import android.widget.TextView;
import android.widget.Toast;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class Climb2  extends MapActivity
{
  private TextView mTextView,mTextView2; 
  private Button mButton01;
  private Long startTime;
  Handler handler = new Handler();
  private MapView mMapView;
  private MapController mMapController; 
  private LocationManager mLocationManager;
  private String strLocationPrivider = "";
  private Location mLocation;
  private int zoomLevel=17;
  private GeoPoint currentGeoPoint;
  private GeoPoint gp2= new GeoPoint((int) (24.07882081  * 1000000),(int)(120.55932632  * 1000000));
  private double distance=0;
  private Long spentTime,minius,seconds;
  private String account;
  private int npc_index;
  private client cln=new client();

  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
	    super.onCreate(savedInstanceState); 
	    setContentView(R.layout.climb); 
	    
	    Bundle bundle=getIntent().getExtras();
	    account=bundle.getString("act");
	    npc_index=bundle.getInt("npc_index");
	    
	    mMapView = (MapView)findViewById(R.id.myMapView1); 
	    mTextView = (TextView)findViewById(R.id.myText1);
	    mTextView2 = (TextView)findViewById(R.id.myText2);
	    mButton01 = (Button)findViewById(R.id.myButton1);
	    mMapController = mMapView.getController();
	    mMapController.setZoom(zoomLevel); 
	    mMapView.displayZoomControls(true);
	    mMapView.setBuiltInZoomControls(true);
	    mMapView.setSatellite(false);
	    mMapView.setStreetView(true);
	    mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
	   
	    mLocation= getLocationPrivider(mLocationManager);
	    if(mLocation!=null)
	    {
	      
	    	currentGeoPoint=getGeoByLocation(mLocation); 
	        refreshMapView(currentGeoPoint);
	           
	    }
	    else
	    {	
	    	handler.removeCallbacks(updateTimer);
	        new AlertDialog.Builder(Climb2.this).setTitle("系統訊息")
	       .setMessage(getResources().getString(R.string.str_message))
	       .setNegativeButton("確定",new DialogInterface.OnClickListener()
	        {
	          public void onClick(DialogInterface dialog, int which)
	          {
	        	
	          }
	        })
	        .show();
	    }
	    mLocationManager.requestLocationUpdates(strLocationPrivider,2000, 10, mLocationListener); 
	    
	    mButton01.setOnClickListener(new Button.OnClickListener() 
	    { 
	      public void onClick(View v) 
	      { 
	    	  startTime = System.currentTimeMillis();//取得目前時間
	  	      handler.removeCallbacks(updateTimer);//設定定時要執行的方法
	  	      handler.postDelayed(updateTimer, 1000);//設定Delay的時間
	        
	        resetOverlay();
	        setRoute(currentGeoPoint,gp2);
	        refreshMapView(currentGeoPoint);
	       
	        mTextView.setText("距離："+format(distance)+"M");
	        mButton01.setEnabled(false);
	      } 
	    });
	    
	  
  }
  private void resetOverlay() 
  {
	    List<Overlay> overlays = mMapView.getOverlays(); 
	    overlays.clear();
  } 
  
  private void setRoute(GeoPoint gp,GeoPoint gp2) 
  {  
	    ClimbOverlay mOverlay = new ClimbOverlay(gp,gp2); 
	    List<Overlay> overlays = mMapView.getOverlays(); 
	    overlays.add(mOverlay);
  }
  
  public void refreshMapView(GeoPoint gp) 
  { 
	    mMapController.animateTo(gp); 
	    
  } 
  
  public final LocationListener mLocationListener =  new LocationListener() 
  { 
	    public void onLocationChanged(Location location) 
	    { 
	    	currentGeoPoint=getGeoByLocation(location);
	    	refreshMapView(currentGeoPoint);
	        distance=GetDistance(currentGeoPoint,gp2);
	        mTextView.setText("距離："+format(distance)+"M"); 
	    	if(distance<=20)//到達終點
	    	{
	    		new AlertDialog.Builder(Climb2.this).setTitle("message")
	 	       .setMessage("Congratrulation!!!you arrive\nyou take "+minius+":"+seconds)
	 	       .setNegativeButton("OK",new DialogInterface.OnClickListener()
	 	        {
	 	          public void onClick(DialogInterface dialog, int which)
	 	          {
	 	        	cln.updateScoreByGID(account, npc_index+1, 1000);
	 	        	cln.updateFinGame(account, npc_index+1);
	 	        	Toast popup = Toast.makeText(Climb2.this, "現在照原路走回去然後往有食物的學生餐廳走吧~!", Toast.LENGTH_LONG);
	 	        	popup.show();
	 	        	finish();
	 	          }
	 	        })
	 	        .show();
	    		
	    		
	    	}
	    	
	       
	    } 
	     
	    public void onProviderDisabled(String provider) 
	    { 
	    } 
	    public void onProviderEnabled(String provider) 
	    { 
	    } 
	    public void onStatusChanged(String provider,int status,Bundle extras) 
	    { 
	    } 
  }; 

  
  private GeoPoint getGeoByLocation(Location location) 
  { 
	    GeoPoint gp = null; 
	    try 
	    { 
	      if (location != null) 
	      { 
	        double geoLatitude = location.getLatitude()*1E6; 
	        double geoLongitude = location.getLongitude()*1E6; 
	        gp = new GeoPoint((int) geoLatitude, (int) geoLongitude);
	      } 
	    } 
	    catch(Exception e) 
	    { 
	      e.printStackTrace(); 
	    }
	    return gp;
  } 
  
 
  public Location getLocationPrivider(LocationManager lm) 
  { 
	  Location retLocation = null;
      try
      {
        Criteria mCriteria01 = new Criteria();
        mCriteria01.setAccuracy(Criteria.ACCURACY_FINE);
        mCriteria01.setAltitudeRequired(false);
        mCriteria01.setBearingRequired(false);
        mCriteria01.setCostAllowed(true);
        mCriteria01.setPowerRequirement(Criteria.POWER_LOW);
        strLocationPrivider = lm.getBestProvider(mCriteria01, true);
        retLocation = lm.getLastKnownLocation(strLocationPrivider);
      }
      catch(Exception e)
      {
    	  mTextView.setText(e.toString());
        e.printStackTrace();
      }
      return retLocation;
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
  
  // format距離
  public String format(double num)
  {
	    NumberFormat formatter = new DecimalFormat("###");
	    String s=formatter.format(num);
	    return s;
  }
//計時
  private Runnable updateTimer = new Runnable() 
  {
	   public void run() 
	   {
		   
		    spentTime = System.currentTimeMillis() - startTime;
		    minius = (spentTime/1000)/60;//計算目前已過分鐘數
	        seconds = (spentTime/1000) % 60;//計算目前已過秒數
	       mTextView2.setText("目前經過時間: "+minius+":"+seconds);
	       handler.postDelayed(this, 1000);
	    }
  };
  @Override
  protected boolean isRouteDisplayed()
  {
	  	return false;
  }
} 
