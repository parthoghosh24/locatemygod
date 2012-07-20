package com.partho.locatemygod.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocateMyGodDatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "locatemygod";
	private static final String TABLE_FTS_NAME = "shrine";
	private static final int DATABASE_VERSION = 2;
	private static final String NAME_FIELD = "name";
	private static final String STREET_FIELD = "street";
	private static final String TOWN_FIELD = "town";
	private static final String ZIP_FIELD = "zip";
	private static final String STATE_FIELD = "state";
	private static final String COUNTRY_FIELD = "country";
	private static final String CONTACT_FIELD = "contact";
	private static final String ISFAVORITE_FIELD = "isfavorite";
	private static final String ISTEMPLE_FIELD = "istemple";
	private static final String ISCHURCH_FIELD = "ischurch";
	private static final String ISMOSQUE_FIELD = "ismosque";
	private static final String LATITUDE_FIELD = "latitude";
	private static final String LONGITUDE_FIELD = "longitude";

	// create table
	private static final String CREATE_FTS_TABLE = "CREATE VIRTUAL TABLE "
			+ TABLE_FTS_NAME + " using FTS3(" + NAME_FIELD + ", "
			+ STREET_FIELD + ", " + TOWN_FIELD + ", " + ZIP_FIELD + ", "
			+ STATE_FIELD + ", " + COUNTRY_FIELD + ", " + CONTACT_FIELD + ", "
			+ ISFAVORITE_FIELD + ", " + ISTEMPLE_FIELD + ", " + ISCHURCH_FIELD
			+ ", " + ISMOSQUE_FIELD + ", " + LATITUDE_FIELD + ", "
			+ LONGITUDE_FIELD + ");";

	SQLiteDatabase appDatabase;

	public LocateMyGodDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		appDatabase = db;
		appDatabase.execSQL(CREATE_FTS_TABLE);

		new Thread(new Runnable() {

			@Override
			public void run() {
				populateDatabase();

			}
		}).start();

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FTS_NAME);
		onCreate(db);
	}

	private void populateDatabase() {
		appDatabase.beginTransaction();

		ContentValues shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Shri Sai Samiti");
		shrineValues.put(STREET_FIELD, "64-D/2, F Block, Sector 40");
		shrineValues.put(TOWN_FIELD, "Noida");
		shrineValues.put(STATE_FIELD, "Uttar Pradesh");
		shrineValues.put(ZIP_FIELD, "201301");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "01204575672");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.564879");
		shrineValues.put(LONGITUDE_FIELD, "77.355874");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Sai Karuna Dham");
		shrineValues.put(STREET_FIELD, "E-4, Sector 61");
		shrineValues.put(TOWN_FIELD, "Noida");
		shrineValues.put(STATE_FIELD, "Uttar Pradesh");
		shrineValues.put(ZIP_FIELD, "201301");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "01204587768");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.594282");
		shrineValues.put(LONGITUDE_FIELD, "77.372082");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Shirdi Dham");
		shrineValues.put(STREET_FIELD,
				"Chatarpur, Gadaipur Mandi Road, Chhatarpur");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110030");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "0116801707");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.505093");
		shrineValues.put(LONGITUDE_FIELD, "77.174972");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Shiv Mandir");
		shrineValues.put(STREET_FIELD, "Kendriya Vihar, Sector 51");
		shrineValues.put(TOWN_FIELD, "Noida");
		shrineValues.put(STATE_FIELD, "Uttar Pradesh");
		shrineValues.put(ZIP_FIELD, "201307");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.576883");
		shrineValues.put(LONGITUDE_FIELD, "77.366664");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Cheraman Juma Masjid");
		shrineValues.put(STREET_FIELD,
				"On the Paravur-Kodungalloor Road, NH-17, Methala");
		shrineValues.put(TOWN_FIELD, "Kodungallur");
		shrineValues.put(STATE_FIELD, "Kerala");
		shrineValues.put(ZIP_FIELD, "680664");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "10.212892");
		shrineValues.put(LONGITUDE_FIELD, "76.202044");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Malik Dinar Mosque");
		shrineValues.put(STREET_FIELD, "Railway Station Road, Thalangara");
		shrineValues.put(TOWN_FIELD, "Kasargode");
		shrineValues.put(STATE_FIELD, "Kerala");
		shrineValues.put(ZIP_FIELD, "671121");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "12.484919");
		shrineValues.put(LONGITUDE_FIELD, "74.989092");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Kazimar Big Mosque");
		shrineValues.put(STREET_FIELD, "123, 1st Cross Street, Main Madurai");
		shrineValues.put(TOWN_FIELD, "Madurai");
		shrineValues.put(STATE_FIELD, "Tamil Nadu");
		shrineValues.put(ZIP_FIELD, "625001");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "9.912234");
		shrineValues.put(LONGITUDE_FIELD, "78.113928");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Asafi Masjid");
		shrineValues.put(STREET_FIELD, "Machchhi Bhavan");
		shrineValues.put(TOWN_FIELD, "Lucknow ");
		shrineValues.put(STATE_FIELD, "Uttar Pradesh");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "09415513408");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "26.869521");
		shrineValues.put(LONGITUDE_FIELD, "80.911987");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Taj-ul-Masjid");
		shrineValues.put(STREET_FIELD, "Koh-E-Fiza");
		shrineValues.put(TOWN_FIELD, "Bhopal");
		shrineValues.put(STATE_FIELD, "Madhya Pradesh");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "23.263045");
		shrineValues.put(LONGITUDE_FIELD, "77.392824");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Ziarat Shareef");
		shrineValues.put(STREET_FIELD, "Budaun");
		shrineValues.put(TOWN_FIELD, "Kakrala");
		shrineValues.put(STATE_FIELD, "Uttar Pradesh");
		shrineValues.put(ZIP_FIELD, "243637");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "27.89326");
		shrineValues.put(LONGITUDE_FIELD, "79.193868");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Charminar");
		shrineValues.put(STREET_FIELD, "Telephone Exchange Pathergatti Road");
		shrineValues.put(TOWN_FIELD, "Hyderabad");
		shrineValues.put(STATE_FIELD, "Andhra Pradesh");
		shrineValues.put(ZIP_FIELD, "500082");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "02224521290");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "17.361672");
		shrineValues.put(LONGITUDE_FIELD, "78.474642");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Hazratbal Shrine");
		shrineValues.put(STREET_FIELD, "Dal Lake, Nohata");
		shrineValues.put(TOWN_FIELD, "Srinagar");
		shrineValues.put(STATE_FIELD, "Jammu and Kashmir");
		shrineValues.put(ZIP_FIELD, "190006");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "00919906582726");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "34.130092");
		shrineValues.put(LONGITUDE_FIELD, "74.835402");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Jama Masjid");
		shrineValues.put(STREET_FIELD, "Meena Bazar, Chandni Chowk");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110006");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "01123320005");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "28.650367");
		shrineValues.put(LONGITUDE_FIELD, "77.233659");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Jama Masjid");
		shrineValues.put(STREET_FIELD, "Shahpur Market, Shahpur Road");
		shrineValues.put(TOWN_FIELD, "Ludhiana");
		shrineValues.put(STATE_FIELD, "Punjab");
		shrineValues.put(ZIP_FIELD, "141008");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "30.735012");
		shrineValues.put(LONGITUDE_FIELD, "75.720563");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Atala Masjid");
		shrineValues.put(STREET_FIELD, "Rizwikhan");
		shrineValues.put(TOWN_FIELD, "Jaunpur");
		shrineValues.put(STATE_FIELD, "Uttar Pradesh");
		shrineValues.put(ZIP_FIELD, "222001");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "25.752804");
		shrineValues.put(LONGITUDE_FIELD, "82.690219");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Mecca Masjid");
		shrineValues.put(STREET_FIELD, "Laad Bazaar Rd, Khilwat, Charminar");
		shrineValues.put(TOWN_FIELD, "Hyderabad");
		shrineValues.put(STATE_FIELD, "Andhra Pradesh");
		shrineValues.put(ZIP_FIELD, "500002");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+914024524023");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "17.360561");
		shrineValues.put(LONGITUDE_FIELD, "78.473199");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Moti Masjid");
		shrineValues.put(STREET_FIELD, "Red Fort");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110006");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "28.656755");
		shrineValues.put(LONGITUDE_FIELD, "77.243154");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Sidi Saiyyed Mosque");
		shrineValues.put(STREET_FIELD, "Mirzapur Road");
		shrineValues.put(TOWN_FIELD, "Ahmedabad");
		shrineValues.put(STATE_FIELD, "Gujrat");
		shrineValues.put(ZIP_FIELD, "380004");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "23.026993");
		shrineValues.put(LONGITUDE_FIELD, "72.580965");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Jama Masjid");
		shrineValues.put(STREET_FIELD,
				"Sheikh Menon Street, Chippi Chawl, Lohar Chawl");
		shrineValues.put(TOWN_FIELD, "Mumbai");
		shrineValues.put(STATE_FIELD, "Maharashtra");
		shrineValues.put(ZIP_FIELD, "400003");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "18.948985");
		shrineValues.put(LONGITUDE_FIELD, "72.832122");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Sir Syed Masjid");
		shrineValues.put(STREET_FIELD, "Aligarh");
		shrineValues.put(TOWN_FIELD, "Aligarh");
		shrineValues.put(STATE_FIELD, "Uttar Pradesh");
		shrineValues.put(ZIP_FIELD, "202002");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "27.909881");
		shrineValues.put(LONGITUDE_FIELD, "78.080995");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Tipu Sultan Mosque");
		shrineValues.put(STREET_FIELD, "Prince Anwar Shah Rd, Lake Gardens");
		shrineValues.put(TOWN_FIELD, "Kolkata");
		shrineValues.put(STATE_FIELD, "West Bengal");
		shrineValues.put(ZIP_FIELD, "700033");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "22.501909");
		shrineValues.put(LONGITUDE_FIELD, "88.345369");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Solah Khamba Masjid");
		shrineValues.put(STREET_FIELD, "Bidar");
		shrineValues.put(TOWN_FIELD, "Bidar");
		shrineValues.put(STATE_FIELD, "Karnataka");
		shrineValues.put(ZIP_FIELD, "585401");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "17.921027");
		shrineValues.put(LONGITUDE_FIELD, "77.528122");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Khwaja Bande Nawaz Dargah");
		shrineValues.put(STREET_FIELD, "Khaja Colony");
		shrineValues.put(TOWN_FIELD, "Gulbarga");
		shrineValues.put(STATE_FIELD, "Karnataka");
		shrineValues.put(ZIP_FIELD, "585104");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "08472220558");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "17.343849");
		shrineValues.put(LONGITUDE_FIELD, "76.85083");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Dakshineshwar Kali Temple");
		shrineValues.put(STREET_FIELD, "Dakshineshwar, NORTH 24 PARGANAS");
		shrineValues.put(TOWN_FIELD, "Kolkata");
		shrineValues.put(STATE_FIELD, "West Bengal");
		shrineValues.put(ZIP_FIELD, "700076");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "03325645222");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "22.654859");
		shrineValues.put(LONGITUDE_FIELD, "88.357466");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Kashi Vishwanath Temple");
		shrineValues.put(STREET_FIELD, "Vishwanath Gali");
		shrineValues.put(TOWN_FIELD, "Varanasi");
		shrineValues.put(STATE_FIELD, "Uttar Pradesh");
		shrineValues.put(ZIP_FIELD, "221001");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "05422392629");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "25.312965");
		shrineValues.put(LONGITUDE_FIELD, "83.01556");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Khajuraho Temple");
		shrineValues.put(STREET_FIELD,
				"Khajuraho in Bundelkhand in Chhatarpur District");
		shrineValues.put(TOWN_FIELD, "Khajuraho");
		shrineValues.put(STATE_FIELD, "Madhya Pradesh");
		shrineValues.put(ZIP_FIELD, "462003");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "05422392629");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "24.841814");
		shrineValues.put(LONGITUDE_FIELD, "79.924936");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Badrinath Temple");
		shrineValues.put(STREET_FIELD, "Chandrabhaga Bridge");
		shrineValues.put(TOWN_FIELD, "Badrinath");
		shrineValues.put(STATE_FIELD, "Uttarakhand");
		shrineValues.put(ZIP_FIELD, "249201");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "30.744418");
		shrineValues.put(LONGITUDE_FIELD, "79.491153");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Kedarnath Temple");
		shrineValues.put(STREET_FIELD, "Rudraprayag");
		shrineValues.put(TOWN_FIELD, "Kedarnath");
		shrineValues.put(STATE_FIELD, "Uttaranchal");
		shrineValues.put(ZIP_FIELD, "246445");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "01352627122");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "30.732351");
		shrineValues.put(LONGITUDE_FIELD, "79.06727");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Pushkar Brahma Temple");
		shrineValues.put(STREET_FIELD, "Main Market, Pushkar");
		shrineValues.put(TOWN_FIELD, "Pushkar");
		shrineValues.put(STATE_FIELD, "Rajasthan");
		shrineValues.put(ZIP_FIELD, "305022");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "01415155125");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "26.487343");
		shrineValues.put(LONGITUDE_FIELD, "74.5488");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Dwarkadheesh Mandir");
		shrineValues.put(STREET_FIELD, "Way to Dwarkadheesh temple");
		shrineValues.put(TOWN_FIELD, "Dwarka");
		shrineValues.put(STATE_FIELD, "Gujarat");
		shrineValues.put(ZIP_FIELD, "361335");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "22.237689");
		shrineValues.put(LONGITUDE_FIELD, "68.967276");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Ajanta Cave Temple");
		shrineValues.put(STREET_FIELD,
				"Near Jalgaon, outside the village of Ajanta");
		shrineValues.put(TOWN_FIELD, "Aurangabad");
		shrineValues.put(STATE_FIELD, "Maharashtra");
		shrineValues.put(ZIP_FIELD, "431001");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "20.529755");
		shrineValues.put(LONGITUDE_FIELD, "75.742589");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Laxminarayan Temple");
		shrineValues.put(STREET_FIELD, "Main Road Kalkaji, Kalkaji");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110019");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+911126422062");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.54212");
		shrineValues.put(LONGITUDE_FIELD, "77.254116");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Birla Temple");
		shrineValues.put(STREET_FIELD, "Tilak Nagar");
		shrineValues.put(TOWN_FIELD, "Jaipur");
		shrineValues.put(STATE_FIELD, "Rajasthan");
		shrineValues.put(ZIP_FIELD, "302015");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+911412620969");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "26.89217");
		shrineValues.put(LONGITUDE_FIELD, "75.815382");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Birla Temple");
		shrineValues.put(STREET_FIELD,
				"29, Ashutosh Chowdhury Road, Ballygunge");
		shrineValues.put(TOWN_FIELD, "Kolkata");
		shrineValues.put(STATE_FIELD, "West Bengal");
		shrineValues.put(ZIP_FIELD, "700019");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+913324615717");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "22.530557");
		shrineValues.put(LONGITUDE_FIELD, "88.364955");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Birla Temple");
		shrineValues.put(STREET_FIELD, "Adarsh Nagar Colony, Saifabad");
		shrineValues.put(TOWN_FIELD, "Hyderabad");
		shrineValues.put(STATE_FIELD, "Andhra Pradesh");
		shrineValues.put(ZIP_FIELD, "500004");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+914023233259");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "17.40627");
		shrineValues.put(LONGITUDE_FIELD, "78.469033");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Meenakshi Sundareshwarar Temple");
		shrineValues.put(STREET_FIELD, "North Chitrai Street");
		shrineValues.put(TOWN_FIELD, "Madurai");
		shrineValues.put(STATE_FIELD, "Tamil Nadu");
		shrineValues.put(ZIP_FIELD, "625001");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "04522344360");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "9.919484");
		shrineValues.put(LONGITUDE_FIELD, "78.120577");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Ellora Caves Temple");
		shrineValues.put(STREET_FIELD, "Ellora Cave Road");
		shrineValues.put(TOWN_FIELD, "Aurangabad");
		shrineValues.put(STATE_FIELD, "Maharashtra");
		shrineValues.put(ZIP_FIELD, "431001");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "20.032997");
		shrineValues.put(LONGITUDE_FIELD, "75.173631");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Konark Sun Temple");
		shrineValues.put(STREET_FIELD, "Konark");
		shrineValues.put(TOWN_FIELD, "Konark");
		shrineValues.put(STATE_FIELD, "Odisha");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "06742432177");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "19.88751");
		shrineValues.put(LONGITUDE_FIELD, "86.09446");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Rameswaram Temple");
		shrineValues.put(STREET_FIELD, "Ramanathapuram District");
		shrineValues.put(TOWN_FIELD, "Rameshwaram");
		shrineValues.put(STATE_FIELD, "Tamil Nadu");
		shrineValues.put(ZIP_FIELD, "682507");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "9.288");
		shrineValues.put(LONGITUDE_FIELD, "79.317335");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Sri Venkateshwara Temple");
		shrineValues.put(STREET_FIELD, "S Mada St, Tirumala");
		shrineValues.put(TOWN_FIELD, "Tirupati");
		shrineValues.put(STATE_FIELD, "Andhra Pradesh");
		shrineValues.put(ZIP_FIELD, "517504");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "13.683252");
		shrineValues.put(LONGITUDE_FIELD, "79.347175");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "The Chennakesava Temple");
		shrineValues.put(STREET_FIELD, "Belur ");
		shrineValues.put(TOWN_FIELD, "Hassan");
		shrineValues.put(STATE_FIELD, "Karnataka");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "13.161901");
		shrineValues.put(LONGITUDE_FIELD, "75.860244");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Kukke Shree Subrahmanya Temple");
		shrineValues.put(STREET_FIELD, "Subrahmanya,Sullia Taluk");
		shrineValues.put(TOWN_FIELD, "Dakshina Kannada District");
		shrineValues.put(STATE_FIELD, "Karnataka");
		shrineValues.put(ZIP_FIELD, "574238");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+918257281423");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "12.663714");
		shrineValues.put(LONGITUDE_FIELD, "75.615957");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Swaminarayan Akshardham Temple");
		shrineValues.put(STREET_FIELD, "National Highway 24, Near Noida Mor");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110092");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+911122026688");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.612558");
		shrineValues.put(LONGITUDE_FIELD, "77.27718");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "BAPS Swaminarayan Mandir");
		shrineValues.put(STREET_FIELD, "44-38 Bowne Street");
		shrineValues.put(TOWN_FIELD, "Flushing ");
		shrineValues.put(STATE_FIELD, "New York");
		shrineValues.put(ZIP_FIELD, "11355 ");
		shrineValues.put(COUNTRY_FIELD, "United States");
		shrineValues.put(CONTACT_FIELD, "+1718-539-5373");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "40.754485");
		shrineValues.put(LONGITUDE_FIELD, "-73.819759");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Shree Swaminarayan Mandir");
		shrineValues.put(STREET_FIELD, "40 Eleanor Street, Rosehill");
		shrineValues.put(TOWN_FIELD, "Sydney ");
		shrineValues.put(STATE_FIELD, "New South Wales");
		shrineValues.put(ZIP_FIELD, "2142");
		shrineValues.put(COUNTRY_FIELD, "Australia");
		shrineValues.put(CONTACT_FIELD, "+61298972776");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "-33.82622");
		shrineValues.put(LONGITUDE_FIELD, "151.017337");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "BAPS Shree Swaminarayan Mandir");
		shrineValues.put(STREET_FIELD, "105-119 Brentfield Road");
		shrineValues.put(TOWN_FIELD, "Greater London");
		shrineValues.put(STATE_FIELD, "London");
		shrineValues.put(ZIP_FIELD, "NW10 8LD");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+442089652651");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "51.547474");
		shrineValues.put(LONGITUDE_FIELD, "-0.261395");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Chattarpur Temple");
		shrineValues.put(STREET_FIELD, "Main Chhatarpur Road, Chhatarpur");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110074");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "01126802360");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.503676");
		shrineValues.put(LONGITUDE_FIELD, "77.178416");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Hanuman Temple");
		shrineValues.put(STREET_FIELD,
				"Baba Kharak Singh Marg, Connaught Place");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110092");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "01123362777");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.629931");
		shrineValues.put(LONGITUDE_FIELD, "77.214752");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "ISKCON Temple");
		shrineValues.put(STREET_FIELD, "1 Hare Krishna Hill, East of Kailash");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110065");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+911126235133");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.556184");
		shrineValues.put(LONGITUDE_FIELD, "77.253542");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Kali Bari Temple");
		shrineValues.put(STREET_FIELD, "Mandir Marg");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+911123743830");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.630376");
		shrineValues.put(LONGITUDE_FIELD, "77.197167");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Lotus Temple");
		shrineValues.put(STREET_FIELD, "East of Kailash, Nehru Place");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110019");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+911126470526");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.553319");
		shrineValues.put(LONGITUDE_FIELD, "77.258584");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Sri Shirdi Sai Temple(Lepers Colony)");
		shrineValues
				.put(STREET_FIELD,
						"Lepers Colony, Jeevan Deep Kust Ashram Opp. Mohan Singh Market, Sector –1, R.K. Puram");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110022");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "011-6186672");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.564337");
		shrineValues.put(LONGITUDE_FIELD, "77.181696");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Ayyappa Temple");
		shrineValues.put(STREET_FIELD, "Sector 2, R.K Puram");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.561664");
		shrineValues.put(LONGITUDE_FIELD, "77.184743");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "ISKCON Temple");
		shrineValues.put(STREET_FIELD, "41/77, Club Road, Punjabi Bagh");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110026");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "01125222851");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.660476");
		shrineValues.put(LONGITUDE_FIELD, "77.123417");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Shiv Mandir");
		shrineValues
				.put(STREET_FIELD, "Chittaranjan Park, Greater Kailash II ");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110019");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "01126276661");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.536668");
		shrineValues.put(LONGITUDE_FIELD, "77.24741");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Kali Bari");
		shrineValues
				.put(STREET_FIELD, "Chittaranjan Park, Greater Kailash II ");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110019");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.536873");
		shrineValues.put(LONGITUDE_FIELD, "77.247491");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Mata Vaishno Devi");
		shrineValues.put(STREET_FIELD, "");
		shrineValues.put(TOWN_FIELD, "Katra");
		shrineValues.put(STATE_FIELD, "Jammu and Kashmir");
		shrineValues.put(ZIP_FIELD, "182301");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+911991232367");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "33.030814");
		shrineValues.put(LONGITUDE_FIELD, "74.948972");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Sai Dham Temple");
		shrineValues.put(STREET_FIELD,"Plot No-712, Western Express Highway, Kandivali East");
		shrineValues.put(TOWN_FIELD, "Mumbai");
		shrineValues.put(STATE_FIELD, "Maharashtra");
		shrineValues.put(ZIP_FIELD, "400101");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+912228547724");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "19.207317");
		shrineValues.put(LONGITUDE_FIELD, "72.866829");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Golden Temple");
		shrineValues.put(STREET_FIELD, "Rajan Khadwal");
		shrineValues.put(TOWN_FIELD, "Amritsar");
		shrineValues.put(STATE_FIELD, "Punjab");
		shrineValues.put(ZIP_FIELD, "143006");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "01832553951");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "31.619895");
		shrineValues.put(LONGITUDE_FIELD, "74.876904");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Sai Baba Temple");
		shrineValues.put(STREET_FIELD, "Lodi Estate, Lodi Colony");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.589385");
		shrineValues.put(LONGITUDE_FIELD, "77.228898");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Gurdwara");
		shrineValues.put(STREET_FIELD, "sector 18");
		shrineValues.put(TOWN_FIELD, "Noida");
		shrineValues.put(STATE_FIELD, "Uttar Pradesh");
		shrineValues.put(ZIP_FIELD, "201301");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.567723");
		shrineValues.put(LONGITUDE_FIELD, "77.323625");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Gurdwara Bangla Sahib");
		shrineValues.put(STREET_FIELD, "Ashok Road, Connaught Place");
		shrineValues.put(TOWN_FIELD, "New Delhi");
		shrineValues.put(STATE_FIELD, "Delhi");
		shrineValues.put(ZIP_FIELD, "110001");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "28.626404");
		shrineValues.put(LONGITUDE_FIELD, "77.208897");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Shree Siddhivinayak Temple");
		shrineValues.put(STREET_FIELD," Swatantrya Veer Savarkar Marg, Prabhadevi");
		shrineValues.put(TOWN_FIELD, "Mumbai");
		shrineValues.put(STATE_FIELD, "Maharashtra");
		shrineValues.put(ZIP_FIELD, "400028");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+912224373626");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "19.016948");
		shrineValues.put(LONGITUDE_FIELD, "72.830274");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Great Stupa of Sanchi");
		shrineValues.put(STREET_FIELD, "Sanchi village");
		shrineValues.put(TOWN_FIELD, "Sanchi");
		shrineValues.put(STATE_FIELD, "Madhya Pradesh");
		shrineValues.put(ZIP_FIELD, "464661");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "23.479688");
		shrineValues.put(LONGITUDE_FIELD, "77.739893");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Sai Baba Temple");
		shrineValues.put(STREET_FIELD, "");
		shrineValues.put(TOWN_FIELD, "Shirdi");
		shrineValues.put(STATE_FIELD, "Maharashtra");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "19.766176");
		shrineValues.put(LONGITUDE_FIELD, "74.4769");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Church of St Francis of Assisi");
		shrineValues.put(STREET_FIELD, "");
		shrineValues.put(TOWN_FIELD, "North Goa");
		shrineValues.put(STATE_FIELD, "Goa");
		shrineValues.put(ZIP_FIELD, "403511");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+918322438749");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "15.503311");
		shrineValues.put(LONGITUDE_FIELD, "73.911437");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Se Cathedral Church");
		shrineValues.put(STREET_FIELD,
				"Off National Highway 4, Old Goa Church Complex");
		shrineValues.put(TOWN_FIELD, "");
		shrineValues.put(STATE_FIELD, "Goa");
		shrineValues.put(ZIP_FIELD, "403402");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "15.503789");
		shrineValues.put(LONGITUDE_FIELD, "73.912067");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St. Anne's Church");
		shrineValues.put(STREET_FIELD, "");
		shrineValues.put(TOWN_FIELD, "North Goa");
		shrineValues.put(STATE_FIELD, "Goa");
		shrineValues.put(ZIP_FIELD, "403510");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "15.578832");
		shrineValues.put(LONGITUDE_FIELD, "73.7797");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Basilica of Bom Jesus");
		shrineValues.put(STREET_FIELD, "Old Goa Road");
		shrineValues.put(TOWN_FIELD, "North Goa");
		shrineValues.put(STATE_FIELD, "Goa");
		shrineValues.put(ZIP_FIELD, "403511");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+918322285790");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "15.500796");
		shrineValues.put(LONGITUDE_FIELD, "73.911526");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Reis Magos Church");
		shrineValues.put(STREET_FIELD, "Main Village Road, Church Road");
		shrineValues.put(TOWN_FIELD, "Reis Magos");
		shrineValues.put(STATE_FIELD, "Goa");
		shrineValues.put(ZIP_FIELD, "403114");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "15.497033");
		shrineValues.put(LONGITUDE_FIELD, "73.809363");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St Cajetan Church");
		shrineValues.put(STREET_FIELD, "Divar Ferry Road");
		shrineValues.put(TOWN_FIELD, "");
		shrineValues.put(STATE_FIELD, "Goa");
		shrineValues.put(ZIP_FIELD, "403402");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "15.505839");
		shrineValues.put(LONGITUDE_FIELD, "73.91527");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St Francis Xavier Church");
		shrineValues.put(STREET_FIELD, "Near National Highway 566");
		shrineValues.put(TOWN_FIELD, "");
		shrineValues.put(STATE_FIELD, "Goa");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "15.351745");
		shrineValues.put(LONGITUDE_FIELD, "74.004276");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Church of our Lady");
		shrineValues.put(STREET_FIELD, "Emidio Gracia Road, Altinho");
		shrineValues.put(TOWN_FIELD, "Panaji");
		shrineValues.put(STATE_FIELD, "Goa");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "15.49855");
		shrineValues.put(LONGITUDE_FIELD, "73.82941");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Holy Spirit Church");
		shrineValues.put(STREET_FIELD, "St Joquim Road");
		shrineValues.put(TOWN_FIELD, "Margao");
		shrineValues.put(STATE_FIELD, "Goa");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+918322714005");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "15.281732");
		shrineValues.put(LONGITUDE_FIELD, "73.959634");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Our Lady of the Rosary Church");
		shrineValues.put(STREET_FIELD, "");
		shrineValues.put(TOWN_FIELD, "North Goa");
		shrineValues.put(STATE_FIELD, "Goa");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "India");
		shrineValues.put(CONTACT_FIELD, "+918322247257");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "15.664076");
		shrineValues.put(LONGITUDE_FIELD, "73.718138");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD,
				"Annunciation of the Blessed Virgin Mary Parish Church");
		shrineValues.put(STREET_FIELD, "3 Combermere Drive");
		shrineValues.put(TOWN_FIELD, "Toronto");
		shrineValues.put(STATE_FIELD, "Province of Ontario");
		shrineValues.put(ZIP_FIELD, "M3A 2W4");
		shrineValues.put(COUNTRY_FIELD, "Canada");
		shrineValues.put(CONTACT_FIELD, "+1 416-445-1760");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "43.755915");
		shrineValues.put(LONGITUDE_FIELD, "-79.314924");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Swayambhunath Stupa");
		shrineValues.put(STREET_FIELD, "Stairways");
		shrineValues.put(TOWN_FIELD, "Kathmandu");
		shrineValues.put(STATE_FIELD, "Kathmandu");
		shrineValues.put(ZIP_FIELD, "44600");
		shrineValues.put(COUNTRY_FIELD, "Nepal");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "27.71518");
		shrineValues.put(LONGITUDE_FIELD, "85.29017");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Pashupatinath Temple");
		shrineValues.put(STREET_FIELD, "Pashupatinath");
		shrineValues.put(TOWN_FIELD, "Kathmandu");
		shrineValues.put(STATE_FIELD, "Kathmandu");
		shrineValues.put(ZIP_FIELD, "44600");
		shrineValues.put(COUNTRY_FIELD, "Nepal");
		shrineValues.put(CONTACT_FIELD, "+977 1-4256909");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "27.710238");
		shrineValues.put(LONGITUDE_FIELD, "85.348661");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Changu Narayan Temple");
		shrineValues.put(STREET_FIELD, "Mandir Walkway");
		shrineValues.put(TOWN_FIELD, "Changunarayan");
		shrineValues.put(STATE_FIELD, "Changunarayan");
		shrineValues.put(ZIP_FIELD, "44804");
		shrineValues.put(COUNTRY_FIELD, "Nepal");
		shrineValues.put(CONTACT_FIELD, "+977 1-4256909");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "27.716362");
		shrineValues.put(LONGITUDE_FIELD, "85.42781");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Kantipur Temple House");
		shrineValues.put(STREET_FIELD, "");
		shrineValues.put(TOWN_FIELD, "Jyatha");
		shrineValues.put(STATE_FIELD, "Kathmandu");
		shrineValues.put(ZIP_FIELD, "44600");
		shrineValues.put(COUNTRY_FIELD, "Nepal");
		shrineValues.put(CONTACT_FIELD, "+977 1-4250131");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "27.710998");
		shrineValues.put(LONGITUDE_FIELD, "85.313124");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Boudhanath");
		shrineValues.put(STREET_FIELD, "");
		shrineValues.put(TOWN_FIELD, "Boudhanath (Boudha)");
		shrineValues.put(STATE_FIELD, "Kathmandu");
		shrineValues.put(ZIP_FIELD, "44600");
		shrineValues.put(COUNTRY_FIELD, "Nepal");
		shrineValues.put(CONTACT_FIELD, "+977 1-4256909 ");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "27.721496");
		shrineValues.put(LONGITUDE_FIELD, "85.361742");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Narayan Temple");
		shrineValues.put(STREET_FIELD, "Narayan Hiti Palace");
		shrineValues.put(TOWN_FIELD, "");
		shrineValues.put(STATE_FIELD, "Kathmandu");
		shrineValues.put(ZIP_FIELD, "44600");
		shrineValues.put(COUNTRY_FIELD, "Nepal");
		shrineValues.put(CONTACT_FIELD, "+977 1-4250131");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "27.713905");
		shrineValues.put(LONGITUDE_FIELD, "85.31921");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Narayan Temple");
		shrineValues.put(STREET_FIELD, "Narayan Hiti Palace");
		shrineValues.put(TOWN_FIELD, "");
		shrineValues.put(STATE_FIELD, "Kathmandu");
		shrineValues.put(ZIP_FIELD, "44600");
		shrineValues.put(COUNTRY_FIELD, "Nepal");
		shrineValues.put(CONTACT_FIELD, "+977 1-4250131");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "27.713905");
		shrineValues.put(LONGITUDE_FIELD, "85.31921");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Manjushree Temple");
		shrineValues.put(STREET_FIELD, "");
		shrineValues.put(TOWN_FIELD, "Swayambhu");
		shrineValues.put(STATE_FIELD, "Kathmandu");
		shrineValues.put(ZIP_FIELD, "44600");
		shrineValues.put(COUNTRY_FIELD, "Nepal");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "27.715904");
		shrineValues.put(LONGITUDE_FIELD, "85.287775");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Taleju Temple");
		shrineValues.put(STREET_FIELD, "Hanuman Dhoka Road");
		shrineValues.put(TOWN_FIELD, "");
		shrineValues.put(STATE_FIELD, "Kathmandu");
		shrineValues.put(ZIP_FIELD, "44600");
		shrineValues.put(COUNTRY_FIELD, "Nepal");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "1");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "27.704898");
		shrineValues.put(LONGITUDE_FIELD, "85.308111");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Valley Fellowship Christian Academy");
		shrineValues.put(STREET_FIELD, "3616 Holmes Ave NW");
		shrineValues.put(TOWN_FIELD, "Huntsville");
		shrineValues.put(STATE_FIELD, "Alabama");
		shrineValues.put(ZIP_FIELD, "35816-4112");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1256-274-6669");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "34.727654");
		shrineValues.put(LONGITUDE_FIELD, "-86.621115");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Lakeside Baptist Church");
		shrineValues.put(STREET_FIELD, "2865 Old Rocky Ridge Rd");
		shrineValues.put(TOWN_FIELD, "Birmingham");
		shrineValues.put(STATE_FIELD, "Alabama");
		shrineValues.put(ZIP_FIELD, "35243-2915");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1205-469-8031");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "33.408349");
		shrineValues.put(LONGITUDE_FIELD, "-86.764094");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Heavenly Places Church Of God");
		shrineValues.put(STREET_FIELD, "817 21st Ave W");
		shrineValues.put(TOWN_FIELD, "Birmingham");
		shrineValues.put(STATE_FIELD, "Alabama");
		shrineValues.put(ZIP_FIELD, "35204-1438");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1205-251-2707");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "33.529543");
		shrineValues.put(LONGITUDE_FIELD, "-86.849422");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Word Of Life Worship Center");
		shrineValues.put(STREET_FIELD, "1001 N 3 Notch St");
		shrineValues.put(TOWN_FIELD, "Troy");
		shrineValues.put(STATE_FIELD, "Alabama");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1334-566-6960");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "31.816724");
		shrineValues.put(LONGITUDE_FIELD, "-85.968868");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "River Of Life Worship Center");
		shrineValues.put(STREET_FIELD, "1410 Hillabee St");
		shrineValues.put(TOWN_FIELD, "Alexander City");
		shrineValues.put(STATE_FIELD, "Alabama");
		shrineValues.put(ZIP_FIELD, "35010-2345");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1256-329-9593");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "32.943712");
		shrineValues.put(LONGITUDE_FIELD, "-85.936507");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Grasselli Church Of Christ");
		shrineValues.put(STREET_FIELD, "3708 Grasselli Ave SW");
		shrineValues.put(TOWN_FIELD, "Birmingham");
		shrineValues.put(STATE_FIELD, "Alabama");
		shrineValues.put(ZIP_FIELD, "35221-2012");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1205-923-1241");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "33.446171");
		shrineValues.put(LONGITUDE_FIELD, "-86.891789");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Cornerstone Church Of God");
		shrineValues.put(STREET_FIELD, "4167 Center Point Rd");
		shrineValues.put(TOWN_FIELD, "Pinson");
		shrineValues.put(STATE_FIELD, "Alabama");
		shrineValues.put(ZIP_FIELD, "35126-3245");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1205-681-3031");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "33.680467");
		shrineValues.put(LONGITUDE_FIELD, "-86.681214");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "The Church Brew Works");
		shrineValues.put(STREET_FIELD, "3525 Liberty Avenue");
		shrineValues.put(TOWN_FIELD, "Pittsburgh");
		shrineValues.put(STATE_FIELD, "Pennsylvania");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1412-688-8200");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "40.461989");
		shrineValues.put(LONGITUDE_FIELD, "-79.964131");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Lakewood Church");
		shrineValues.put(STREET_FIELD, "3700 Southwest Freeway");
		shrineValues.put(TOWN_FIELD, "Houston");
		shrineValues.put(STATE_FIELD, "Texas");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1713-635-4154");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "29.730222");
		shrineValues.put(LONGITUDE_FIELD, "-95.435794");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Christ Church in Philadelphia");
		shrineValues.put(STREET_FIELD, "20 North American Street");
		shrineValues.put(TOWN_FIELD, "Philadelphia");
		shrineValues.put(STATE_FIELD, "Pennsylvania");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1215-922-1695");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "39.952028");
		shrineValues.put(LONGITUDE_FIELD, "-75.148161");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "The Riverside Church New York City");
		shrineValues.put(STREET_FIELD, "490 Riverside Drive");
		shrineValues.put(TOWN_FIELD, "New York");
		shrineValues.put(STATE_FIELD, "New York");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1212-870-6700");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "40.811952");
		shrineValues.put(LONGITUDE_FIELD, "-73.963255");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Willow Creek Community Church");
		shrineValues.put(STREET_FIELD, "67 E Algonquin Rd");
		shrineValues.put(TOWN_FIELD, "South Barrington");
		shrineValues.put(STATE_FIELD, "Illinois");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1847-765-5000");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "42.093472");
		shrineValues.put(LONGITUDE_FIELD, "-88.132692");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "New Life Church");
		shrineValues.put(STREET_FIELD, "11025 Voyager Parkway");
		shrineValues.put(TOWN_FIELD, "Colorado Springs");
		shrineValues.put(STATE_FIELD, "Colorado");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1719-594-6602");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "38.991959");
		shrineValues.put(LONGITUDE_FIELD, "-104.795596");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Mariners Church");
		shrineValues.put(STREET_FIELD, "5001 Newport Coast Drive");
		shrineValues.put(TOWN_FIELD, "Irvine");
		shrineValues.put(STATE_FIELD, "California");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1949-854-7600");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "33.632509");
		shrineValues.put(LONGITUDE_FIELD, "-117.831236");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "The Abyssinian Baptist Church");
		shrineValues.put(STREET_FIELD, "132 Odell Clark Place");
		shrineValues.put(TOWN_FIELD, "New York");
		shrineValues.put(STATE_FIELD, "New York");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1212-862-7474");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "40.816753");
		shrineValues.put(LONGITUDE_FIELD, "-73.941601");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Holy Name Cathedral");
		shrineValues.put(STREET_FIELD, "Chicago");
		shrineValues.put(TOWN_FIELD, " Chicago");
		shrineValues.put(STATE_FIELD, " Illinois");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1312-787-8040");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "41.895962");
		shrineValues.put(LONGITUDE_FIELD, "-87.628297");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Crystal Cathedral");
		shrineValues.put(STREET_FIELD, "12141 South Lewis Street");
		shrineValues.put(TOWN_FIELD, "Garden Grove");
		shrineValues.put(STATE_FIELD, " California");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1714-971-4000");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "33.786404");
		shrineValues.put(LONGITUDE_FIELD, "-117.898984");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Patronato San Xavier");
		shrineValues.put(STREET_FIELD, "1950 West San Xavier Road");
		shrineValues.put(TOWN_FIELD, "Tucson");
		shrineValues.put(STATE_FIELD, " Arizona");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1520-407-6130 ");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "32.107304");
		shrineValues.put(LONGITUDE_FIELD, "-111.007364");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Glide Memorial United Methodist Church");
		shrineValues.put(STREET_FIELD, "San Francisco");
		shrineValues.put(TOWN_FIELD, "California");
		shrineValues.put(STATE_FIELD, " California");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1415-674-6000");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "37.785177");
		shrineValues.put(LONGITUDE_FIELD, "-122.41154");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Park Street Church");
		shrineValues.put(STREET_FIELD, "1 Park Street");
		shrineValues.put(TOWN_FIELD, "Boston");
		shrineValues.put(STATE_FIELD, "  Massachusetts");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "USA");
		shrineValues.put(CONTACT_FIELD, "+1617-523-3383");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "42.356804");
		shrineValues.put(LONGITUDE_FIELD, "-71.062003");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Abu Hanifa Mosque");
		shrineValues.put(STREET_FIELD, "Al-Adamiyah");
		shrineValues.put(TOWN_FIELD, "");
		shrineValues.put(STATE_FIELD, "Baghdad");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "Iraq");
		shrineValues.put(CONTACT_FIELD, "+9647711425874");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "33.371178");
		shrineValues.put(LONGITUDE_FIELD, "44.358995");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Great Mosque");
		shrineValues.put(STREET_FIELD, "Camiikebir Mh.");
		shrineValues.put(TOWN_FIELD, "");
		shrineValues.put(STATE_FIELD, "Diyarbakir Province");
		shrineValues.put(ZIP_FIELD, "21300");
		shrineValues.put(COUNTRY_FIELD, "Turkey");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "37.912238");
		shrineValues.put(LONGITUDE_FIELD, "40.23582");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Halil Urrahman Mosque");
		shrineValues.put(STREET_FIELD, "Merkez.");
		shrineValues.put(TOWN_FIELD, "Pehlivan Mah.");
		shrineValues.put(STATE_FIELD, "Hakkari");
		shrineValues.put(ZIP_FIELD, "30000");
		shrineValues.put(COUNTRY_FIELD, "Turkey");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "37.573794");
		shrineValues.put(LONGITUDE_FIELD, "43.732916");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Imam Khomeini Mosque");
		shrineValues.put(STREET_FIELD, "Ghal-e-Tabarok");
		shrineValues.put(TOWN_FIELD, "Isfahan");
		shrineValues.put(STATE_FIELD, "Tehran");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "Iran");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "32.654976");
		shrineValues.put(LONGITUDE_FIELD, "51.677805");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Ancient Jameh Mosque of Qazvin");
		shrineValues.put(STREET_FIELD, "");
		shrineValues.put(TOWN_FIELD, "Sepah");
		shrineValues.put(STATE_FIELD, "Qazvin");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "Iran");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "32.654976");
		shrineValues.put(LONGITUDE_FIELD, "51.677805");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);
			

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Rasoul-e Akram Mosque");
		shrineValues.put(STREET_FIELD, "Jahanshahr");
		shrineValues.put(TOWN_FIELD, "Karaj");
		shrineValues.put(STATE_FIELD, "Alborz");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "Iran");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "0");
		shrineValues.put(ISMOSQUE_FIELD, "1");
		shrineValues.put(LATITUDE_FIELD, "35.824855");
		shrineValues.put(LONGITUDE_FIELD, "50.984301");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Lister Hill Baptist Church");
		shrineValues.put(STREET_FIELD, "1 Brownberrie Avenue");
		shrineValues.put(TOWN_FIELD, "Leeds");
		shrineValues.put(STATE_FIELD, "Yorkshire");
		shrineValues.put(ZIP_FIELD, "LS18 5PW");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441132584506");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "53.846664");
		shrineValues.put(LONGITUDE_FIELD, "-1.634358");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "New Life Community Church");
		shrineValues.put(STREET_FIELD, "");
		shrineValues.put(TOWN_FIELD, "Yeadon");
		shrineValues.put(STATE_FIELD, "West Yorkshire");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441132506249");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "53.867735");
		shrineValues.put(LONGITUDE_FIELD, "-1.685441");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Grace Baptist Church");
		shrineValues.put(STREET_FIELD, "Lune Street");
		shrineValues.put(TOWN_FIELD, "");
		shrineValues.put(STATE_FIELD, "Lancaster");
		shrineValues.put(ZIP_FIELD, "LA1 2AJ");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441524841133 ");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "54.054021");
		shrineValues.put(LONGITUDE_FIELD, "-2.800269");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St Marks Church");
		shrineValues.put(STREET_FIELD, "Darlington Rd, Middleton St George");
		shrineValues.put(TOWN_FIELD, "Darlington");
		shrineValues.put(STATE_FIELD, "County Durham");
		shrineValues.put(ZIP_FIELD, "DL2");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441325382400");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "54.548529");
		shrineValues.put(LONGITUDE_FIELD, "-1.546827");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St Mary's Church");
		shrineValues.put(STREET_FIELD, "Broadfield Walk");
		shrineValues.put(TOWN_FIELD, "Leyland");
		shrineValues.put(STATE_FIELD, "Lancashire");
		shrineValues.put(ZIP_FIELD, "PR25 1PD");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441772455955");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "53.692126");
		shrineValues.put(LONGITUDE_FIELD, "-2.705043");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St Huberts R C Church");
		shrineValues.put(STREET_FIELD, "Trough Road");
		shrineValues.put(TOWN_FIELD, "Clitheroe");
		shrineValues.put(STATE_FIELD, "Lancashire");
		shrineValues.put(ZIP_FIELD, "BB7 3BG");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441200448231");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "53.945999");
		shrineValues.put(LONGITUDE_FIELD, "-2.524672");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "All Saints C Of E Church");
		shrineValues.put(STREET_FIELD, "Church St, Clayton-le-Moors");
		shrineValues.put(TOWN_FIELD, "Accrington");
		shrineValues.put(STATE_FIELD, "Lancashire");
		shrineValues.put(ZIP_FIELD, "BB5 5HT");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441254384321");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "53.774385");
		shrineValues.put(LONGITUDE_FIELD, "-2.386372");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St John The Evangelist Church");
		shrineValues.put(STREET_FIELD, "John street, Meadowfield");
		shrineValues.put(TOWN_FIELD, "Duram");
		shrineValues.put(STATE_FIELD, "New Hampshire");
		shrineValues.put(ZIP_FIELD, "DH7 8RP");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441913780845");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "54.752835");
		shrineValues.put(LONGITUDE_FIELD, "-1.616216");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);
		

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Kendal Parish Church");
		shrineValues.put(STREET_FIELD, "Kirkland, Kendal, ");
		shrineValues.put(TOWN_FIELD, "Kendal");
		shrineValues.put(STATE_FIELD, "Cumbria");
		shrineValues.put(ZIP_FIELD, "LA9 5AF");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441539721248");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "54.322573");
		shrineValues.put(LONGITUDE_FIELD, "-2.744396");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);
		
		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St Thomas Aquinas Church");
		shrineValues.put(STREET_FIELD, "North Road, Darlington");
		shrineValues.put(TOWN_FIELD, "Darlington");
		shrineValues.put(STATE_FIELD, "County Durham");
		shrineValues.put(ZIP_FIELD, "DL1 2PU");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441325463636");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "54.544012");
		shrineValues.put(LONGITUDE_FIELD, "-1.548469");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Our Lady of Lourdes Presbytery");
		shrineValues.put(STREET_FIELD, "Ebor Lane, Haworth, Keighley");
		shrineValues.put(TOWN_FIELD, "Keighley");
		shrineValues.put(STATE_FIELD, "West Yorkshire");
		shrineValues.put(ZIP_FIELD, "BD22 8HR");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "53.834217");
		shrineValues.put(LONGITUDE_FIELD, "-1.94594");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St John The Evangelist Church");
		shrineValues.put(STREET_FIELD,"St Johns Vicarage, Gorple Road, Worsthorne");
		shrineValues.put(TOWN_FIELD, "Burnley");
		shrineValues.put(STATE_FIELD, "Lancashire");
		shrineValues.put(ZIP_FIELD, "BB10 3NN");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441282428478");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "53.787829");
		shrineValues.put(LONGITUDE_FIELD, "-2.189557");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St Patricks Catholic Church");
		shrineValues.put(STREET_FIELD, "51 Saint John's Road, Heysham");
		shrineValues.put(TOWN_FIELD, "Morecambe");
		shrineValues.put(STATE_FIELD, "Lancashire");
		shrineValues.put(ZIP_FIELD, "LA3 1EX");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441524410322");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "54.059264");
		shrineValues.put(LONGITUDE_FIELD, "-2.887468");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St Stephen's R C Church");
		shrineValues.put(STREET_FIELD, "St. Stephens/Castle View Ter");
		shrineValues.put(TOWN_FIELD, "Skipton");
		shrineValues.put(STATE_FIELD, "Yorkshire");
		shrineValues.put(ZIP_FIELD, "BD23 1NT");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441756793000");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "53.963802");
		shrineValues.put(LONGITUDE_FIELD, "-2.019634");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "St Marys Parish Church");
		shrineValues.put(STREET_FIELD, "Crumlin Road");
		shrineValues.put(TOWN_FIELD, "Belfast ");
		shrineValues.put(STATE_FIELD, "");
		shrineValues.put(ZIP_FIELD, " BT13 1RU");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+442890584540");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "54.610201");
		shrineValues.put(LONGITUDE_FIELD, "-5.95039");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD, "Pannal C Of E Church");
		shrineValues.put(STREET_FIELD, "21 Crimple Meadows, Pannal");
		shrineValues.put(TOWN_FIELD, "Harrogate");
		shrineValues.put(STATE_FIELD, "North Yorkshire");
		shrineValues.put(ZIP_FIELD, "HG3 1EL");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441423870202");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "53.959212");
		shrineValues.put(LONGITUDE_FIELD, "-1.538727");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		shrineValues = new ContentValues();
		shrineValues.put(NAME_FIELD,"Our Lady And English Martyrs Catholic Church");
		shrineValues.put(STREET_FIELD, "near Addingham");
		shrineValues.put(TOWN_FIELD, "Addingham");
		shrineValues.put(STATE_FIELD, "West Yorkshire");
		shrineValues.put(ZIP_FIELD, "");
		shrineValues.put(COUNTRY_FIELD, "United Kingdom");
		shrineValues.put(CONTACT_FIELD, "+441943830259");
		shrineValues.put(ISFAVORITE_FIELD, "0");
		shrineValues.put(ISTEMPLE_FIELD, "0");
		shrineValues.put(ISCHURCH_FIELD, "1");
		shrineValues.put(ISMOSQUE_FIELD, "0");
		shrineValues.put(LATITUDE_FIELD, "53.944958");
		shrineValues.put(LONGITUDE_FIELD, "-1.88062");

		appDatabase.insert(TABLE_FTS_NAME, null, shrineValues);

		appDatabase.setTransactionSuccessful();
		appDatabase.endTransaction();
	}

}
