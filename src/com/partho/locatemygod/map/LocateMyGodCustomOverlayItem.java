package com.partho.locatemygod.map;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class LocateMyGodCustomOverlayItem extends OverlayItem {

	protected long shrineId;
	
	public LocateMyGodCustomOverlayItem(GeoPoint point, String title, String snippet, long id) {
		super(point, title, snippet);
		shrineId =id;
	}	
	
	public Long getShrineId()
	{
		return shrineId;
	}
	
	public void setShrineId(long shrineId)
	{
		this.shrineId=shrineId;
	}

}
