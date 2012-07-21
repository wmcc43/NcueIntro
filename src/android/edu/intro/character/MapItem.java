package android.edu.intro.character;

import java.util.ArrayList;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MapItem extends ItemizedOverlay<OverlayItem>
{
	private ArrayList<OverlayItem> mapOverlay=new ArrayList<OverlayItem>();
	public MapItem(Drawable defaultMarker)
	{
		super(boundCenterBottom(defaultMarker));
	}
	
	public void addOverlay(OverlayItem overlay)
	{
		mapOverlay.add(overlay);
		populate();
	}
	
	public void removeOverlay(OverlayItem overlay)
	{
		mapOverlay.remove(overlay);
		populate();
	}
	
	public boolean contains(OverlayItem overlay)
	{
		return mapOverlay.contains(overlay);
	}
	
	protected OverlayItem createItem(int i)
	{
		return mapOverlay.get(i);
	}

	public int size()
	{
		return mapOverlay.size();
	}
}
