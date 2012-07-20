package com.partho.locatemygod.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


//Simply used to store the session of Foursquare when app is running.

public class FoursquareAppSession {
	
	private SharedPreferences fsquarePreferences;
	private Editor editor;
	
	private static final String FOURSQUARE_PREFERENCES_NAME="Forsquare_preferences";
	private static final String FOURSQUARE_USERNAME="Forsquare_usrnm";
	private static final String FOURSQUARE_ACCESS_TOKEN="Forsquare_access_token";
	
	public FoursquareAppSession(Context context) {
		fsquarePreferences = context.getSharedPreferences(FOURSQUARE_PREFERENCES_NAME, Context.MODE_PRIVATE);
		editor = fsquarePreferences.edit();
	}
	
	public void storeAccessToken (String accessToken, String username)
	{
		editor.putString(FOURSQUARE_ACCESS_TOKEN, accessToken);
		editor.putString(FOURSQUARE_USERNAME, username);
		editor.commit();
	}
	
	public void resetAccessToken()
	{
		editor.putString(FOURSQUARE_ACCESS_TOKEN, null);
		editor.putString(FOURSQUARE_USERNAME, null);
		editor.commit();
	}
	
	public String getAccessToken()
	{
		return fsquarePreferences.getString(FOURSQUARE_ACCESS_TOKEN, null);
	}
	

}
