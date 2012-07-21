package android.edu.intro.game;

import android.graphics.Canvas; 
import android.graphics.Color;
import android.graphics.Paint; 
import android.graphics.Point; 
import android.graphics.RectF; 
import com.google.android.maps.GeoPoint; 
import com.google.android.maps.MapView; 
import com.google.android.maps.Overlay; 
import com.google.android.maps.Projection; 

public class ClimbOverlay extends Overlay 
{ 
  private GeoPoint gp1;
  private GeoPoint gp2;
  private int mRadius=6;
  
     
  
  public ClimbOverlay(GeoPoint gp1,GeoPoint gp2) 
  { 
    this.gp1 = gp1; 
    this.gp2 = gp2;
    
  } 
 
  @Override 
  public boolean draw (Canvas canvas, MapView mapView, boolean shadow, long when) 
  { 
    Projection projection = mapView.getProjection(); 
    if (shadow == false) 
    {      
      
      Paint paint = new Paint(); 
      Paint paintText = new Paint();
      paintText.setColor(Color.RED);
	  paintText.setTextSize(20);
      paint.setAntiAlias(true); 
      paint.setColor(Color.BLACK);
      
      Point point = new Point(); 
      projection.toPixels(gp1, point);
     
      
       
        RectF oval=new RectF(point.x - mRadius, point.y - mRadius,  
                             point.x + mRadius, point.y + mRadius); 
        
        canvas.drawOval(oval, paint); //起點
        
     
      
        Point point2 = new Point(); 
        projection.toPixels(gp2, point2);
        
        RectF oval2=new RectF(point2.x - mRadius,point2.y - mRadius,  
                             point2.x + mRadius,point2.y + mRadius); 
        
       
        canvas.drawOval(oval2, paint);//終點
        canvas.drawText("終點  卦山月圓", point2.x, point2.y, paintText);
        paint.setStrokeWidth(5);
        paint.setAlpha(120);
       
        canvas.drawLine(point.x, point.y, point2.x,point2.y, paint);//路徑
        
       
      
    } 
    return super.draw(canvas, mapView, shadow, when); 
  }
 
} 
