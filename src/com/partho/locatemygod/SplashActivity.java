package com.partho.locatemygod;



import com.partho.locatemygod.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity{

	private final int SPLASH_DELAY= 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splashactivitylayout);						
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent= new Intent(SplashActivity.this, AppTabsActivity.class);
				startActivity(intent);
				finish();
			}
		}, SPLASH_DELAY);
	}

	
}
