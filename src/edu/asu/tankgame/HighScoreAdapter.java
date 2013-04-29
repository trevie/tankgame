package edu.asu.tankgame;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
//import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
import android.widget.TextView;

//public class HighScoreAdapter extends BaseAdapter {
public class HighScoreAdapter extends ArrayAdapter<Comment>
{

	//private LayoutInflater inflater;
	private ArrayList<Comment> data;
	private Activity activity;
	
	//public HighScoreAdapter (Context context, ArrayList<Comment> data)
	public HighScoreAdapter (Activity a, int textViewResourceId, ArrayList<Comment> data)
	{
		super(a, textViewResourceId, data);
		//this.inflater = LayoutInflater.from(context);
		this.activity = a;
		this.data = data;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//return 0;
		return this.data.size();
	}

	/*@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		//return null;
		return this.data.get(arg0);
	}*/

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		//return 0;
		return arg0;
	}

	class ViewHolder
	{
		TextView txtFirst;
		TextView txtSecond;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return null;
		
		View v = convertView;
		ViewHolder holder;
		
		//LayoutInflater inflater = activity.getLayoutInflater();
		
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.high_score_row, null);
			
			holder = new ViewHolder();
			//Object b = R.id.big;
			//Object a = v.findViewById(R.id.big);
			holder.txtFirst = (TextView) v.findViewById(R.id.comment); 	// comment or big
			holder.txtSecond = (TextView) v.findViewById(R.id.score); // score or small
			//Log.w("getView", "Got txtFirst (" + holder.txtFirst + ") and txtSecond (" + holder.txtSecond + ")");
			v.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)v.getTag();
		}
		
		final Comment comment = data.get(position);
		if (comment != null)
		{
			//holder.txtFirst.setText(map.get(FIRST_COLUMN));
			//holder.txtSecond.setText(map.get(SECOND_COLUMN));
			
			//Log.w("getView", "Using txtFirst (" + holder.txtFirst + ") and txtSecond (" + holder.txtSecond + ")");
			holder.txtFirst.setText(comment.getComment());
			//holder.txtFirst.setText("poop");
			Object c = comment.getScore();
			holder.txtSecond.setText(c.toString());
			//holder.txtSecond.setText(1000);
		}
		//HashMap map = list.get(position);
		return v;
		
		
	}

}
