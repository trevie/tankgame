package edu.asu.tankgame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FixedResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import android.util.DisplayMetrics;

public class PlayGameActivity extends BaseGameActivity{
	
	private Camera mCamera;
	private Scene mScene;
	private int Height;
	private int Width;
	public Sprite[][] mLevelSprites;
	
	@Override
	public EngineOptions onCreateEngineOptions()
	{
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		Height = dm.heightPixels;
		Width = dm.widthPixels;	
		// Define our mCamera object
		mCamera = new Camera(0,0, Width, Height);
		
		EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FixedResolutionPolicy(Width, Height), mCamera);
		
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);		
		return engineOptions;
	}
	
	@Override
	public void onCreateResources( OnCreateResourcesCallback pOnCreateResourcesCallback)
	{
		ResourceManager.getInstance().loadGameTextures(mEngine, this);
		GameManager.getInstance().generateLevel(Height, Width);
		// Notify call back that we've finished.	
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}
	
	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
	{
		// Create the scene object and background roughly sky colored
		mScene = new Scene();
		Background background = new Background(0.45f,0.69f,0.85f,1f);
		mScene.setBackground(background);
		mScene.setBackgroundEnabled(true);
		
		ITextureRegion temp = null;
		boolean [][] level = GameManager.getInstance().getLevel();
		
		mLevelSprites =  new Sprite[level.length][level[0].length];
		
		for(int i = 0; i < mLevelSprites.length; i++)
			for(int j = 0; j < mLevelSprites[i].length; j++)
			{				
				if(level[i][j] == true)
				{
					temp = querryTile(i,j);
					if(temp != null)
					{
						mLevelSprites[i][j] = new Sprite((i*48),Height - (j*48), temp, mEngine.getVertexBufferObjectManager());
						mScene.attachChild(mLevelSprites[i][j]);
					}
				}
			}
		// Notify the callback that we've finished creating the scene
		pOnCreateSceneCallback.onCreateSceneFinished(mScene);
	}
	
	public ITextureRegion querryTile(int x, int y)
	{
		ITextureRegion temp = null;
		boolean above;
		boolean left;
		boolean right;
		boolean [][] level = GameManager.getInstance().getLevel();

		if(y+1 == level[x].length)
			above = false;
		else
			above = level[x][y+1];
		if(x == 0)
			left = false;
		else
			left = level[x-1][y];
		if(x+1 == level.length)
			right = false;
		else
			right = level[x+1][y];
		
		if(above == false && left == true && right == true)
			temp = ResourceManager.getInstance().mCenterTextureRegion;
		else if(above == false && left == false && right == true)
			temp = ResourceManager.getInstance().mLeftCornerTextureRegion;
		else if(above == false && left == true && right == false)
			temp = ResourceManager.getInstance().mRightCornerTextureRegion;
		else if(above == true && left == false && right == true)
			temp = ResourceManager.getInstance().mLeftTextureRegion;
		else if(above == true && left == true && right == false)
			temp = ResourceManager.getInstance().mRightTextureRegion;
		else if(above == true && left == false && right == false)
			temp = ResourceManager.getInstance().mPillarTextureRegion;
		else if(above == true && left == true && right == true)
			temp = ResourceManager.getInstance().mBlankTextureRegion;
		
		return temp;
	}
	
	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback)
	{
		// Inform callback we've finished
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
}
