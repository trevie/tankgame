package edu.asu.tankgame;

import org.andengine.engine.Engine;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.debug.Debug;

import android.content.Context;



public class ResourceManager {

	private static ResourceManager INSTANCE;
	
	public ITextureRegion mRightCornerTextureRegion;
	public ITextureRegion mLeftCornerTextureRegion;
	public ITextureRegion mCenterTextureRegion;
	public ITextureRegion mLeftTextureRegion;
	public ITextureRegion mRightTextureRegion;
	public ITextureRegion mBlankTextureRegion;
	public ITextureRegion mTankTextureRegion;
	public ITextureRegion mBarrelTextureRegion;
	
	public BitmapTextureAtlas mBitmapTextureAtlas;
	
	ResourceManager(){
		// The constructor is of no use to us
	}

	public synchronized static ResourceManager getInstance(){
		if(INSTANCE == null){
			INSTANCE = new ResourceManager();
		}
		return INSTANCE;
	}


	public synchronized void loadGameTextures(Engine pEngine, Context pContext){
		// Set our game assets folder in "assets/gfx/game/"
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		
		BuildableBitmapTextureAtlas mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(pEngine.getTextureManager(), 256, 256);
		
		
		mRightCornerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "right_corner_tile.png");
		mLeftCornerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "left_corner_tile.png");
		mCenterTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "up_tile.png");
		mRightTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "right_tile.png");
		mLeftTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "left_tile.png");
		mBlankTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "blank_tile.png");
		
		mTankTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "tank.png");
		mBarrelTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "gun.png");
		
		try {
			mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
			mBitmapTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}

	public synchronized void unloadGameTextures(){
		// call unload to remove the corresponding texture atlas from memory
		BuildableBitmapTextureAtlas mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mRightCornerTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mLeftCornerTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mCenterTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mLeftTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mRightTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mBlankTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mTankTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mBarrelTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		
		System.gc();
	}
	
}
