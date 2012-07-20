package com.partho.locatemygod.home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.FloatMath;
import android.util.Log;
import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.partho.locatemygod.AppTabsActivity;
import com.partho.locatemygod.R;
import com.partho.locatemygod.database.LocateMyGodDatabase;
import com.partho.locatemygod.helper.TabPagerManager;
import com.partho.locatemygod.map.LocateMyGodMapActivity;
import com.partho.locatemygod.models.LocateMyGodDistanceComparator;
import com.partho.locatemygod.models.Shrine;

public class ShrineSearchFragment extends Fragment implements OnClickListener {

	private ImageButton searchButton;
	private ImageButton templeButton;
	private ImageButton churchButton;
	private ImageButton mosqueButton;
	private ImageButton mapButton;	
	private EditText searchString;		
	RelativeLayout searchLayout;
	RelativeLayout buttonsLayout;
	RelativeLayout mFooterView;
	private ProgressBar mProgressBar;
	private int listScrollState;
	ViewPager appViewPager;		
	SearchResultAdapter mSearchResultAdapter;
	TabPagerManager adapter;
	ArrayList<Shrine> shrineList;
	ListView searchResultListView;
	private String userAddress="";
	int viewId;
	private String hintString;
	private LocateMyGodDatabase mDatabaseInstance;
	private static final int TYPE_DEFAULT = 0;
	private static final int TYPE_TEMPLE = 1;
	private static final int TYPE_CHURCH = 2;
	private static final int TYPE_MOSQUE = 3;
	int HOMESTATE;
	private static int loadMoreOffset;
	private boolean isLoading;
	private static final int DATALIMIT =10;
	public static Fragment thisFragment;	
	boolean isHome=true;
	boolean addressShown=false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {		
		View homeView = inflater.inflate(R.layout.shrinesearchfragment, container,false);
		searchLayout = (RelativeLayout)homeView.findViewById(R.id.home_search_layout);
		buttonsLayout =(RelativeLayout)homeView.findViewById(R.id.shrine_buttons_layout);
		searchResultListView = (ListView) homeView.findViewById(R.id.search_result_listview);
		mFooterView = (RelativeLayout)inflater.inflate(R.layout.loadmorefooter, searchResultListView, false);		
		mProgressBar=(ProgressBar)mFooterView.findViewById(R.id.shrine_list_progress_bar);
		mProgressBar.getIndeterminateDrawable().setColorFilter(android.R.color.black, android.graphics.PorterDuff.Mode.DARKEN);
		isLoading=false;		
		searchResultListView.addFooterView(mFooterView);
		
		isHome = getArguments().getBoolean("isHome");
		if(!isHome)
		{
			searchLayout.setVisibility(View.GONE);
		}
		else
		{
			searchLayout.setVisibility(View.VISIBLE);
		}
		setHasOptionsMenu(false);															
		searchButton = (ImageButton) homeView.findViewById(R.id.home_search_button);
		templeButton = (ImageButton) homeView.findViewById(R.id.temple_button);
		churchButton = (ImageButton) homeView.findViewById(R.id.church_button);
		mosqueButton = (ImageButton) homeView.findViewById(R.id.mosque_button);
		mapButton =(ImageButton) homeView.findViewById(R.id.home_search_map);
		searchString = (EditText) homeView.findViewById(R.id.home_search);
		hintString= "Search Shrines...";
		searchString.setHint(hintString);
		HOMESTATE=TYPE_DEFAULT;
		
		mDatabaseInstance = LocateMyGodDatabase.instantiate(getActivity());
		searchButton.setOnClickListener(this);
		templeButton.setOnClickListener(this);
		churchButton.setOnClickListener(this);
		mosqueButton.setOnClickListener(this);		
		mapButton.setOnClickListener(this);
		shrineList= new ArrayList<Shrine>();
		thisFragment=this;
		viewId=-1;
		mSearchResultAdapter = new SearchResultAdapter(getActivity());		
		searchResultListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View view, int pos, long id) {	
				if(isOnline() && shrineList.size()>0 && !isLoading)
				{
					Shrine shrine = shrineList.get(pos);
					Intent detailIntent = new Intent(getActivity(), ShrineDetailActivity.class);
					detailIntent.putExtra("shrine", shrine);
					startActivity(detailIntent);		
					
				}
						
			}
		});		
					
		searchResultListView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
				listScrollState= scrollState;				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
								
		         boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount;		        
		         if(!isLoading && isOnline() && loadMore && listScrollState != SCROLL_STATE_IDLE)
		         {		        	
		        	 isLoading=true;
		        	 loadMoreOffset += DATALIMIT;
			         new CreateListTask().execute(viewId);
			         mSearchResultAdapter.notifyDataSetChanged(); 
		         }
		         
			}
		});
		
		if(isOnline() && isHome && !addressShown)
		{
			new FetchUserLocationName().execute();
			addressShown=true;
		}
		
		return homeView;
	}
	
	class FetchUserLocationName extends AsyncTask<Void, Void, Void>
    {

    	ProgressDialog dialog = new ProgressDialog(getActivity());    	
    	@Override
    	protected void onPreExecute() {
    		userAddress="";
    		dialog.setMessage("Fetching Location Name...");
    		dialog.show();
    	}
    	
		@Override
		protected Void doInBackground(Void... params) {
			showUserLocation();
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			if(dialog.isShowing())
			{
				dialog.dismiss();
			}
			if(userAddress.length()>0)
			{
				Toast.makeText(getActivity(), userAddress, Toast.LENGTH_LONG).show();
			}
			
		}
    	
    }
    
    private void showUserLocation()
    {
				Geocoder geocoder = new Geocoder(getActivity());				
				try {
					List<Address> addresses= geocoder.getFromLocation(AppTabsActivity.currentLocation.getLatitude(), AppTabsActivity.currentLocation.getLongitude(), 1);
					userAddress="";
					if(addresses.size() >0)
					{
						for(int i = 0 ; i<addresses.get(0).getMaxAddressLineIndex(); ++i)
						{
							userAddress+=addresses.get(0).getAddressLine(i)+" ";
						}				
					}
					
				} catch (IOException e) {			
					e.printStackTrace();
					userAddress="Unable to retrieve address";
				}
    }
	
	public boolean onFragmentBackPressed ()
	{		
		if(HOMESTATE == TYPE_TEMPLE || HOMESTATE == TYPE_MOSQUE || HOMESTATE == TYPE_CHURCH)
		{
			HOMESTATE = TYPE_DEFAULT;
			hintString="Search Shrines...";
			searchString.setHint(hintString);
			buttonsLayout.setVisibility(View.VISIBLE);
			mapButton.setVisibility(View.GONE);
			shrineList.clear();
			searchString.setText("");
			mProgressBar.setVisibility(View.GONE);
			isLoading=false;
			loadMoreOffset=0;
			mSearchResultAdapter.notifyDataSetChanged();
			return false;
		}
		else
		{
			return true;
		}
	}
	
	@Override
	public void onResume() {	
				
		super.onResume();
		if(isOnline())
		{			
			if(mSearchResultAdapter!=null)
			{				
				mProgressBar.setVisibility(View.GONE);
				isLoading=false;					
				loadMoreOffset=0;				
				if(viewId!=-1 && shrineList.size()>0)
				{							
					new CreateListTask().execute(viewId); //Refresh list
				}
				else
				{
					searchString.setText("");
				}
			}
			
			switch(HOMESTATE)
			{
			
			case TYPE_TEMPLE:
				hintString="Search Temples...";
				buttonsLayout.setVisibility(View.GONE);
				break;
				
			case TYPE_CHURCH:
				hintString="Search Churches...";
				buttonsLayout.setVisibility(View.GONE);
				break;
				
			case TYPE_MOSQUE:
				hintString= "Search Mosques...";
				buttonsLayout.setVisibility(View.GONE);
				break;
				
			case TYPE_DEFAULT:
				hintString= "Search Shrines...";
				buttonsLayout.setVisibility(View.VISIBLE);
				break;
			}
			searchString.setHint(hintString);				
		}
	}

	@Override
	public void onClick(View v) {
										
			loadMoreOffset=0;
			if(v.getId()!= R.id.home_search_map)
			{
				new CreateListTask().execute(v.getId());
			}
			else
			{
				Intent mapIntent = new Intent(getActivity(),LocateMyGodMapActivity.class);	
				mapIntent.putParcelableArrayListExtra("shrineList", shrineList);
				startActivity(mapIntent);
			}								
			searchResultListView.setAdapter(mSearchResultAdapter);		
					
	}

	class CreateListTask extends AsyncTask<Integer, Void, Void> {		
		

		@Override
		protected void onPreExecute() {			
			mProgressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Integer... params) {
		
			if(isOnline())
			{				
				viewId= params[0];
				switch (viewId) {
				
				
				case R.id.home_search_button:							
					String getSearchString = searchString.getText().toString();					
					if(isLoading)
					{
						shrineList.addAll(mDatabaseInstance.findAllShrinesByOffsetAndLimit(getSearchString, HOMESTATE,loadMoreOffset, DATALIMIT));
					}
					else
					{
						
						shrineList = mDatabaseInstance.findAllShrinesByOffsetAndLimit(getSearchString, HOMESTATE,loadMoreOffset, DATALIMIT);
					}
					
					
					break;
				case R.id.temple_button:
										   
					   if(!isHome)
					   {
						   if(isLoading)
						   {
							   shrineList.addAll(mDatabaseInstance.findAllShrinesByTypeAndFavorite(TYPE_TEMPLE, loadMoreOffset, DATALIMIT));   
						   }
						   else
						   {
							   
							   shrineList = mDatabaseInstance.findAllShrinesByTypeAndFavorite(TYPE_TEMPLE, loadMoreOffset, DATALIMIT);  
						   }
						      
					   }
					   else
					   {					
						   HOMESTATE=TYPE_TEMPLE;
						   
						   if(isLoading)
						   {
							   shrineList.addAll(mDatabaseInstance.findAllShrinesByTypeAndOffsetAndLimit(TYPE_TEMPLE, loadMoreOffset, DATALIMIT));   
						   }
						   else
						   {
							   System.out.println("I am in do bg");
							   shrineList = mDatabaseInstance.findAllShrinesByTypeAndOffsetAndLimit(TYPE_TEMPLE, loadMoreOffset, DATALIMIT);   
						   }
						   					  
					   }	   
					break;
				case R.id.church_button:
					
					   if(!isHome)
					   {
						   if(isLoading)
						   {
							   shrineList.addAll(mDatabaseInstance.findAllShrinesByTypeAndFavorite(TYPE_CHURCH, loadMoreOffset, DATALIMIT));   
						   }
						   else
						   {
							   shrineList = mDatabaseInstance.findAllShrinesByTypeAndFavorite(TYPE_CHURCH, loadMoreOffset, DATALIMIT);   
						   }
						   
					   }
					   else
					   {
						   HOMESTATE=TYPE_CHURCH;
						   
						   if(isLoading)
						   {
							   shrineList.addAll(mDatabaseInstance.findAllShrinesByTypeAndOffsetAndLimit(TYPE_CHURCH, loadMoreOffset,DATALIMIT));  
						   }
						   else
						   {
							   shrineList = mDatabaseInstance.findAllShrinesByTypeAndOffsetAndLimit(TYPE_CHURCH, loadMoreOffset,DATALIMIT);   
						   }					   
						      
					   }
						
					
					break;
				case R.id.mosque_button:				
					
						if(!isHome)
						{
							if(isLoading)
							{
								shrineList.addAll(mDatabaseInstance.findAllShrinesByTypeAndFavorite(TYPE_MOSQUE, loadMoreOffset, DATALIMIT));
							}
							else
							{
								shrineList = mDatabaseInstance.findAllShrinesByTypeAndFavorite(TYPE_MOSQUE, loadMoreOffset, DATALIMIT);
							}
							
						}
						else
						{
							HOMESTATE=TYPE_MOSQUE;
							
							if(isLoading)
							{
								shrineList.addAll(mDatabaseInstance.findAllShrinesByTypeAndOffsetAndLimit(TYPE_MOSQUE,loadMoreOffset,DATALIMIT));
							}
							else
							{
								shrineList = mDatabaseInstance.findAllShrinesByTypeAndOffsetAndLimit(TYPE_MOSQUE, loadMoreOffset,DATALIMIT);
							}
														
						}
										
					break;
				}
			}
			return null;
		}

		
		@Override
		protected void onPostExecute(Void result) {
			
			Log.v("SHRINELIST SIZE AND LIST: ",shrineList.size()+": "+shrineList);
			isLoading=false;
		if(isOnline())
		{	
										
				if(shrineList.size() > 0)
				{
					Collections.sort(shrineList, new LocateMyGodDistanceComparator());					
									
																								
						mProgressBar.setVisibility(View.GONE);
						mapButton.setVisibility(View.VISIBLE);
						switch(HOMESTATE)
						{
						
						case TYPE_TEMPLE:
							hintString="Search Temples...";
							buttonsLayout.setVisibility(View.GONE);
							break;
							
						case TYPE_CHURCH:
							hintString="Search Churches...";
							buttonsLayout.setVisibility(View.GONE);
							break;
							
						case TYPE_MOSQUE:
							hintString= "Search Mosques...";
							buttonsLayout.setVisibility(View.GONE);
							break;
							
						case TYPE_DEFAULT:
							hintString= "Search Shrines...";
							buttonsLayout.setVisibility(View.VISIBLE);
							break;
						}
						searchString.setHint(hintString);						
						mSearchResultAdapter.notifyDataSetChanged();					
					
	
				}
				else
				{
					mProgressBar.setVisibility(View.GONE);
					AlertDialog alertDialog= new AlertDialog.Builder(getActivity()).create();
					alertDialog.setMessage("No results");
					alertDialog.show();
					shrineList.clear();
					mSearchResultAdapter.notifyDataSetChanged();
				}
				
						
		}		
	
	}			
			

}
	
	
	private final boolean isOnline()
	{
		try
		{
			ConnectivityManager manager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();		
			return (networkInfo != null && networkInfo.isConnected());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	class SearchResultAdapter extends BaseAdapter {

		private Context mContext;

		public SearchResultAdapter(Context context) {
			mContext = context;
		}

		@Override
		public int getCount() {						
			return shrineList.size();
		}

		@Override
		public Object getItem(int position) {			
			return position;
		}

		@Override
		public long getItemId(int position) {			
			return position;
		}

		class ViewHolder {
			TextView shrineName;
			TextView shrineAddress;
			TextView shrineDistance;			
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder;			
			Shrine shrine = shrineList.get(position);					
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
			
			
					
			
			
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.searchlistitem, parent, false);
				holder = new ViewHolder();
				holder.shrineName = (TextView) convertView.findViewById(R.id.search_item_name);				
				holder.shrineAddress = (TextView) convertView.findViewById(R.id.search_item_address);				
				holder.shrineDistance = (TextView) convertView.findViewById(R.id.search_item_distance);							
				
				convertView.setTag(holder);
			} else
			{
				holder = (ViewHolder) convertView.getTag();				
			}
			
			if(shrine.getName().equals(""))
			{
				holder.shrineName.setText("Empty");
			}
			else
			{
				holder.shrineName.setText(shrine.getName());
			}
			holder.shrineName.setText(shrine.getName());
			
			String street="";
			if(shrine.getStreet().equals(""))
			{
				street ="Empty";
			}
			else
			{
				street=shrine.getStreet();
			}
			
			String town="";
			if(shrine.getTownOrCityOrVillage().equals(""))
			{
				town ="Empty";
			}
			else
			{
				town=shrine.getTownOrCityOrVillage();
			}
			
			String state="";
			if(shrine.getState().equals(""))
			{
				state ="Empty";
			}
			else
			{
				state=shrine.getState();
			}
			
			String zip="";
			if(shrine.getZipcode().equals(""))
			{
				zip ="Empty";
			}
			else
			{
				zip=shrine.getZipcode();
			}
			
			String country="";
			if(shrine.getCountry().equals(""))
			{
				country ="Empty";
			}
			else
			{
				country=shrine.getCountry();
			}
			holder.shrineAddress.setText(street + ", "
					+ town + ", "
					+ state + ", Zip-" + zip
					+ ", " + country);
			
			String distance = "%.2f Km";
			holder.shrineDistance.setText(String.format(distance, FloatMath.ceil(AppTabsActivity.currentLocation.distanceTo(shrineLocation)) / 1000));			
			return convertView;
		}

	}

}
