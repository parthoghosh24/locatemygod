package com.partho.locatemygod.addshrine;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.partho.locatemygod.R;
import com.partho.locatemygod.database.LocateMyGodDatabase;
import com.partho.locatemygod.models.Shrine;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddShrineFragment extends Fragment implements OnClickListener{
	
	private EditText shrineName;
	private EditText shrineStreet;
	private EditText shrineTown;
	private EditText shrineState;
	private EditText shrineZip;
	private EditText shrineCountry;
	private EditText shrineContact;
	private Spinner shrineType;
	private Button saveShrine;
	private Button cancelShrineSave;
	private LocateMyGodDatabase mDatabaseInstance;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View addShrineView = inflater.inflate(R.layout.addshrinefragment, container,false);
		shrineName=(EditText)addShrineView.findViewById(R.id.shrine_name_edittext);
		shrineStreet=(EditText)addShrineView.findViewById(R.id.shrine_street_edittext);
		shrineTown=(EditText)addShrineView.findViewById(R.id.shrine_town_edittext);
		shrineState=(EditText)addShrineView.findViewById(R.id.shrine_state_edittext);
		shrineZip=(EditText)addShrineView.findViewById(R.id.shrine_zip_edittext);
		shrineCountry=(EditText)addShrineView.findViewById(R.id.shrine_country_edittext);
		shrineContact=(EditText)addShrineView.findViewById(R.id.shrine_contact_edittext);
		shrineType=(Spinner)addShrineView.findViewById(R.id.shrine_type_spinner);
		saveShrine=(Button)addShrineView.findViewById(R.id.shrine_save_button);
		cancelShrineSave=(Button)addShrineView.findViewById(R.id.shrine_cancel_button);
		mDatabaseInstance = LocateMyGodDatabase.instantiate(getActivity());
		saveShrine.setOnClickListener(this);
		cancelShrineSave.setOnClickListener(this);
		
		return addShrineView;
	}

	@Override
	public void onResume() {		
		super.onResume();
		clearAllFields();
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.shrine_save_button:
			new SaveShrine().execute(); 
			break;
		case R.id.shrine_cancel_button:			
			clearAllFields();
			break;
		
		}	
	}
	
	private void clearAllFields()
	{
		shrineName.setText("");
		shrineStreet.setText("");
		shrineTown.setText("");
		shrineState.setText("");
		shrineZip.setText("");
		shrineCountry.setText("");
		shrineContact.setText("");
	}
	
	private boolean updateShrine()
	{
		boolean result =false;
		Shrine shrine = new Shrine();			
		shrine.setName(shrineName.getText().toString().trim());			
		shrine.setStreet(shrineStreet.getText().toString().trim());		
		shrine.setTownOrCityOrVillage(shrineTown.getText().toString().trim());			
		shrine.setState(shrineState.getText().toString().trim());			
		shrine.setZipcode(shrineZip.getText().toString().trim());		
		shrine.setCountry(shrineCountry.getText().toString().trim());		
		shrine.setContact(shrineContact.getText().toString().trim());
		shrine.setIsFavorite("1");
		
		String selectedItem= (String)shrineType.getSelectedItem();
				
		if(selectedItem.equals("Temple"))
		{
			shrine.setIsTemple("1");
			shrine.setIsChurch("0");
			shrine.setIsMosque("0");
		}
		else if(selectedItem.equals("Church"))
		{
			shrine.setIsTemple("0");
			shrine.setIsChurch("1");
			shrine.setIsMosque("0");
		}
		else if(selectedItem.equals("Mosque"))
		{
			shrine.setIsTemple("0");
			shrine.setIsChurch("0");
			shrine.setIsMosque("1");
		}
		double latitude=0;
		double longitude =0;
		Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
		try
		{
			List<Address> addresses= geocoder.getFromLocationName(shrine.getStreet()+" "+
					shrine.getTownOrCityOrVillage()+" "+
					shrine.getState()+" "+
					shrine.getZipcode()+" "+
					shrine.getCountry(), 1);						
		   if(addresses.size() > 0)
		   {
			latitude =addresses.get(0).getLatitude();
			longitude =addresses.get(0).getLongitude();
			shrine.setLatitude(Double.toString(latitude));
			shrine.setLongitude(Double.toString(longitude));
			result=true;
		   }
		   else
		   {
			   shrine.setLatitude("empty");
			   shrine.setLongitude("empty");
			   result =false;
		   }
		   
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
			shrine.setLatitude("empty");
			shrine.setLongitude("empty");
			result = false;
		}
			
		    System.out.println(shrine.getLatitude());
			mDatabaseInstance.addShrine(shrine);
			return result;		
		
	}
	
	class SaveShrine extends AsyncTask<Void, Void, Void>
	{
		ProgressDialog dialog = new ProgressDialog(getActivity());	
		boolean updateSuccessful = false;
		String transactionMessage="";
		
		@Override
		protected void onPreExecute() {						
			dialog.setMessage("Adding shrine...");
			dialog.show();
		}
		
		

		@Override
		protected Void doInBackground(Void... params) {		
			 
			if(shrineName.getText().toString().equals(""))
			{
				transactionMessage="Please enter a name for the shrine";
				updateSuccessful=false;
				
			} 
			else if(shrineCountry.getText().toString().equals(""))			    
			{
				transactionMessage="Please enter atleast country";
				updateSuccessful=false;
			}
			else if(!updateShrine())
			{
				transactionMessage="Sorry! Google Servers were unable to return location against provided Address.\nPlease update the newly created shrine with street level address or use lock location to update location from it's detail page if you are infront of shrine";
				updateSuccessful=false;
			}
			else
			{
				updateSuccessful=true;
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {			
			if(dialog.isShowing())
			{
				dialog.dismiss();
			}						
			
			if(updateSuccessful)
			{
				Toast.makeText(getActivity(), "New Shrine successfully created", Toast.LENGTH_SHORT).show();
				clearAllFields();
			}	
			else
			{
				AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();				
				alertDialog.setMessage(transactionMessage);
				alertDialog.show();				
			}
			
						
		}
		
	}
}
