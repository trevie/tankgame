package edu.asu.tankgame;

//port android.app.Activity;
import java.util.ArrayList;
//import java.util.List;
import java.util.Random;

//import android.app.Activity;
import android.app.Activity;
//import android.app.ListActivity;
//import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
//import android.webkit.WebChromeClient.CustomViewCallback;
//import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HighScoresActivity extends Activity { // ListActivity {

	private CommentsDataSource datasource;
	
	private ListView lv;
	private HighScoreAdapter adapter;
	//private ArrayList<Comment> fetch = new ArrayList<Comment>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
		
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true); // *** testing
		
		//Intent intent = getIntent();
		//String message = intent.getStringExtra(MainMenuActivity.EXTRA_MESSAGE);	// get message by "key" name
		
		//setContentView(R.layout.activity_high_scores);
		
		datasource = new CommentsDataSource(this);
		datasource.open();
		
		ArrayList<Comment> values = datasource.getAllComments();
		
		lv = (ListView) findViewById(R.id.listHighScores);
		//adapter = new HighScoreAdapter(HighScoresActivity.this, R.id.listView1, fetch);
		//adapter = new HighScoreAdapter(HighScoresActivity.this, values);
		
		// public HighScoreAdapter (Activity a, int textViewResourceId, ArrayList<Comment> data)
		adapter = new HighScoreAdapter(HighScoresActivity.this, R.id.listHighScores, values);
		
		// Use the SimpleCursorAdapter to show the elements in a ListView
		//ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this, android.R.layout.simple_list_item_1, values); // android.R.layout.simple_list_item_1 is a common default
		//ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this, android.R.layout.two_line_list_item, android.R.id.text1, values); // android.R.layout.simple_list_item_1 is a common default
		
		//((ListView)findViewById(R.id.listHighScores)).setAdapter(adapter);
		lv.setAdapter(adapter);
		//setListAdapter(adapter);
		
	}
	
	// Called via onClick attribute for the buttons in activity_high_scores.xml
	public void onClick(View view)
	{
		//@SuppressWarnings("unchecked")
		//ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
		//HighScoreAdapter adapter = (HighScoreAdapter) getListAdapter();
		HighScoreAdapter adapter = (HighScoreAdapter) lv.getAdapter();
		
		Comment comment = null;
		
		////////////////
		// ADD button //
		////////////////
		if (view.getId() == R.id.add) {
			String[] comments = new String[] { "Drew", "Ben", "Michael" };
			int randName = new Random().nextInt(3);
			//int randScore = new Random().nextInt(55)*1000;
			int randScore = (int)(Math.random() * 55) * 1000;
			Log.w("onClick", "New randScore: " + Integer.toString(randScore));
			// save the new comment to the database
			comment = datasource.createComment(comments[randName], randScore);
			adapter.add(comment);
		}
		///////////////////
		// DELETE button //
		///////////////////
		else if (view.getId() == R.id.delete) 
		{
			//if (getListAdapter().getCount() > 0) // if extending ListActivity
			if (lv.getAdapter().getCount() > 0)
			{
				//comment = (Comment) getListAdapter().getItem(0);
				comment = (Comment) lv.getAdapter().getItem(0);
				datasource.deleteComment(comment);
				adapter.remove(comment);
			}
		}
		adapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onResume()
	{
		datasource.open();
		super.onResume();
	}
	
	@Override
	protected void onPause()
	{
		datasource.close();
		super.onPause();
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_high_scores, menu);
		return true;
	}*/

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

}
