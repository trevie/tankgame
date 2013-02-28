package edu.asu.tankgame;

public class GameManager {

	private static GameManager INSTANCE;
	
	private boolean [][] level;
	private int p1health;
	private int p2health;
	private int p3health;
	private int p4health;
	
	private static final int initialPlayerHealth = 100;
	
	GameManager()
	{
		p1health = initialPlayerHealth;
		p2health = initialPlayerHealth;
		p3health = initialPlayerHealth;
		p4health = initialPlayerHealth;
	}
	
	public static GameManager getInstance(){
		if(INSTANCE == null){
			INSTANCE = new GameManager();
		}
		return INSTANCE;
	}
	
	public boolean[][] generateLevel(int height, int width)
	{
		level = new boolean [(int) Math.ceil(width/48.0)][(int) Math.ceil(height/48.0)];
		
		// TODO Code for randomly generating level until then hard coded level		
		int limit;
		for(int i = 0; i < level.length; i++)
			for(int j = 0; j < level[i].length; j++)
				level[i][j] = false;
		
		for(int i = 0; i < level.length; i++)
		{
			limit = (int) (Math.random() * Math.ceil(height/48.0));
			for(int j = 0;j <= limit; j++)
			{
				level[i][j] = true;
			}
		}

		return this.level;
	}
	
	public boolean[][] getLevel()
	{
		return level;
	}
	
	public int getP1Health()
	{
		return this.p1health;
	}
	
	public int getP2Health()
	{
		return this.p2health;
	}
	
	public int getP3Health()
	{
		return this.p3health;
	}
	
	public int getP4Health()
	{
		return this.p4health;
	}
	
	public void damageP1(int damage)
	{
		p1health = p1health - damage;
	}
	
	public void damageP2(int damage)
	{
		p1health = p2health - damage;
	}

	public void damageP3(int damage)
	{
		p1health = p3health - damage;
	}

	public void damageP4(int damage)
	{
		p1health = p4health - damage;
	}

}
