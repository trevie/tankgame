package edu.asu.tankgame;

public class GameManager {

	private static GameManager INSTANCE;
	
	private boolean [][] level;
	private int[] playerHealth;
	private int[] playerPosition;

	public static final int maxPlayers = 4;	
	public static final int initialPlayerHealth = 100;
	
	GameManager()
	{
		playerHealth = new int[maxPlayers];
		playerPosition = new int[maxPlayers];
		
		for(int i = 0; i < maxPlayers; i++)
			playerHealth[i] = initialPlayerHealth;
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
			for(int i = 0; i < maxPlayers; i++)
				playerPosition[i] = (int) (Math.random() * level.length);
//		}
		
		int limit;
		for(int i = 0; i < level.length; i++)
			for(int j = 0; j < level[i].length; j++)
				level[i][j] = false;
		
		for(int i = 0; i < level.length; i++)
		{
			limit = (int) (Math.random() * level[i].length);
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
