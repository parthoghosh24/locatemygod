package com.partho.locatemygod.models;

import java.util.Comparator;

import android.location.Location;

import com.partho.locatemygod.AppTabsActivity;

public class LocateMyGodDistanceComparator implements Comparator<Shrine>
{

	@Override
	public int compare(Shrine shrine1, Shrine shrine2) {
		
	try{	
		Location shrine1Location = new Location(AppTabsActivity.appProvider);		
		
		if(shrine1.getLatitude().equalsIgnoreCase("empty"))
		{
			shrine1Location.setLatitude(0.0);
		}
		else
		{
			shrine1Location.setLatitude(Double.parseDouble(shrine1.getLatitude()));
		}
		
		if(shrine1.getLongitude().equalsIgnoreCase("empty"))
		{
			shrine1Location.setLongitude(0.0);
		}
		else
		{
			shrine1Location.setLongitude(Double.parseDouble(shrine1.getLongitude()));
		}
		
		
		
		Location shrine2Location = new Location(AppTabsActivity.appProvider);
		
		if(shrine2.getLatitude().equalsIgnoreCase("empty"))
		{
			shrine2Location.setLatitude(0.0);
		}
		else
		{
			shrine2Location.setLatitude(Double.parseDouble(shrine2.getLatitude()));
		}
		
		if(shrine2.getLongitude().equalsIgnoreCase("empty"))
		{
			shrine2Location.setLongitude(0.0);
		}
		else
		{
			shrine2Location.setLongitude(Double.parseDouble(shrine2.getLongitude()));
		}
				
				
		double distanceOfShrine1FromUser = AppTabsActivity.currentLocation.distanceTo(shrine1Location);
		double distanceOfShrine2FromUser = AppTabsActivity.currentLocation.distanceTo(shrine2Location);
		return (int)(distanceOfShrine1FromUser - distanceOfShrine2FromUser);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return 0;
	}
		
	}
	
}
