package edu.asu.tankgame;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WeaponSelectActivity extends Activity {

	public final static String EXTRA_MESSAGE = "edu.asu.tankgame.message";	// a key for passing extras
	ListView weaponsListView;
	ArrayAdapter arrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weapon_select);
		// Show the Up button in the action bar.
		weaponsListView = (ListView) findViewById(R.id.listView1);
		String[] WeaponArray = new String[20];
		for(int i=0; i < WeaponArray.length; i++){
        	WeaponArray[i] = "Weapon " + i;
        }
		arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, WeaponArray)
		{

	        @Override
	        public View getView(int position, View convertView,
	                ViewGroup parent) {
	            View view =super.getView(position, convertView, parent);

	            TextView textView=(TextView) view.findViewById(android.R.id.text1);

	            /*YOUR CHOICE OF COLOR*/
	            textView.setTextColor(Color.RED);

	            return view;
	        }
	    };
		weaponsListView.setAdapter(arrayAdapter);
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weapon_select, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void playGame(View view)
	{
		// http://developer.android.com/training/basics/firstapp/starting-activity.html#BuildIntent
		// an Intent is an object that provides runtime binding between separate components (two activities in this case).
		// They're usually, but not always, used to start another activity.
		
		Intent intent = new Intent(this, PlayGameActivity.class);	// constructor takes Context then class to deliver intent to
		String message = "Hi from weapon select menu";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		
		
	}
}
