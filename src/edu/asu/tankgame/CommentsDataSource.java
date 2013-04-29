package edu.asu.tankgame;

import java.util.ArrayList;
//import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import edu.asu.tankgame.Comment;

public class CommentsDataSource {

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;
  private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_COMMENT, MySQLiteHelper.COLUMN_SCORE }; //

  public CommentsDataSource(Context context) {
    dbHelper = new MySQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Comment createComment(String comment, int score) { // , int score
    ContentValues values = new ContentValues();
    values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
    values.put(MySQLiteHelper.COLUMN_SCORE, score);
    Log.w("createComment","About to add ("+comment+","+score+")");
    long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null, values);
    Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
    cursor.moveToFirst();
    Comment newComment = cursorToComment(cursor);
    Log.w("createComment", "Cursor now at (" + newComment.getComment() + ", " + newComment.getScore() + ")");
    cursor.close();
    return newComment;
  }

  public void deleteComment(Comment comment) {
    long id = comment.getId();
    System.out.println("Comment deleted with id: " + id);
    database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
        + " = " + id, null);
  }

  //public List<Comment> getAllComments() {
  public ArrayList<Comment> getAllComments() {
	ArrayList<Comment> comments = new ArrayList<Comment>();

    //Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, null, null, null, null, null);
	Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS, allColumns, null, null, null, null, MySQLiteHelper.COLUMN_SCORE + " DESC");

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Comment comment = cursorToComment(cursor);
      comments.add(comment);
      cursor.moveToNext();
    }
    // Make sure to close the cursor
    cursor.close();
    return comments;
  }

  private Comment cursorToComment(Cursor cursor) {
    Comment comment = new Comment();
    comment.setId(cursor.getLong(0));
    comment.setComment(cursor.getString(1));
    comment.setScore(cursor.getInt(2));
    return comment;
  }
}
