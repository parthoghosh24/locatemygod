package com.partho.locatemygod.models;

import android.os.Parcel;
import android.os.Parcelable;


public class Shrine implements Parcelable{

	private long id;
	private String name;
	private String street;
	private String townOrCityOrVillage;
	private String zipcode;
	private String state;
	private String country;
	private String contact;
	private String isFavorite;
	private String isTemple;
	private String isChurch;
	private String isMosque;
	private String latitude;
	private String longitude;
		
	public Shrine()
	{
		
	}
	
	public Shrine(Parcel in)
	{
		String[] shrineDataArray = new String[14];
		in.readStringArray(shrineDataArray);
		this.id =Long.parseLong(shrineDataArray[0]);
		this.name= shrineDataArray[1];
		this.street=shrineDataArray[2];
		this.townOrCityOrVillage=shrineDataArray[3];
		this.zipcode=shrineDataArray[4];
		this.state=shrineDataArray[5];
		this.country=shrineDataArray[6];
		this.contact=shrineDataArray[7];
		this.isFavorite=shrineDataArray[8];
		this.isTemple=shrineDataArray[9];
		this.isChurch=shrineDataArray[10];
		this.isMosque=shrineDataArray[11];
		this.latitude=shrineDataArray[12];
		this.longitude=shrineDataArray[13];
		
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getId ()
	{
		return id;
	}
	
	public void setName (String name)
	{
		this.name=name;
	}
	
	public String getName ()
	{
		return name;
	}
	
	public void setStreet (String street)
	{
		this.street = street;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public void setTownOrCityOrVillage (String townOrCityOrVillage)
	{
		this.townOrCityOrVillage =townOrCityOrVillage;
	}
	
	public String getTownOrCityOrVillage()
	{
		return townOrCityOrVillage;
	}
	
	public void setZipcode (String zipcode)
	{
		this.zipcode = zipcode;
	}
	
	public String getZipcode()
	{
		return zipcode;
	}
	
	public void setState (String state)
	{
		this.state = state;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setCountry (String country)
	{
		this.country = country;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setContact (String contact)
	{
		this.contact = contact;
	}
	
	public String getContact()
	{
		return contact;
	}
	
	public void setIsFavorite (String isFavorite)
	{
		this.isFavorite = isFavorite;
	}
	
	public String getIsFavorite ()
	{
		return isFavorite;
	}
	
	public void setIsTemple (String isTemple)
	{
		this.isTemple = isTemple;
	}
	
	public String getIsTemple ()
	{
		return isTemple;
	}
	
	public void setIsChurch (String isChurch)
	{
		this.isChurch = isChurch;
	}
	
	public String getIsChurch ()
	{
		return isChurch;
	}
	
	public void setIsMosque (String isMosque)
	{
		this.isMosque = isMosque;
	}
	
	public String getIsMosque ()
	{
		return isMosque;
	}
	
	public void setLatitude (String latitude)
	{
		this.latitude = latitude;
	}
	
	public String getLatitude ()
	{
		return latitude;
	}
	
	public void setLongitude (String longitude)
	{
		this.longitude = longitude;
	}
	
	public String getLongitude ()
	{
		return longitude;
	}
	
	public String toString ()
	{
		return name;
	}

	@Override
	public int describeContents() {	
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[]{Long.toString(this.id),
				this.name,
				this.street,
				this.townOrCityOrVillage,
				this.zipcode,
				this.state,
				this.country,
				this.contact,
				this.isFavorite,
				this.isTemple,
				this.isChurch,
				this.isMosque,
				this.latitude,
				this.longitude});
		
	}
	
	public static final Parcelable.Creator<Shrine> CREATOR= new Parcelable.Creator<Shrine>() {

		@Override
		public Shrine createFromParcel(Parcel source) {
			return new Shrine(source);
		}

		@Override
		public Shrine[] newArray(int size) {
			return new Shrine[size];
		}
	
	};
	
}


