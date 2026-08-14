package com.eb.poker;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class HighScore extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.highscore);
		setupButton();
	}
	
	private void setupButton() {
		Button b = (Button)this.findViewById(R.id.startFAButtonId);
		b.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
					parentButtonClicked(v);
			}
		});
	}
	
	private void parentButtonClicked(View v) {
		animate();
	}
	
	private void animate() {
		ImageView imgView1 = (ImageView) findViewById(R.id.animationImage1);
		imgView1.setVisibility(ImageView.VISIBLE);
		imgView1.setBackgroundResource(R.anim.frame_animation_clubs);
		AnimationDrawable frameAnimation1 = (AnimationDrawable) imgView1.getBackground();
		ImageView imgView2 = (ImageView) findViewById(R.id.animationImage2);
		imgView2.setVisibility(ImageView.VISIBLE);
		imgView2.setBackgroundResource(R.anim.frame_animation_dimonds);
		AnimationDrawable frameAnimation2 = (AnimationDrawable) imgView2.getBackground();
		ImageView imgView3 = (ImageView) findViewById(R.id.animationImage3);
		imgView3.setVisibility(ImageView.VISIBLE);
		imgView3.setBackgroundResource(R.anim.frame_animation_hearts);
		AnimationDrawable frameAnimation3 = (AnimationDrawable) imgView3.getBackground();
		ImageView imgView4 = (ImageView) findViewById(R.id.animationImage4);
		imgView4.setVisibility(ImageView.VISIBLE);
		imgView4.setBackgroundResource(R.anim.frame_animation_spades);
		AnimationDrawable frameAnimation4 = (AnimationDrawable) imgView4.getBackground();
		if (frameAnimation1.isRunning()) {
			frameAnimation1.stop();
			frameAnimation2.stop();
			frameAnimation3.stop();
			frameAnimation4.stop();
		} else {
			frameAnimation1.stop();
			frameAnimation1.start();
			frameAnimation2.stop();
			frameAnimation2.start();
			frameAnimation3.stop();
			frameAnimation3.start();
			frameAnimation4.stop();
			frameAnimation4.start();
		}
	}
}
