package com.partho.locatemygod.home;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import com.partho.locatemygod.AppTabsActivity;
import com.partho.locatemygod.R;
import com.partho.locatemygod.database.LocateMyGodDatabase;
import com.partho.locatemygod.map.LocateMyGodMapActivity;
import com.partho.locatemygod.models.Shrine;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.FloatMath;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShrineDetailActivity extends Activity implements OnClickListener{
	

	private TextView shrineName;
	private TextView shrineDistanceFromUser;	
	private EditText shrineStreetEditText;
	private EditText shrineTownEditText;
	private EditText shrineStateEditText;
	private EditText shrineZipEditText;
	private EditText shrineCountryEditText;
	private EditText shrineContactEditText;	
	private Button callContact;
	private ImageView shrineTypeIcon;
	private LinearLayout shrineTypeRow;
	private Button getMap;
	private Button getDirection;
	private Shrine shrine;
	private ImageView shrineFavorite;
	private LinearLayout bottomBar;
	private Button save;
	private Button cancel;	
	private boolean isUpdating;
	private LocateMyGodDatabase mDatabaseInstance;
	Context mContext;
	
		
		@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shrinedetailactivity);
		shrineName= (TextView)findViewById(R.id.shrine_detail_name_textView);
		shrineDistanceFromUser= (TextView)findViewById(R.id.shrine_detail_distance);				
		mContext=this;		
		shrineStreetEditText =(EditText)findViewById(R.id.shrine_detail_street_edittext);
		shrineTownEditText =(EditText)findViewById(R.id.shrine_detail_town_edittext);
		shrineStateEditText =(EditText)findViewById(R.id.shrine_detail_state_edittext);
		shrineZipEditText =(EditText)findViewById(R.id.shrine_detail_zip_edittext);
		shrineCountryEditText =(EditText)findViewById(R.id.shrine_detail_country_edittext);
		shrineContactEditText =(EditText)findViewById(R.id.shrine_detail_contact_edittext);
		bottomBar =(LinearLayout)findViewById(R.id.shrine_detail_bottom_button_bar);
		callContact= (Button)findViewById(R.id.shrine_detail_contact_button);
		shrineTypeIcon = (ImageView)findViewById(R.id.shrine_detail_type_image);				
		getMap = (Button)findViewById(R.id.shrine_get_map);
		getDirection = (Button)findViewById(R.id.shrine_get_direction);
		shrineFavorite= (ImageView)findViewById(R.id.shrine_detail_favorite);			
		save=(Button)findViewById(R.id.shrine_detail_save_button);
		cancel=(Button)findViewById(R.id.shrine_detail_cancel_button);
		shrine = getIntent().getParcelableExtra("shrine");
		shrineTypeRow = (LinearLayout)findViewById(R.id.shrine_detail_type_row);

		mDatabaseInstance= LocateMyGodDatabase.instantiate(this); 
		shrine = mDatabaseInstance.findShrine(shrine.getId());
		if(shrine.getIsFavorite().trim().equals("0"))
		{
			shrineFavorite.setImageResource(R.drawable.btn_star_big_off);			
		}
		else
		{
			shrineFavorite.setImageResource(R.drawable.btn_star_big_on);
		}
		
		if(shrine.getName().equals(""))
		{
			shrineName.setText("Empty");
		}
		else
		{
			shrineName.setText(shrine.getName());
		}
		
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
				
		String distance = " %.2f Km";
		shrineDistanceFromUser.setText(String.format(distance, FloatMath.ceil(AppTabsActivity.currentLocation.distanceTo(shrineLocation))  / 1000));
		
		if(shrine.getStreet().equals(""))
		{
			shrineStreetEditText.setText("Empty");
		}
		else
		{
			shrineStreetEditText.setText(shrine.getStreet());
		}
		
		if(shrine.getTownOrCityOrVillage().equals(""))
		{
			shrineTownEditText.setText("Empty");
		}
		else
		{
			shrineTownEditText.setText(shrine.getTownOrCityOrVillage());
		}
		
		if(shrine.getState().equals(""))
		{
			shrineStateEditText.setText("Empty");
		}
		else
		{
			shrineStateEditText.setText(shrine.getState());
		}
		
		if(shrine.getZipcode().equals(""))
		{
			shrineZipEditText.setText("Empty");
		}
		else
		{
			shrineZipEditText.setText(shrine.getZipcode());
		}
		
		if(shrine.getCountry().equals(""))
		{
			shrineCountryEditText.setText("Empty");
		}
		else
		{
			shrineCountryEditText.setText(shrine.getCountry());
		}
		

		String phoneNumber =shrine.getContact();
		if(!PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber))
		{
			shrineContactEditText.setText("Empty");
			callContact.setEnabled(false);
		}
		else
		{
			shrineContactEditText.setText(shrine.getContact());
			callContact.setOnClickListener(this);
		}	
					
		getMap.setOnClickListener(this);
		getDirection.setOnClickListener(this);			
		shrineFavorite.setOnClickListener(this);
		save.setOnClickListener(this);
		cancel.setOnClickListener(this);
		
		
		if(shrine.getIsTemple().contains("1"))
		{
			shrineTypeIcon.setImageDrawable(getResources().getDrawable(R.drawable.templeicon));
		}
		else if(shrine.getIsChurch().contains("1"))
		{
			shrineTypeIcon.setImageDrawable(getResources().getDrawable(R.drawable.churchicon));
		}
		else if(shrine.getIsMosque().contains("1"))
		{
			shrineTypeIcon.setImageDrawable(getResources().getDrawable(R.drawable.mosqueicon));
		}
		
	}
	
		@Override
		protected void onResume() {		
			super.onResume();
			isUpdating=false;
		}
	@Override
	public void onClick(View v) {
		
		switch(v.getId())
		{
		
			case R.id.shrine_detail_contact_button:				
				String numberToCall = "tel:"+shrineContactEditText.getText().toString();
				
				try
				{
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse(numberToCall));
					startActivity(callIntent);
				}
				catch(ActivityNotFoundException ae)
				{
					ae.printStackTrace();
				}
				break;
				
			case R.id.shrine_get_map:
				 Intent mapIntent = new Intent(this,LocateMyGodMapActivity.class);	
				 mapIntent.putExtra("FROM_SHRINE_DETAIL", true);
				 mapIntent.putExtra("shrine", shrine);
				 startActivity(mapIntent);
				 break;
				 
			case R.id.shrine_get_direction:
				 Intent intent = new Intent(Intent.ACTION_VIEW,
						 Uri.parse("http://maps.google.com/maps?f=d&hl=en"+
							       "&saddr="+AppTabsActivity.currentLocation.getLatitude()+","+AppTabsActivity.currentLocation.getLongitude()+								 
							       "&daddr="+shrine.getLatitude()+","+shrine.getLongitude()+"&ie=UTF8&0&om=0&output=kml"));
				 if(isApplicationInstalled("com.google.android.apps.maps"))
				 {
					 intent.setClassName("com.google.android.apps.maps",
			                 "com.google.android.maps.MapsActivity");	 
				 }
				 			
				 startActivity(intent);
				 break;
			
			case R.id.shrine_detail_favorite:
								
				if(shrine.getIsFavorite().trim().equals("0"))
				{					
					shrineFavorite.setImageResource(R.drawable.btn_star_big_on);
					mDatabaseInstance.updateShrineFavorite("1", shrine.getId());
					shrine.setIsFavorite("1");
				}
				else
				{					
					shrineFavorite.setImageResource(R.drawable.btn_star_big_off);
					mDatabaseInstance.updateShrineFavorite("0", shrine.getId());
					shrine.setIsFavorite("0");
				}
			     break;
			     
			case R.id.shrine_detail_save_button:				
				new UpdateShrineTask().execute(true);		
				isUpdating=false;
				break;
				
			case R.id.shrine_detail_cancel_button:
				bottomBar.setVisibility(View.GONE);
				makeAllEditTextNonEditable();
				isUpdating=false;
				break;
				 
		}
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {				
		MenuItem edit= menu.add("Edit");
		edit.setIcon(android.R.drawable.ic_menu_edit);
		MenuItem delete = menu.add("Delete");
		delete.setIcon(android.R.drawable.ic_menu_delete);
		MenuItem lockLocation = menu.add("Lock Location");
		lockLocation.setIcon(R.drawable.ic_menu_mylocation);
		return true;
	}
		
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if(isUpdating)
		{
			menu.setGroupEnabled(0,false);
			return false;
		}
		else
		{
			menu.setGroupEnabled(0,true);
			return true;
		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getTitle().equals("Edit"))
		{
			Toast.makeText(this, "Editing", Toast.LENGTH_SHORT).show();
			bottomBar.setVisibility(View.VISIBLE);
			makeAllEditTextEditable();
			isUpdating=true;
			
		}
		else if(item.getTitle().equals("Delete"))
		{			
			AlertDialog.Builder yesNoDialogBuilder = new AlertDialog.Builder(this);
			yesNoDialogBuilder.setTitle("Delete Shrine");
			yesNoDialogBuilder.setMessage("Are you sure ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					mDatabaseInstance.deleteShrine(shrine.getId());
					finish();
					
				}
			}).setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					
				}
			});
			
			AlertDialog alertDialog = yesNoDialogBuilder.create();
			alertDialog.show();
		}
		else
		{
			AlertDialog.Builder yesNoDialogBuilder = new AlertDialog.Builder(this);
			yesNoDialogBuilder.setTitle("Lock Location");
			yesNoDialogBuilder.setMessage("Are you sure you want to update shrine location with your current location?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					new UpdateShrineTask().execute(false);														
				}
			}).setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					
				}
			});
			
			AlertDialog alertDialog = yesNoDialogBuilder.create();
			alertDialog.show();
			
		}
		
		return true;
	}
	
	private boolean isApplicationInstalled(String uri)
	{
		PackageManager manager= getPackageManager();
		boolean appInstalled = false;
		try
		{
			manager.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			appInstalled=true;
		}
		catch(PackageManager.NameNotFoundException ne)
		{
			ne.printStackTrace();
			appInstalled=false;
		}
		return appInstalled;
	}
	
	private void makeAllEditTextEditable()
	{		
		shrineStreetEditText.setClickable(true);
		shrineStreetEditText.setFocusable(true);
		shrineStreetEditText.setFocusableInTouchMode(true);
		shrineStreetEditText.setBackgroundDrawable(getResources().getDrawable(R.drawable.fieldbg));
		
		shrineTownEditText.setClickable(true);
		shrineTownEditText.setFocusable(true);
		shrineTownEditText.setFocusableInTouchMode(true);
		shrineTownEditText.setBackgroundDrawable(getResources().getDrawable(R.drawable.fieldbg));
				
		shrineStateEditText.setClickable(true);
		shrineStateEditText.setFocusable(true);
		shrineStateEditText.setFocusableInTouchMode(true);
		shrineStateEditText.setBackgroundDrawable(getResources().getDrawable(R.drawable.fieldbg));
				
		shrineZipEditText.setClickable(true);
		shrineZipEditText.setFocusable(true);
		shrineZipEditText.setFocusableInTouchMode(true);
		shrineZipEditText.setBackgroundDrawable(getResources().getDrawable(R.drawable.fieldbg));
				
		shrineCountryEditText.setClickable(true);
		shrineCountryEditText.setFocusable(true);
		shrineCountryEditText.setFocusableInTouchMode(true);
		shrineCountryEditText.setBackgroundDrawable(getResources().getDrawable(R.drawable.fieldbg));
										
		shrineContactEditText.setClickable(true);
		shrineContactEditText.setFocusable(true);
		shrineContactEditText.setFocusableInTouchMode(true);
		shrineContactEditText.setBackgroundDrawable(getResources().getDrawable(R.drawable.fieldbg));
		
		shrineTypeRow.setVisibility(View.GONE);
	}
	
	private void makeAllEditTextNonEditable()
	{
		shrineStreetEditText.setClickable(false);
		shrineStreetEditText.setFocusable(false);
		shrineStreetEditText.setFocusableInTouchMode(false);
		shrineStreetEditText.setBackgroundResource(android.R.color.transparent);

		shrineTownEditText.setClickable(false);
		shrineTownEditText.setFocusable(false);
		shrineTownEditText.setFocusableInTouchMode(false);
		shrineTownEditText.setBackgroundResource(android.R.color.transparent);
		
		shrineStateEditText.setClickable(false);
		shrineStateEditText.setFocusable(false);
		shrineStateEditText.setFocusableInTouchMode(false);
		shrineStateEditText.setBackgroundResource(android.R.color.transparent);
		
		shrineZipEditText.setClickable(false);
		shrineZipEditText.setFocusable(false);
		shrineZipEditText.setFocusableInTouchMode(false);
		shrineZipEditText.setBackgroundResource(android.R.color.transparent);
		
		shrineCountryEditText.setClickable(false);
		shrineCountryEditText.setFocusable(false);		
		shrineCountryEditText.setFocusableInTouchMode(false);
		shrineCountryEditText.setBackgroundResource(android.R.color.transparent);
		
		shrineContactEditText.setClickable(false);
		shrineContactEditText.setFocusable(false);		
		shrineContactEditText.setFocusableInTouchMode(false);
		shrineContactEditText.setBackgroundResource(android.R.color.transparent);
							
		String phoneNumber =shrine.getContact(); 		
		if(!PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber))
		{
			shrineContactEditText.setText("Empty");			
		}
		else
		{
			shrineContactEditText.setText(shrine.getContact());			
		}
		shrineTypeRow.setVisibility(View.VISIBLE);
	}
	
	class UpdateShrineTask extends AsyncTask<Boolean, Void, Void>
	{

		ProgressDialog dialog = new ProgressDialog(mContext);		
		@Override
		protected void onPreExecute() {			
			dialog.setTitle("Updating Shrine");
			dialog.setMessage("Updating shrine...");
			dialog.show();
		}
		
		@Override
		protected Void doInBackground(Boolean... params) {
			double latitude=0.0;
			double longitude=0.0;
			if(!params[0])
			{
									
				latitude= AppTabsActivity.currentLocation.getLatitude();
				longitude= AppTabsActivity.currentLocation.getLongitude();
				shrine.setLatitude(Double.toString(latitude));
				shrine.setLongitude(Double.toString(longitude));
				mDatabaseInstance.updateShrineLocation(shrine.getId(), latitude, longitude);
			}
			else
			{
				shrine.setStreet(shrineStreetEditText.getText().toString().trim());
				shrine.setTownOrCityOrVillage(shrineTownEditText.getText().toString().trim());
				shrine.setState(shrineStateEditText.getText().toString().trim());
				shrine.setZipcode(shrineZipEditText.getText().toString().trim());
				shrine.setCountry(shrineCountryEditText.getText().toString().trim());
				shrine.setContact(shrineContactEditText.getText().toString().trim());				
								
				if(shrine.getLatitude().contains("empty") || shrine.getLongitude().contains("empty"))
				{					
					Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
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
						shrine.setLatitude(Double.toString(latitude));
						longitude =addresses.get(0).getLongitude();
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
						shrine.setLatitude("empty");
						shrine.setLongitude("empty");
					}				
			
					
				}
						mDatabaseInstance.updateShrine(shrine);
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {			
			if(dialog.isShowing())
			{
				dialog.dismiss();
			}			
			makeAllEditTextNonEditable();
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
			
			
			
			String distance = " %.2f Km";
			shrineDistanceFromUser.setText(String.format(distance, FloatMath.ceil(AppTabsActivity.currentLocation.distanceTo(shrineLocation))  / 1000));
			bottomBar.setVisibility(View.GONE);
			AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
			alertDialog.setMessage("Location Updated.\nTo update and improve location accuracy, use Lock Location.");
			alertDialog.show();
			
		}
		
	}
	

}
