package com.partho.locatemygod.map;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.partho.locatemygod.AppTabsActivity;
import com.partho.locatemygod.R;
import com.partho.locatemygod.models.Shrine;

public class LocateMyGodMapActivity extends MapActivity{

	ArrayList<Shrine> shrineList;	
	List<Overlay> mapOverlays;
	GeoPoint geoPoint;
	MyLocationOverlay myLocation;
	MapView appMap;
	@Override
	protected void onCreate(Bundle savedInstance) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstance);
		setContentView(R.layout.locatemygodmap);
		appMap = (MapView)findViewById(R.id.locateMyGod_mapView);
		mapOverlays= appMap.getOverlays();
		appMap.setBuiltInZoomControls(true);
		
		if(getIntent().getExtras().getBoolean("FROM_SHRINE_DETAIL"))
		{
			shrineList= new ArrayList<Shrine>();			
			shrineList.add((Shrine) getIntent().getParcelableExtra("shrine"));
		}
		else
		{
			shrineList= getIntent().getExtras().getParcelableArrayList("shrineList");
		}
				
		Drawable templeDrawable = getResources().getDrawable(R.drawable.temple_pin);		
		Drawable mosqueDrawable = getResources().getDrawable(R.drawable.mosque_pin);
		Drawable churchDrawable = getResources().getDrawable(R.drawable.church_pin);
		boolean fromShrineDetail = getIntent().getExtras().getBoolean("FROM_SHRINE_DETAIL");
		LocateMyGodCustomMapOverlay<LocateMyGodCustomOverlayItem> templeItemizedOverlay = new LocateMyGodCustomMapOverlay<LocateMyGodCustomOverlayItem>(templeDrawable, appMap, this, fromShrineDetail);
		LocateMyGodCustomMapOverlay<LocateMyGodCustomOverlayItem> churchItemizedOverlay = new LocateMyGodCustomMapOverlay<LocateMyGodCustomOverlayItem>(churchDrawable, appMap, this, fromShrineDetail);
		LocateMyGodCustomMapOverlay<LocateMyGodCustomOverlayItem> mosqueItemizedOverlay = new LocateMyGodCustomMapOverlay<LocateMyGodCustomOverlayItem>(mosqueDrawable, appMap, this, fromShrineDetail);
		for(Shrine shrine: shrineList)
		{					
			Location shrineLocation = new Location(AppTabsActivity.appProvider);
			
			if(shrine.getLatitude().trim().equalsIgnoreCase("empty"))
			{
				shrineLocation.setLatitude(0.0);
			}
			else
			{
				shrineLocation.setLatitude(Double.parseDouble(shrine.getLatitude()));
			}
			
			if(shrine.getLongitude().trim().equalsIgnoreCase("empty"))
			{
				shrineLocation.setLongitude(0.0);	
			}
			else
			{
				shrineLocation.setLongitude(Double.parseDouble(shrine.getLongitude()));	
			}
						
			int latitude= (int)(shrineLocation.getLatitude()*1e6);
			int longitude= (int)(shrineLocation.getLongitude()*1e6);
			geoPoint = new GeoPoint(latitude, longitude);
			String address = shrine.getStreet()+", "+ 
			                 shrine.getTownOrCityOrVillage()+", "+
			                 shrine.getState()+" ,"+
			                 shrine.getZipcode()+" ,"+
			                 shrine.getCountry();
			
			String formatDistance="%.2f Km";
			String distance = "\n"+String.format(formatDistance,  FloatMath.ceil(AppTabsActivity.currentLocation.distanceTo(shrineLocation)) /1000);			
			LocateMyGodCustomOverlayItem overlayItem = new LocateMyGodCustomOverlayItem(geoPoint, shrine.getName(), address+distance, shrine.getId());
			if(shrine.getIsTemple().contains("1"))
			{				
				templeItemizedOverlay.addOverlay(overlayItem); 
			}
			else if(shrine.getIsChurch().contains("1"))
			{
				churchItemizedOverlay.addOverlay(overlayItem);
			}
			else if(shrine.getIsMosque().contains("1"))
			{
				mosqueItemizedOverlay.addOverlay(overlayItem);
			}			
		}
		
		if(templeItemizedOverlay.size()>0)
		{
			mapOverlays.add(templeItemizedOverlay);
		}
		
		if(churchItemizedOverlay.size()>0)
		{
			mapOverlays.add(churchItemizedOverlay);
		}
		
		if(mosqueItemizedOverlay.size()>0)
		{
			mapOverlays.add(mosqueItemizedOverlay);
		}
		
		
		myLocation = new MyLocationOverlay(this, appMap);
		mapOverlays.add(myLocation);
		appMap.postInvalidate();
		final MapController controller= appMap.getController();
		controller.animateTo(geoPoint);
		controller.setZoom(5);
		
	}
	
	@Override
	protected boolean isRouteDisplayed() {		
		return false;
	}
	
	@Override
	protected void onResume() {
		myLocation.enableMyLocation();
		myLocation.enableCompass();
		super.onResume();
		
	}
	
	@Override
	protected void onPause() {
		myLocation.disableMyLocation();
		myLocation.disableCompass();
		super.onPause();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Street View");
		menu.add("Satellite View");
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getTitle().equals("Street View"))
		{			
			appMap.setSatellite(false);
		}
		else
		{
			appMap.setSatellite(true);			
		}
		return true;
	}
	

}
