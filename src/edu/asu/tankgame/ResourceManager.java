package edu.asu.tankgame;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.debug.Debug;

import android.content.Context;



public class ResourceManager {

	private static ResourceManager INSTANCE;
	
	public ITextureRegion mRightCornerTextureRegion;
	public ITextureRegion mLeftCornerTextureRegion;
	public ITextureRegion mCenterCornerTextureRegion;
	public ITextureRegion mCenterTextureRegion;
	public ITextureRegion mLeftTextureRegion;
	public ITextureRegion mRightTextureRegion;
	public ITextureRegion mBlankTextureRegion;
	public ITextureRegion mTankTextureRegion;
	public ITextureRegion mBarrelTextureRegion;
	public ITextureRegion mPillarTextureRegion;
	public ITextureRegion mBarBGTextureRegion;
	public ITextureRegion mBarLensTextureRegion;
	public ITextureRegion mBarLineTextureRegion;
	public ITextureRegion mHaloTextureRegion;
	public ITextureRegion mFireTextureRegion;
	public ITextureRegion mShellTextureRegion;
	public TiledTextureRegion mExplosionTextureRegion;
	public BitmapTextureAtlas mBitmapTextureAtlas;
	
	public Music mMusic;
	public Sound mFiringSound;		// for when a tank fires
	public Sound mHitSound;
	
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
		BuildableBitmapTextureAtlas mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(pEngine.getTextureManager(), 512, 512);
		
		
		mRightCornerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "right_corner_tile.png");
		mLeftCornerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "left_corner_tile.png");
		mCenterCornerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "center_corner_tile.png");
		mCenterTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "up_tile.png");
		mRightTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "right_full_tile.png");
		mLeftTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "left_full_tile.png");
		mBlankTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "blank_tile.png");
		mPillarTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "pillar_tile.png");
		
		mTankTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "tank.png");
		mBarrelTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "gun.png");
		mHaloTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "halo.png");
		mShellTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "shell.png");
		
		mBarBGTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "PowerBarBG.png");
		mBarLensTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "PowerBarLens.png");
		mBarLineTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "PowerBarLine.png");
		
		mFireTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pContext, "firebutton.png");
		
		mExplosionTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mBitmapTextureAtlas, pContext, "explosion.jpg", 4, 3);
		
		
		try {
			mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
			mBitmapTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
		
	public synchronized void loadSounds(Engine pEngine, Context pContext)
	{
		SoundFactory.setAssetBasePath("sfx/");
		MusicFactory.setAssetBasePath("sfx/");
		try {
			double choice = Math.random() * 13;
			if(choice < 3)
			{
				mMusic = MusicFactory.createMusicFromAsset(pEngine.getMusicManager(), pContext, "insane.mp3");
			}
			else if(choice < 6)
			{
				mMusic = MusicFactory.createMusicFromAsset(pEngine.getMusicManager(), pContext, "general.mp3");
			}
			else if(choice < 9)
			{
				mMusic = MusicFactory.createMusicFromAsset(pEngine.getMusicManager(), pContext, "villain.mp3");
			}
			else if (choice < 12)
			{
				mMusic = MusicFactory.createMusicFromAsset(pEngine.getMusicManager(), pContext, "fugue.mp3");
			}
			else
			{
				mMusic = MusicFactory.createMusicFromAsset(pEngine.getMusicManager(), pContext, "ra.mp3");
			}
			mMusic.setLooping(true);
		} catch (IOException e)	{
			Debug.e(e);
		}
		
		// Loading sound effects.
		try {
			this.mFiringSound = SoundFactory.createSoundFromAsset(pEngine.getSoundManager(), pContext, "firing-boom-distant.mp3");
			this.mHitSound = SoundFactory.createSoundFromAsset(pEngine.getSoundManager(), pContext, "boom, simple.mp3");
		} catch (final IOException e) {
			Debug.e(e);
		}
	}
	
	public synchronized void unloadSounds()
	{

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
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mPillarTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mTankTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mBarrelTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mCenterCornerTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mBarBGTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mBarLensTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mBarLineTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mFireTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mHaloTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mShellTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();
		mBitmapTextureAtlas = (BuildableBitmapTextureAtlas) mExplosionTextureRegion.getTexture();
		mBitmapTextureAtlas.unload();

		System.gc();
	}
	
}
