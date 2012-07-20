package com.partho.locatemygod.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.content.Context;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;

import com.partho.locatemygod.models.Shrine;

public class LocateMyGodDatabase implements DataAccessInterface {

	LocateMyGodDatabaseHelper mDatabaseInstance;
	private static final String TABLE_NAME="shrine";	
	private static Context currentContext;
	private static LocateMyGodDatabase databaseInstance;
	
	public static synchronized LocateMyGodDatabase instantiate(Context context)
	{
		if(databaseInstance == null)
		{
			databaseInstance = new LocateMyGodDatabase(context);
		}
		return databaseInstance;
	}
	
	//Singleton class
	private LocateMyGodDatabase(Context context) {
		currentContext= context;
		mDatabaseInstance = new LocateMyGodDatabaseHelper(currentContext);		
	}
	
	@Override
	
	//getting single shrine
	public Shrine findShrine(long id) {
			
		Shrine shrine = new Shrine();
		Cursor result = mDatabaseInstance.getWritableDatabase().rawQuery("SELECT docid as _id, name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude FROM "+TABLE_NAME+" WHERE _id = "+id, null);
		
		result.moveToFirst();
		shrine.setId(result.getLong(0));
		shrine.setName(result.getString(1));
		shrine.setStreet(result.getString(2));
		shrine.setTownOrCityOrVillage(result.getString(3));
		shrine.setZipcode(result.getString(4));
		shrine.setState(result.getString(5));
		shrine.setCountry(result.getString(6));
		shrine.setContact(result.getString(7));
		shrine.setIsFavorite(result.getString(8));
		shrine.setIsTemple(result.getString(9));
		shrine.setIsChurch(result.getString(10));
		shrine.setIsMosque(result.getString(11));
		shrine.setLatitude(result.getString(12));
		shrine.setLongitude(result.getString(13));
				
		result.close();
		mDatabaseInstance.close();
		return shrine;
	}

	//getting list of shrines
	@Override
	public ArrayList<Shrine> findAllShrinesByOffsetAndLimit(String pattern, int type, int offset, int limit) 
	{		
		
		String query="";
		ArrayList<Shrine> shrineList= new ArrayList<Shrine>();	
		if(pattern.equalsIgnoreCase(""))
		{						
			mDatabaseInstance.close();
			return shrineList;
		}
		else
		{			
			switch(type)
			{
				case 1:
					query="SELECT docid as _id, name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude FROM "+TABLE_NAME+ " WHERE "+TABLE_NAME+" MATCH '"+pattern+"' AND istemple LIKE '%1' LIMIT "+limit+" OFFSET "+offset;
					break;
				case 2:
					query="SELECT docid as _id, name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude FROM "+TABLE_NAME+ " WHERE "+TABLE_NAME+ " MATCH '"+pattern+"' AND ischurch LIKE '%1' LIMIT "+limit+" OFFSET "+offset;
					break;
				case 3:
					query="SELECT docid as _id, name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude FROM "+TABLE_NAME+ " WHERE "+TABLE_NAME+ " MATCH '"+pattern+"' AND ismosque LIKE '%1' LIMIT "+limit+" OFFSET "+offset;
					break;
				default:
					query ="SELECT docid as _id, name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude FROM "+TABLE_NAME+ " WHERE "+TABLE_NAME+ " MATCH '"+pattern+"' LIMIT "+limit+" OFFSET "+offset;
					break;
			}
		
		}
		Cursor result = mDatabaseInstance.getWritableDatabase().rawQuery(query, null);
		
		//cursor move to first and last... in loop, populate shrine objects and keep on adding them to list
		if(result.moveToFirst())
		{
			do
			{
				Shrine shrine = new Shrine();
				shrine.setId(result.getLong(0));
				shrine.setName(result.getString(1));
				shrine.setStreet(result.getString(2));
				shrine.setTownOrCityOrVillage(result.getString(3));
				shrine.setZipcode(result.getString(4));
				shrine.setState(result.getString(5));
				shrine.setCountry(result.getString(6));
				shrine.setContact(result.getString(7));
				shrine.setIsFavorite(result.getString(8));
				shrine.setIsTemple(result.getString(9));
				shrine.setIsChurch(result.getString(10));
				shrine.setIsMosque(result.getString(11));
				
				if(result.getString(12).equalsIgnoreCase("empty") || result.getString(13).equalsIgnoreCase("empty"))
				{					
					double latitude=0.0;
					double longitude=0.0;
					Geocoder geocoder = new Geocoder(currentContext, Locale.getDefault());
					try
					{
						List<Address> addresses= geocoder.getFromLocationName(shrine.getStreet()+" "+
								shrine.getTownOrCityOrVillage()+" "+
								shrine.getState()+" "+
								shrine.getZipcode()+" "+
								shrine.getCountry(), 5);
						
						
					   if(addresses.size() > 0)
					   {
						latitude =addresses.get(0).getLatitude();
						longitude =addresses.get(0).getLongitude();
						mDatabaseInstance.getWritableDatabase().execSQL("UPDATE "+TABLE_NAME+" SET latitude ='"+Double.toString(latitude)+"', longitude ='"+Double.toString(longitude)+"' WHERE docid="+shrine.getId());
						shrine.setLatitude(Double.toString(latitude));
						shrine.setLongitude(Double.toString(longitude));
						
					   }
					   else
					   {
						   shrine.setLatitude("empty");
						   shrine.setLongitude("empty");
					   }
					  
					}
					catch(IOException ie)
					{
						ie.printStackTrace();
						shrine.setLatitude(result.getString(12));
						shrine.setLongitude(result.getString(13));
					}
				}
				else
				{								
				shrine.setLatitude(result.getString(12));
				shrine.setLongitude(result.getString(13));				
				}				
					shrineList.add(shrine);				
			}
			while(result.moveToNext());
		}
				
		if(result != null && !result.isClosed())
		{			
			result.close();
		}
		
		mDatabaseInstance.close();
		return shrineList;
	}

	@Override
	public ArrayList<Shrine> findAllShrinesByTypeAndOffsetAndLimit(int type, int offset, int limit) {
		String query="";	
		
		ArrayList<Shrine> shrineList= new ArrayList<Shrine>();		
		switch(type)
		{
		case 1:
			query = "SELECT docid as _id, name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude FROM "+TABLE_NAME+ " WHERE istemple MATCH '1' LIMIT "+limit+" OFFSET "+offset;
			break;
			
		case 2:
			query = "SELECT docid as _id, name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude FROM "+TABLE_NAME+ " WHERE ischurch MATCH '1' LIMIT "+limit+" OFFSET "+offset;
			break;
			
		case 3:
			query = "SELECT docid as _id, name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude FROM "+TABLE_NAME+ " WHERE ismosque MATCH '1' LIMIT "+limit+" OFFSET "+offset;
			break;
		}		
		Cursor result = mDatabaseInstance.getWritableDatabase().rawQuery(query, null);
		
		//cursor move to first and last... in loop, populate shrine objects and keep on adding them to list
		if(result.moveToFirst())
		{
			do
			{
				Shrine shrine = new Shrine();
				shrine.setId(result.getLong(0));
				shrine.setName(result.getString(1));
				shrine.setStreet(result.getString(2));
				shrine.setTownOrCityOrVillage(result.getString(3));
				shrine.setZipcode(result.getString(4));
				shrine.setState(result.getString(5));
				shrine.setCountry(result.getString(6));
				shrine.setContact(result.getString(7));
				shrine.setIsFavorite(result.getString(8));
				shrine.setIsTemple(result.getString(9));
				shrine.setIsChurch(result.getString(10));
				shrine.setIsMosque(result.getString(11));
				if(result.getString(12).equalsIgnoreCase("empty") || result.getString(13).equalsIgnoreCase("empty"))
				{					
					System.out.println(shrine.getName());
					double latitude=0.0;
					double longitude=0.0;
					Geocoder geocoder = new Geocoder(currentContext, Locale.getDefault());
					try
					{
						List<Address> addresses= geocoder.getFromLocationName(shrine.getStreet()+" "+
								shrine.getTownOrCityOrVillage()+" "+
								shrine.getState()+" "+
								shrine.getZipcode()+" "+
								shrine.getCountry(), 5);
						
						System.out.println(addresses);
					   if(addresses.size() > 0)
					   {
						latitude =addresses.get(0).getLatitude();
						longitude =addresses.get(0).getLongitude();
						
						mDatabaseInstance.getWritableDatabase().execSQL("UPDATE "+TABLE_NAME+" SET latitude ='"+Double.toString(latitude)+"', longitude ='"+Double.toString(longitude)+"' WHERE docid="+shrine.getId());												
						shrine.setLatitude(Double.toString(latitude));
						shrine.setLongitude(Double.toString(longitude));
						
					   }
					   else
					   {
						   shrine.setLatitude("empty");
						   shrine.setLongitude("empty");
					   }
					   System.out.println(shrine.getLatitude()+ " "+shrine.getLongitude());
					  
					}
					catch(IOException ie)
					{
						ie.printStackTrace();
						shrine.setLatitude(result.getString(12));
						shrine.setLongitude(result.getString(13));
					}
				}
				else
				{								
				shrine.setLatitude(result.getString(12));
				shrine.setLongitude(result.getString(13));				
				}
								
					shrineList.add(shrine);				
				
			}
			while(result.moveToNext());
		}		
		if(result != null && !result.isClosed())
		{			
			result.close();
		}
				
		mDatabaseInstance.close();
		return shrineList;
	}

	@Override
	public ArrayList<Shrine> findAllShrinesByTypeAndFavorite(int type, int offset, int limit) {
		String query="";				
		ArrayList<Shrine> shrineList = new ArrayList<Shrine>();		
		switch(type)
		{
		
		case 1: query="SELECT docid , name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude FROM "+TABLE_NAME+ " WHERE istemple LIKE '%1' AND isfavorite LIKE '%1' LIMIT "+limit+" OFFSET "+offset;
				break;
		case 2: query="SELECT docid , name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude FROM "+TABLE_NAME+ " WHERE ischurch LIKE '%1' AND isfavorite LIKE '%1' LIMIT "+limit+" OFFSET "+offset;
				break;
		case 3: query="SELECT docid , name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude FROM "+TABLE_NAME+ " WHERE ismosque LIKE '%1' AND isfavorite LIKE '%1' LIMIT "+limit+" OFFSET "+offset;
				break;
		}
		
		Cursor result = mDatabaseInstance.getWritableDatabase().rawQuery(query, null);				
		if(result.moveToFirst())
		{			
			do
			{
				Shrine shrine = new Shrine();
				
				shrine.setId(result.getLong(0));
				shrine.setName(result.getString(1));
				shrine.setStreet(result.getString(2));
				shrine.setTownOrCityOrVillage(result.getString(3));
				shrine.setZipcode(result.getString(4));
				shrine.setState(result.getString(5));
				shrine.setCountry(result.getString(6));
				shrine.setContact(result.getString(7));
				shrine.setIsFavorite(result.getString(8));
				shrine.setIsTemple(result.getString(9));
				shrine.setIsChurch(result.getString(10));
				shrine.setIsMosque(result.getString(11));	
				shrine.setLatitude(result.getString(12));
				shrine.setLongitude(result.getString(13));
				shrineList.add(shrine);
			}
			while(result.moveToNext());
		}
				
		if(result != null && !result.isClosed())
		{			
			result.close();
		}	
		mDatabaseInstance.close();
		return shrineList;
	}

	@Override
	public void addShrine(Shrine shrine) {

		mDatabaseInstance.getWritableDatabase().execSQL("INSERT into "+TABLE_NAME+" (name, street, town, zip, state, country, contact, isfavorite, istemple, ischurch, ismosque, latitude, longitude)" +
				" values('"+shrine.getName()+"',' " +
						shrine.getStreet()+"',' " +
						shrine.getTownOrCityOrVillage()+"',' " +
						shrine.getZipcode()+"',' " +
						shrine.getState()+"',' " +
						shrine.getCountry()+"',' " +
						shrine.getContact()+"',' " +
						shrine.getIsFavorite()+"',' " +
						shrine.getIsTemple()+"',' " +
						shrine.getIsChurch()+"',' " +
						shrine.getIsMosque()+"',' " +
						shrine.getLatitude()+"',' " +
						shrine.getLongitude()+"');");				
		mDatabaseInstance.close();
		
	}

	@Override
	public void deleteShrine(long shrineId) {
		
		mDatabaseInstance.getWritableDatabase().execSQL("DELETE from "+TABLE_NAME+" where docid="+shrineId);		
		mDatabaseInstance.close();
		
	}

	@Override
	public void updateShrine(Shrine shrine) {		
		mDatabaseInstance.getWritableDatabase().execSQL("UPDATE "+TABLE_NAME+" set street ='"+																							
												shrine.getStreet()+"'"+
												", town='"+
												shrine.getTownOrCityOrVillage()+"'"+
												", zip='"+
												shrine.getZipcode()+"'"+
												", state='"+
												shrine.getState()+"'"+
												", street='"+
												shrine.getStreet()+"'"+
												", country='"+
												shrine.getCountry()+"'"+
												", contact='"+
												shrine.getContact()+"'"+
												", isfavorite='"+
												shrine.getIsFavorite()+"'"+												
												", latitude='"+
												shrine.getLatitude()+"'"+
												", longitude='"+
												shrine.getLongitude()+"'"+
												" WHERE docid="+shrine.getId());		
		mDatabaseInstance.close();
		
	}

	@Override
	public void updateShrineFavorite(String value, long shrineId) {		
		mDatabaseInstance.getWritableDatabase().execSQL("UPDATE "+TABLE_NAME+" set isfavorite ='"+value+"' where docid = "+shrineId);		
		mDatabaseInstance.close();
		
	}

	@Override
	public void updateShrineLocation(long shrineId, double latitude, double longitude) {		
		mDatabaseInstance.getWritableDatabase().execSQL("UPDATE "+TABLE_NAME+" set latitude ='"+Double.toString(latitude)+"', longitude ='"+Double.toString(longitude)+"' where docid = "+shrineId);		
		mDatabaseInstance.close();
		
	}

}
