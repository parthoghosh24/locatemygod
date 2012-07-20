package com.partho.locatemygod;


import com.partho.locatemygod.R;
import com.partho.locatemygod.aboutus.AboutUsFragment;
import com.partho.locatemygod.addshrine.AddShrineFragment;
import com.partho.locatemygod.database.LocateMyGodDatabaseHelper;
import com.partho.locatemygod.helper.TabPagerManager;
import com.partho.locatemygod.home.ShrineSearchFragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class AppTabsActivity extends FragmentActivity implements LocationListener{
    TabHost mTabHost;   
    TabPagerManager mTabPagerManager;
    public static ViewPager pager;
    private LocationManager appLocationManager;
    public static Location currentLocation;
    public static String appProvider;    
    
    ShrineSearchFragment targetFragment;
    Activity currentActivity;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apptabsactivitylayout);
        currentActivity=this;        
        appLocationManager=(LocationManager)getSystemService(LOCATION_SERVICE);      
        Criteria criteria = new Criteria();
        appProvider = appLocationManager.getBestProvider(criteria, false);        
        
        
        
        mTabHost= (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        pager=(ViewPager)findViewById(R.id.realtabcontent);       
        mTabPagerManager = new TabPagerManager(this, mTabHost, pager);
        //Home           
        Bundle homeTabBundle = new Bundle();
        homeTabBundle.putBoolean("isHome", true);       
        TabSpec tabSpecHome =mTabHost.newTabSpec("home").setIndicator("", getResources().getDrawable(R.drawable.ic_menu_home_2));
        mTabPagerManager.addTab(tabSpecHome, ShrineSearchFragment.class, homeTabBundle);
        
        //Add Shrine
        TabSpec tabSpecAddShrine =mTabHost.newTabSpec("addshrine").setIndicator("", getResources().getDrawable(R.drawable.ic_menu_add_2));
        mTabPagerManager.addTab(tabSpecAddShrine, AddShrineFragment.class, null);
        
        //Favorite Shrine
        Bundle favTabBundle = new Bundle();
        favTabBundle.putBoolean("isHome", false);
        TabSpec tabSpecFavShrine =mTabHost.newTabSpec("favshrine").setIndicator("", getResources().getDrawable(R.drawable.ic_menu_fav_2));
        mTabPagerManager.addTab(tabSpecFavShrine, ShrineSearchFragment.class, favTabBundle);
        
        //About Us        
        TabSpec tabSpecAboutUs =mTabHost.newTabSpec("aboutus").setIndicator("", getResources().getDrawable(R.drawable.ic_menu_info_details_2));
        mTabPagerManager.addTab(tabSpecAboutUs, AboutUsFragment.class, null);
                
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
        
        for(int i=0 ; i<mTabHost.getTabWidget().getTabCount();i++)
        {
        	mTabHost.getTabWidget().getChildAt(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.customtabindicator));
        }        
        currentLocation = appLocationManager.getLastKnownLocation(appProvider);        
        new PopulateDBTask().execute();                    
         
    }
    
    class PopulateDBTask extends AsyncTask<Void, Void, Void>
    {
    	ProgressDialog loadingDialog = new ProgressDialog(currentActivity);
    	@Override
    	protected void onPreExecute() {    		
    		loadingDialog.setMessage("Populating Database.Please Wait...");
    	}
    	
    	@Override
    	protected Void doInBackground(Void... arg0) {
    		
    		new LocateMyGodDatabaseHelper(currentActivity);
    		return null;
    	}
    	
    	@Override
    	protected void onPostExecute(Void result) {
    		
    		if(loadingDialog.isShowing())
    		{
    			loadingDialog.dismiss();
    		}    		
    	}
    }
        
    @Override
    public void onBackPressed() {
    	targetFragment= (ShrineSearchFragment) ShrineSearchFragment.thisFragment;
    	if(targetFragment.onFragmentBackPressed())
    	{
    		super.onBackPressed();
    	}	
    }
    @Override
    protected void onResume() {    
    	super.onResume();
    	    	
    	AlertDialog.Builder alertDialogBuilder;    	
		if(!appLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
	      {
	        	alertDialogBuilder = new AlertDialog.Builder(this);
				alertDialogBuilder.setTitle("Set User Location");
				alertDialogBuilder.setMessage("User Location is not set.\n Do you want to set User location ?").setCancelable(false).setPositiveButton("Take me to settings", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(Settings.ACTION_SETTINGS);
						startActivity(intent);		
						
						
					}
				}).setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
						
					}
				});
				AlertDialog locationDialog = alertDialogBuilder.create();
				locationDialog.show();
	        }
		else
		{
			appLocationManager.requestLocationUpdates(appProvider, 1*60*1000, 0,this); //request location after every 1 min
			currentLocation = appLocationManager.getLastKnownLocation(appProvider);					
		}
						
    }
    
    
    
    @Override
    protected void onPause() {    	
    	super.onPause();
    	appLocationManager.removeUpdates(this);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {    	
    	super.onSaveInstanceState(outState);
    	outState.putString("tab", mTabHost.getCurrentTabTag());
    }

	@Override
	public void onLocationChanged(Location location) {
		currentLocation = location;			
		
	}

	@Override
	public void onProviderDisabled(String provider) {		
		
	}

	@Override
	public void onProviderEnabled(String provider) {
	
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
			    
    
}