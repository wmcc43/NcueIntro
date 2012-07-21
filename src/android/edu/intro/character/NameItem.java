package android.edu.intro.character;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class NameItem extends Overlay
{
	Paint paint=new Paint();
	String title;
	GeoPoint geopoint;
	public NameItem(String title, GeoPoint geopoint)
	{
		this.title=title;
		this.geopoint=geopoint;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapview, boolean shadow) 
	{
		paint.setColor(Color.BLACK);
		paint.setTextSize(12);
		Point point = new Point();
		Projection projection = mapview.getProjection();
	    projection.toPixels(geopoint, point);
		canvas.drawText(title, point.x, point.y, paint);
		canvas.save();
		super.draw(canvas, mapview, shadow);
	}
}
