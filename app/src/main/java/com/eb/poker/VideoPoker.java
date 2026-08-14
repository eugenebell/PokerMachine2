package com.eb.poker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class VideoPoker extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent ss = new Intent(VideoPoker.this, SplashScreen.class);
        VideoPoker.this.startActivity(ss);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	finish();
    }
}