package com.eb.poker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {
	
	private final int SPLASH_DISPLAY_LENGHT = 3000;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        new Handler().postDelayed(new Runnable(){ 
            @Override 
            public void run() {
                 Intent mainIntent = new Intent(SplashScreen.this, MainMenu.class); 
                 SplashScreen.this.startActivity(mainIntent); 
                 SplashScreen.this.finish(); 
            } 
       }, SPLASH_DISPLAY_LENGHT);
    }
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		finish();
	}
}
