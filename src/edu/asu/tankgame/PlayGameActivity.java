package edu.asu.tankgame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FixedResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
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
		Background background = new Background(0f,0.5f,1f,1f);
		mScene.setBackground(background);
		mScene.setBackgroundEnabled(true);
		
		short [] level = GameManager.getInstance().getLevel();
		
		mLevelSprites =  new Sprite[Width/48][Height/48];
		for(int i = 0; i < mLevelSprites.length; i++)
			for(int j = 0; j < mLevelSprites[i].length; j++)
			{
				if(level[i] >= j)
				{
					// The tile is not located on the left or right most edge
					if(i+1 != level.length && i-1 != -1)
					{
						if(level[i] == j+1)
						{
							if(level[i-1] >= j && level[i+1] >= j)
							{
								mLevelSprites[i][j] = new Sprite(i*48, j*48, ResourceManager.getInstance().mBlankTextureRegion, mEngine.getVertexBufferObjectManager());
								mScene.attachChild(mLevelSprites[i][j]);
							}
							
						}
						else
						{							
							// There are not tiles to the left or right, but one above
							if(j > level[i-1] && j > level[i+1])
							{
								mLevelSprites[i][j] = new Sprite(i*48, j*48, ResourceManager.getInstance().mCenterTextureRegion, mEngine.getVertexBufferObjectManager());
								mScene.attachChild(mLevelSprites[i][j]);
							}
							// There is a tile to the right and one above
							else if(j < level[i-1] && j > level[i+1])
							{
								mLevelSprites[i][j] = new Sprite(i*48, j*48, ResourceManager.getInstance().mLeftTextureRegion, mEngine.getVertexBufferObjectManager());
								mScene.attachChild(mLevelSprites[i][j]);
							}
							else if(j > level[i-1] && j < level[i+1])
							{
								mLevelSprites[i][j] = new Sprite(i*48, j*48, ResourceManager.getInstance().mRightTextureRegion, mEngine.getVertexBufferObjectManager());
								mScene.attachChild(mLevelSprites[i][j]);
							}
							else
							{
								mLevelSprites[i][j] = new Sprite(i*48, j*48, ResourceManager.getInstance().mBlankTextureRegion, mEngine.getVertexBufferObjectManager());
								mScene.attachChild(mLevelSprites[i][j]);
							}
						}
					}
				}
			}
		
		// Notify the callback that we've finished creating the scene
		pOnCreateSceneCallback.onCreateSceneFinished(mScene);
	}
	
	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback)
	{
		// Inform callback we've finished
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
}
