package com.partho.locatemygod.maphelp;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.partho.locatemygod.R;

public abstract class LocateMyGodMapOverlay<Item extends OverlayItem> extends ItemizedOverlay<Item> {

	private MapView appMapView;
	private int viewOffset;
	private LocateMyGodBalloonView balloonView;
	private Item item;
	final MapController controller;
	private View clickRegion;
	
	public LocateMyGodMapOverlay(Drawable defaultMarker, MapView mapView) {
		super(defaultMarker);
		appMapView=mapView;
		viewOffset=0;
		controller=appMapView.getController();
	}
	
	public void setBalloonBottomOffset (int pixels)
	{
		viewOffset=pixels;
	}
	
	protected boolean onBalloonTap(int index, Item item)
	{
		return false;
	}
	
	protected void onBalloonOpen(int index)
	{}
	
	@Override
	protected final boolean onTap(int index) {
       boolean isRecycled;
       final int thisIndex;
       GeoPoint point;
       
       thisIndex =index;
       item = createItem(thisIndex);
       point= createItem(thisIndex).getPoint();
       
       if(balloonView == null)
       {
    	   balloonView = new LocateMyGodBalloonView(appMapView.getContext(), viewOffset);
    	   clickRegion = (View)balloonView.findViewById(R.id.map_balloon_content_layout);
    	   isRecycled= false;    			   
       }
       else
       {
    	   isRecycled =true;
       }
       balloonView.setVisibility(View.GONE);
       List<Overlay> mapOverlays = appMapView.getOverlays();
       if(mapOverlays.size() > 1)
       {
    	   hideOtherBalloons(mapOverlays);
       }
       balloonView.setData(createItem(thisIndex));
       
       MapView.LayoutParams params = new MapView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,point,MapView.LayoutParams.BOTTOM_CENTER);
       params.mode= MapView.LayoutParams.MODE_MAP;
       setBalloonTouchListener(thisIndex);
       balloonView.setVisibility(View.VISIBLE);
       if(isRecycled)
       {
    	   balloonView.setLayoutParams(params);    	   
       }
       else
       {
    	   appMapView.addView(balloonView, params);
       }
       controller.animateTo(point);
	   return true;
	}
	
	private void hideBalloon()
	{
		if(balloonView != null)
		{
			balloonView.setVisibility(View.GONE);
		}
		item=null;
	}
	
	private void hideOtherBalloons(List<Overlay> overLays)
	{
		for(Overlay overlay: overLays)
		{
			if(overlay instanceof LocateMyGodMapOverlay<?> && overlay!= this)
			{
				((LocateMyGodMapOverlay<?>) overlay).hideBalloon();
			}
		}
	}
	
	private void setBalloonTouchListener(final int thisIndex)
	{
		clickRegion.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				View parentView = ((View)v.getParent()).findViewById(R.id.map_balloon_main_layout);
				Drawable drawable = parentView.getBackground();
				
				if(event.getAction() == MotionEvent.ACTION_DOWN)
				{
					 int[] states = {android.R.attr.state_pressed};
					 if(drawable.setState(states))
					 {
						 drawable.invalidateSelf();
					 }
					 return true;
				}
				else if(event.getAction() == MotionEvent.ACTION_UP)
				{
					int newStates[]={};
					if(drawable.setState(newStates))
					{
						drawable.invalidateSelf();
					}
					onBalloonTap(thisIndex,item);
					return true;
				}
				else
				{
					return false;	
				}
				
			}
		});
	}

}
