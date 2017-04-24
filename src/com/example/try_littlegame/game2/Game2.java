package com.example.try_littlegame.game2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.action.listener.IActionListener;
import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.Layer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.remotecontroller.RemoteController;
import com.example.try_gameengine.remotecontroller.RemoteController.CommandType;
import com.example.try_gameengine.scene.DialogScene;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_gameengine.scene.Scene;
import com.example.try_gameengine.scene.SceneManager;
import com.example.try_gameengine.stage.Stage;
import com.example.try_gameengine.utils.GameTimeUtil;
import com.example.try_littlegame.R;
import com.example.try_littlegame.game1.BitmapUtil;
import com.example.try_littlegame.game1.CommonUtil;

public class Game2 extends Stage{
	SceneManager sceneManager;
	
//	MySurfaceView view;
	Thread mainLoop;
	
	public class MyScene extends EasyScene{
		private List<Sprite> fireballs = new CopyOnWriteArrayList<Sprite>();
		Sprite player;
		private int move = 0;
		public static final int LEFT = 1;
		public static final int RIGHT = 2;
		boolean isPressLeftMoveBtn, isPressRightMoveBtn;
		private int gameTime;
		
		private GameTimeUtil fireballTimeUtil;
		private GameTimeUtil gameTimeUtil;

		private static final int INVALID_POINTER_ID = -1;

		// The ��active pointer�� is the one currently moving our object.
		private int mActivePointerId = INVALID_POINTER_ID;
		
		Layer background, background2;
		List<Sprite> monsters = new ArrayList<Sprite>();
		
		public MyScene(final Context context, String id, int level, int mode) {
			super(context, id, level, mode);
			// TODO Auto-generated constructor stub
			isEnableRemoteController(false);
			
			
//			getRemoteController().setUpKyPosition(CommonUtil.screenWidth - getRemoteController().getUpKey().w, CommonUtil.screenHeight - getRemoteController().getUpKey().h);
//			getRemoteController().setLeftKyPosition(0, CommonUtil.screenHeight - getRemoteController().getUpKey().h);
//			getRemoteController().setRemoteContollerListener(new RemoteController.RemoteContollerListener() {
//				
//				@Override
//				public void pressDown(
//						List<com.example.try_gameengine.remotecontroller.RemoteController.CommandType> commandTypes) {
//					// TODO Auto-generated method stub
//					for(CommandType commandType : commandTypes){
//						switch (commandType) {
//						case UPKeyUpCommand:
//							isPressRightMoveBtn = false;
//							move = LEFT;
//							
//							if(!isPressLeftMoveBtn && !isPressRightMoveBtn){
//								player.setXscale(-1.0f);
//								player.runActionFPSFrame(null, new int[]{11,10,9}, new int[]{8,8,8}, false, new com.example.try_gameengine.framework.IActionListener() {
//									
//									@Override
//									public void beforeChangeFrame(int nextFrameId) {
//										// TODO Auto-generated method stub
//										
//									}
//									
//									@Override
//									public void afterChangeFrame(int periousFrameId) {
//										// TODO Auto-generated method stub
//										
//									}
//									
//									@Override
//									public void actionFinish() {
//										// TODO Auto-generated method stub
//										isMoveing = false;
//									}
//								});
//								
//								move = 0;
//								player.currentAction = null;
//								isMoveing = false;
//							}
//							
//
//							break;
//						case UPKeyDownCommand:
////							for(List<ALayer> layers : LayerManager.getLayerLevelList()){
////								for(ALayer layer : layers){
////									if(layer instanceof Sprite){
////										((Sprite)layer).move(30, 0);
////									}
////								}
////							}
//							isPressRightMoveBtn = true;
//							move = RIGHT;
//							
//							final Layer bgLayer = new Layer(BitmapUtil.gameover, BitmapUtil.gameover.getWidth(), BitmapUtil.gameover.getHeight(), false);
//							bgLayer.setPosition(0, bgLayer.h);
////							final Sprite restartButton = new Sprite(BitmapUtil.restartBtn01, 350, 200, false);
//							final ButtonLayer restartButton = new ButtonLayer(0, 0, false);
//							restartButton.setBitmapAndAutoChangeWH(BitmapUtil.restartBtn01);
//							restartButton.setButtonBitmap(BitmapUtil.restartBtn01, BitmapUtil.restartBtn02, BitmapUtil.restartBtn01);
//							restartButton.setPosition(CommonUtil.screenWidth/2.0f - restartButton.w/2.0f, CommonUtil.screenHeight/4.0f*3);
//							restartButton.setOnClickListener(new ButtonLayer.OnClickListener() {
//								
//								@Override
//								public void onClick(ButtonLayer buttonLayer) {
//									// TODO Auto-generated method stub
//									((Game2)context).sceneManager.previous();
//								}
//							});
//							final LabelLayer labelLayer = new LabelLayer("hello", 0, 0, false);
//							labelLayer.setTextSize(100);
//							labelLayer.setPosition(500, 500);
//							final DialogScene dialogScene = new DialogScene(context, "c");
//							dialogScene.setDialogSceneDraw(new DialogScene.DialogSceneDrawListener() {
//								
//								@Override
//								public void draw(Canvas canvas) {
//									// TODO Auto-generated method stub
//					//				canvas.drawColor(Color.RED);
//									canvas.drawColor(Color.TRANSPARENT,Mode.CLEAR);
//									Paint paint = new Paint();
//									paint.setColor(Color.RED);
//									canvas.drawRect(new Rect(100, 100, 300, 300), paint);
//									paint.setColor(Color.BLACK);
//									paint.setAlpha(150);
//									canvas.drawRect(new RectF(0, 0, CommonUtil.screenWidth, CommonUtil.screenHeight ), paint);
//									
//									bgLayer.drawSelf(canvas, null);
//									
//									restartButton.drawSelf(canvas, null);
//									
//									labelLayer.drawSelf(canvas, null);
//								}
//							});
//							dialogScene.setDialogSceneTouchListener(new DialogScene.DialogSceneTouchListener() {
//								
//								@Override
//								public void onTouchEvent(MotionEvent event) {
//									// TODO Auto-generated method stub
//									float x = event.getX();
//									float y = event.getY();
//									
//									LayerManager.onTouchSceneLayers(event, dialogScene.getLayerLevel());
//									
//
//								}
//							});
//							dialogScene.setIsNeedToStopTheActiveScene(false);
//							((Game2)context).sceneManager.addScene(dialogScene);
//							((Game2)context).sceneManager.startLastScene();
//							
//							dialogScene.addAutoDraw(restartButton);
//					//		dialogScene.start();
//							
//							break;
//						case LeftKeyDownCommand:
//							isPressLeftMoveBtn = true;
//							move = LEFT;
//							
//							
//							
//							break;
//						case LeftKeyUpCommand:
//							isPressLeftMoveBtn = false;
//							move = RIGHT;
//					
//							if(!isPressLeftMoveBtn && !isPressRightMoveBtn){
//								player.setXscale(1.0f);
//								player.runActionFPSFrame(null, new int[]{11,10,9}, new int[]{8,8,8}, false, new com.example.try_gameengine.framework.IActionListener() {
//									
//									@Override
//									public void beforeChangeFrame(int nextFrameId) {
//										// TODO Auto-generated method stub
//										
//									}
//									
//									@Override
//									public void afterChangeFrame(int periousFrameId) {
//										// TODO Auto-generated method stub
//										
//									}
//									
//									@Override
//									public void actionFinish() {
//										// TODO Auto-generated method stub
//										isMoveing = false;
//									}
//								});
//								
//								move = 0;
//								player.currentAction = null;
//								isMoveing = false;
//							}
//							break;
//						default:
//							break;
//						}
//					}
//				}
//			});
			
			background = new Layer(BitmapUtil.bg,0, 0, false);
			background2 = new Layer(BitmapUtil.bg,background.getWidth(), 0, false); 
			addAutoDraw(background);
			addAutoDraw(background2);
			
//			Sprite bg = new Sprite(BitmapUtil.bg, CommonUtil.screenWidth, CommonUtil.screenHeight, false);
//			addAutoDraw(bg);
			Sprite flower = new Sprite(BitmapUtil.flower, BitmapUtil.flower.getWidth(), BitmapUtil.flower.getHeight(), false);
			addAutoDraw(flower);
			flower.setPosition(0, CommonUtil.screenHeight - flower.getHeight()*1.5f);
			Sprite cloud1 = new Sprite(BitmapUtil.cloud1, BitmapUtil.cloud1.getWidth(), BitmapUtil.cloud1.getHeight(), false);
			addAutoDraw(cloud1);
			cloud1.setPosition(CommonUtil.screenWidth/2.0f - cloud1.getWidth()/2.0f, 0);
			Sprite cloud2 = new Sprite(BitmapUtil.cloud2, BitmapUtil.cloud2.getWidth(), BitmapUtil.cloud2.getHeight(), false);
			addAutoDraw(cloud2);
			cloud2.setPosition(0, 0);
			Sprite cloud3 = new Sprite(BitmapUtil.cloud3, BitmapUtil.cloud3.getWidth(), BitmapUtil.cloud3.getHeight(), false);
			addAutoDraw(cloud3);
			cloud3.setPosition(CommonUtil.screenWidth - cloud1.getWidth(), 0);
			
			player = new Sprite(BitmapUtil.yellowPoint, 100, 1000, false);
			player.setBitmapAndFrameWH(BitmapUtil.hamster, 150, 150);
			player.setCollisionRectFEnable(true);
			player.setPosition(CommonUtil.screenWidth/2.0f - player.getWidth()/2.0f, player.getHeight());
			player.setCollisionOffsetXY(50, 100);
			player.setCollisionRectFWH(100, 100);
			
//			sprite.setAction(actionName);
			
//			MovementAction playerMoveAciton = MAction.moveTo(100, CommonUtil.screenHeight, 1000);
//			player.runMovementAction(fireballDownAciton);
		}

		GameView gameview;
		
		@Override
		public GameView initGameView(Activity activity, IGameController gameController,
				IGameModel gameModel) {
			// TODO Auto-generated method stub
			
			class MyGameView extends GameView{

				public MyGameView(Context context, IGameController gameController,
						IGameModel gameModel) {
					super(context, gameController, gameModel);
					// TODO Auto-generated constructor stub
				}
				
			}
			
			return gameview = new MyGameView(activity, gameController, gameModel);
		}

		@Override
		public void process() {
			// TODO Auto-generated method stub
			checkCollision();
			
			for(Sprite fireball : fireballs){
				fireball.frameTrig();
			}
			
			checkPlayerMoved();
			
			tickTime();
		}
		
		private void tickTime(){
			if(gameTimeUtil.isArriveExecuteTime()){
				gameTime++;
			}
		}
		
		private void checkCollision(){
			for(Sprite fireball : fireballs){
				if(fireball.getDst().intersect(player.getCollisionRectF())){
					fireball.getMovementAction().controller.cancelAllMove();
					fireballs.remove(fireball);
				}
			}
		}
		
		private void checkMonsterMoved(){
			if(move == LEFT){
				for(Sprite monster : monsters){
					monster.move(5.0f, 0);
				}
			}else{
				
			}
		}
		
		private void checkBackgroundMoved(){
			background.setX(background.getX()-5);
			background2.setX(background2.getX()-5);
			
			if(background2.getX() < 0){
				background.setX(background2.getX());
				background2.setX(background.getX() + background.getWidth());
			}
		}
		
		private void checkOtherThingOnSceneMoved(){
			
		}

		boolean isMoveing = false;
		private void checkPlayerMoved(){
			 
			if(move == LEFT){
				if(player.getX() + 5 < 600 ){
					player.move(5, 0);
				}else{
					player.setX(600);
				}
				
				player.setXscale(1.0f);
				if(!isMoveing){
					isMoveing = true;
					player.runActionFPSFrame(null, new int[]{12,12,13}, new int[]{0,10,10}, false, new com.example.try_gameengine.framework.IActionListener() {
						
						@Override
						public void beforeChangeFrame(int nextFrameId) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void afterChangeFrame(int periousFrameId) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void actionFinish() {
							// TODO Auto-generated method stub
							isMoveing = false;
						}
					});
				}
			}else if(move == RIGHT){
				player.move(5, 0);
				player.setXscale(-1.0f);
				if(!isMoveing){
					isMoveing = true;
					player.runActionFPSFrame(null, new int[]{12,12,13}, new int[]{0,10,10}, false, new com.example.try_gameengine.framework.IActionListener() {
						
						@Override
						public void beforeChangeFrame(int nextFrameId) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void afterChangeFrame(int periousFrameId) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void actionFinish() {
							// TODO Auto-generated method stub
							isMoveing = false;
						}
					});
				}
			}
			player.frameTrig();
//			MovementAction playerMoveAciton = MAction.moveTo(100, CommonUtil.screenHeight, 1000);
//			player.runMovementAction(fireballDownAciton);
		}

		@Override
		public void doDraw(Canvas canvas) {
			// TODO Auto-generated method stub
//			sprite.drawSelf(canvas, null);
//			LayerManager.drawLayers(canvas, null);
			LayerManager.getInstance().drawSceneLayers(canvas, null, sceneLayerLevel);
			
			Paint paint = new Paint();
			paint.setTextSize(50);
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			canvas.drawText(gameTime+"", 100, 100, paint);
			player.drawSelf(canvas, null);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			
			
			if(event.getAction()==MotionEvent.ACTION_DOWN){

			}
			return super.onTouchEvent(event);
		}
		
		@Override
		public void beforeGameStart() {
			// TODO Auto-generated method stub
			fireballTimeUtil = new GameTimeUtil(1000);
			gameTimeUtil = new GameTimeUtil(1000);
		}

		@Override
		public void arrangeView(Activity activity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setActivityContentView(Activity activity) {
			// TODO Auto-generated method stub
			activity.setContentView(gameview);
		}

		@Override
		public void afterGameStart() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
			
		}

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
//		view=new MySurfaceView(this);
//		setContentView(view);
		initStage();
	}
	@Override
	public SceneManager initSceneManager() {
		// TODO Auto-generated method stub
		LayerManager.getInstance().setLayerBySenceIndex(0);
		Scene scene = new MyScene(this, "a", 1, Scene.RESUME);
//		LayerManager.setLayerBySenceIndex(1);
//		Scene scene2 = new MyScene2(this, "b", 2, Scene.RESTART);
		
		sceneManager = SceneManager.getInstance();
		sceneManager.addScene(scene);
//		sceneManager.addScene(scene2);
		
		sceneManager.startScene(0);
		
		return sceneManager;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		sceneManager.previousWithExistedScenes();
	}
}
