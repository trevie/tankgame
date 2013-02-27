package edu.asu.tankgame;

public class GameManager {

	private static GameManager INSTANCE;
	
	private short [] level;
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
	
	public short[] generateLevel(int height, int width)
	{
		level = new short [width/48];
		
		// TODO Code for randomly generating level until then hard coded level		
		
		for(int i = 0; i < level.length; i = i + 4)
		{
			level[i] = 6;
			level[i+1] = 7;
			level[i+2] = 8;
			level[i+3] = 7;
		}
		if(level.length % 4 != 0)
		{
			for(int i = 0; i < level.length % 4; i++)
			{
				level[level.length - 1 - i] = 6;
			}
		}
		return this.level;
	}
	
	public short[] getLevel()
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
