package edu.asu.tankgame;

public class GameManager {

	private static GameManager INSTANCE;
	
	private boolean [][] level;
	private int[] playerHealth;
	private int[] playerPosition;
	private int[] playerAngle;		// 0 is left pointing level, 180 is right pointing level
	private int[] playerPower;

	public static final int maxPlayers = 2;	
	public static final int initialPlayerHealth = 100;
	
	GameManager()
	{
		playerHealth = new int[maxPlayers];
		playerPosition = new int[maxPlayers];
		playerAngle = new int[maxPlayers];
		playerPower = new int[maxPlayers];
		
		for(int i = 0; i < maxPlayers; i++)
		{
			playerHealth[i] = initialPlayerHealth;
			playerPower[i] = 50;
			if(i%2 == 0)
				playerAngle[i] = 180;
			else
				playerAngle[i] = 0;
		}
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
		
		// TODO Simple code for randomly generating level maybe change to a polynomial to create level.		
//		Boolean allSet = false;
//		while(allSet == false)
//		{
//			for(int i = 0; i < maxPlayers; i++)
//				playerPosition[i] = (int) (Math.random() * level.length);
//		}
		playerPosition[0] = 2;
		playerPosition[1] = level.length - 3;
		
		int limit;
		for(int i = 0; i < level.length; i++)
			for(int j = 0; j < level[i].length; j++)
				level[i][j] = false;
		
		for(int i = 0; i < level.length; i++)
		{
			limit = (int) (Math.random() * (level[i].length - 2));
			if(limit < 2)
				limit = 2;
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
	
	public int getPlayerHealth(int player)
	{
		if(player > 0 && player <= maxPlayers)
			return this.playerHealth[player - 1];
		else
			return 0;
	}
	
	public int getPlayerPosition(int player)
	{
		if(player > 0 && player <= maxPlayers)
			return this.playerPosition[player - 1];
		else
			return 0;
	}
	
	public void damageP1(int player, int damage)
	{
		if(player > 0 && player <= maxPlayers)
			playerHealth[player - 1] = playerHealth[player - 1] - damage;
	}
}
