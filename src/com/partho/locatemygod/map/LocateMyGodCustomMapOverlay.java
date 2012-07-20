package com.partho.locatemygod.map;

import java.util.ArrayList;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.partho.locatemygod.database.LocateMyGodDatabase;
import com.partho.locatemygod.home.ShrineDetailActivity;
import com.partho.locatemygod.maphelp.LocateMyGodMapOverlay;
import com.partho.locatemygod.models.Shrine;

public class LocateMyGodCustomMapOverlay<Item extends OverlayItem> extends LocateMyGodMapOverlay<LocateMyGodCustomOverlayItem>{

	private ArrayList<LocateMyGodCustomOverlayItem> overlayItems = new ArrayList<LocateMyGodCustomOverlayItem>();
	private LocateMyGodMapActivity mapActivity;
	private LocateMyGodDatabase mDatabaseInstance;
	private boolean fromShrineDetail=false;
	
	public LocateMyGodCustomMapOverlay(Drawable defaultMarker, MapView mapView, LocateMyGodMapActivity mapActivity, boolean fromShrineDetail) {
		super(boundCenterBottom(defaultMarker) , mapView);				
		this.mapActivity = mapActivity;
		this.fromShrineDetail= fromShrineDetail;
		mDatabaseInstance= LocateMyGodDatabase.instantiate(mapActivity);
	}

	public void addOverlay(LocateMyGodCustomOverlayItem overlay)
	{
		overlayItems.add(overlay);		
		populate();
	}
	
	@Override
	protected LocateMyGodCustomOverlayItem createItem(int i) {
		return overlayItems.get(i);
	}

	@Override
	public int size() {		
		return overlayItems.size();
	}
	
	@Override
	protected boolean onBalloonTap(int index, LocateMyGodCustomOverlayItem item) {	
		if(!fromShrineDetail)
		{
			Shrine shrine = mDatabaseInstance.findShrine(item.getShrineId());
			Intent detailIntent = new Intent(mapActivity,ShrineDetailActivity.class);
			detailIntent.putExtra("shrine", shrine);
			mapActivity.startActivity(detailIntent);
		}		
		mapActivity.finish(); 		
		return true;
	}

}
