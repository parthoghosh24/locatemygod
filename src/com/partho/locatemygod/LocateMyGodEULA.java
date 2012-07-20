package com.partho.locatemygod;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;

public class LocateMyGodEULA extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.locatemygodeula);
		new SimpleEula(this).show();
		
	}
	
	
	// credits goes to DONN FELKER
	// http://blog.donnfelker.com/2011/02/17/android-a-simple-eula-for-your-android-apps/
	class SimpleEula {

		private String EULA_PREFIX = "eula_";
		private Activity mActivity;

		public SimpleEula(Activity context) {
			mActivity = context;
		}

		private PackageInfo getPackageInfo() {
			PackageInfo pi = null;
			try {
				pi = mActivity.getPackageManager().getPackageInfo(
						mActivity.getPackageName(),
						PackageManager.GET_ACTIVITIES);
			} catch (PackageManager.NameNotFoundException e) {
				e.printStackTrace();
			}
			return pi;
		}

		public void show() {
			PackageInfo versionInfo = getPackageInfo();

			// the eulaKey changes every time you increment the version number
			// in the AndroidManifest.xml
			final String eulaKey = EULA_PREFIX + versionInfo.versionCode;
			final SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(mActivity);
			boolean hasBeenShown = prefs.getBoolean(eulaKey, false);
			if (!hasBeenShown) 
			{

				// Show the Eula
				String title = mActivity.getString(R.string.app_name) + " v"
						+ versionInfo.versionName;

				// Includes the updates as well so users know what changed.

				String message = Html.fromHtml(mActivity
						.getString(R.string.eula_updates_text))
						+ "\n\n"
						+ Html.fromHtml(mActivity
								.getString(R.string.eula_license_text));

				AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
						.setTitle(title)
						.setMessage(message)
						.setPositiveButton("Agree",
								new Dialog.OnClickListener() {

									@Override
									public void onClick(
											DialogInterface dialogInterface,
											int i) {
										// Mark this version as read.
										SharedPreferences.Editor editor = prefs
												.edit();
										editor.putBoolean(eulaKey, true);
										editor.commit();
										dialogInterface.dismiss();
										Intent splashIntent = new Intent(mActivity,SplashActivity.class);
										startActivity(splashIntent);
										finish();

									}
								})
						.setNegativeButton("Disagree",
								new Dialog.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// Close the activity as they have
										// declined the EULA
										mActivity.finish();
									}

								});
				builder.create().show();
			}
			else
			{
				Intent splashIntent = new Intent(mActivity,SplashActivity.class);
				startActivity(splashIntent);
				finish();
			}
		}
	}
}
