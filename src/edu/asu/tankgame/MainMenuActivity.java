package edu.asu.tankgame;

//import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

// We use view.View and content.Intent for switching to another view

public class MainMenuActivity extends Activity {

	public final static String EXTRA_MESSAGE = "edu.asu.tankgame.message";	// a key for passing extras
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}
	
	// When user clicks on [High Scores] button on main menu
	public void showHighScores(View view)
	{
		// http://developer.android.com/training/basics/firstapp/starting-activity.html#BuildIntent
		// an Intent is an object that provides runtime binding between separate components (two activities in this case).
		// They're usually, but not always, used to start another activity.
		
		Intent intent = new Intent(this, HighScoresActivity.class);	// constructor takes Context then class to deliver intent to
		String message = "Hi from main menu";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		
		
	}
	// When user clicks on [Start Game] button on main menu
	public void showWeaponSelect(View view)
	{
		// http://developer.android.com/training/basics/firstapp/starting-activity.html#BuildIntent
		// an Intent is an object that provides runtime binding between separate components (two activities in this case).
		// They're usually, but not always, used to start another activity.
		
		Intent intent = new Intent(this, WeaponSelectActivity.class);	// constructor takes Context then class to deliver intent to
		/* Highjacked for quick launching and testing
		Intent intent = new Intent(this, PlayGameActivity.class);
		*/
		String message = "Hi from main menu";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		
		
	}

}
