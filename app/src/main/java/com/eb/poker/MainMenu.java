package com.eb.poker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MainMenu extends Activity implements OnItemClickListener, OnItemLongClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.mainmenu);
		setupListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// call the parent to attach any system level menus
		super.onCreateOptionsMenu(menu);
		menu.add(0, 1, 0, "append");
		menu.add(0, 2, 1, "item2");
		menu.add(0, 3, 2, "clear");
		// It is important to return true to see the menu
		return true;
	}

	private void setupListView() {
		String[] listItems = new String[] {"Play", "High Scores", "Options", "Help", "About"};
		ArrayAdapter<String> listItemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		ListView lv = (ListView) this.findViewById(R.id.ListMenu);
		lv.setAdapter(listItemAdapter);
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case 0:
			Intent intentP = new Intent(MainMenu.this, Play.class);
			MainMenu.this.startActivity(intentP);
			break;
		case 1:
			Intent intentHS = new Intent(MainMenu.this, HighScore.class);
			MainMenu.this.startActivity(intentHS);
			break;
		case 2:

			break;
		case 3:
			Intent intentH = new Intent(MainMenu.this, Help.class);
			MainMenu.this.startActivity(intentH);
			break;
		case 4:
			Intent intentA = new Intent(MainMenu.this, About.class); 
			MainMenu.this.startActivity(intentA);
			break;
		}
	}

	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		System.out.println("received onItemLongClick()");
		return true;
	}
}
