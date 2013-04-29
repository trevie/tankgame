/**
 * 
 */
package edu.asu.tankgame;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Michael J. Astrauskas
 *
 */
public class MySQLiteHelper extends SQLiteOpenHelper
{

	// These constants are used to refer to table and column names.
	// This isn't required, but is an Android conventional.
	public static final String TABLE_COMMENTS = "comments";
	public static final String COLUMN_ID ="_id"; // you should ALWAYS use _id, by convention
	public static final String COLUMN_COMMENT = "comment";
	public static final String COLUMN_SCORE = "score";
	
	private static final String DATABASE_NAME = "comments.db";
	private static final int DATABASE_VERSION = 2; // version numbers are internal to the programs.  It's so you can update the Db if a newer version of the app has a never Db version.
	
	private static final String DATABASE_CREATE_comments = "CREATE TABLE "
			+ TABLE_COMMENTS + "("
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_COMMENT + " text not null, "
			+ COLUMN_SCORE + " integer);";
	
	public MySQLiteHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public MySQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 */
	public MySQLiteHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler)
	{
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase database)
	{
		// TODO Auto-generated method stub
		
		database.execSQL(DATABASE_CREATE_comments);

	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		Log.w(MySQLiteHelper.class.getName(),"Upgrading Db from " + oldVersion + " to " + newVersion);
		
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
		//onCreate(db);
		switch (oldVersion)
		{
			case 1:
				//db.execSQL("");
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
				onCreate(db);
				break; // Use fall-through if updating several versions
			//case default:
				
		}
	}

}
