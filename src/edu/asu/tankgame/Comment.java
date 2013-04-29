package edu.asu.tankgame;

public class Comment {
  private long id;
  private String comment;
  private int score;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
  
  public int getScore() {
    return score;
  }
  
  public void setScore(int score) {
      this.score = score;
  }

  // Will be used by the ArrayAdapter in the ListView
  @Override
  public String toString() {
    return comment + " (" + score + ")";
  }
} 