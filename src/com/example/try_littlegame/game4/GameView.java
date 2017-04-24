package com.example.try_littlegame.game4;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.try_littlegame.game1.BitmapUtil;
import com.example.try_littlegame.game1.CommonUtil;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

	private SurfaceHolder surfaceHolder;
	private boolean gameFlag = true;
	private ArrayList<Enemy> footboards = new ArrayList<Enemy>();
	private Player player;
	private int playerStartX = 400;
	private int platerStartY = 1000;
	private static final int INIT_SPEEDY = 23;
	private int speedX = 2;
	private int speedY = INIT_SPEEDY;
	private int speedY_offset = -1;
	
	private boolean isPlayerJumping = false;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initFootboard();
		createPlayer();

		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
	}
	
	private void initFootboard(){
		
		wallLeftX = 80;
		wallRightX = wallLeftX + BitmapUtil.wall.getWidth();
		
		
		for(int i=0; i<1; i++){
			createWallLine(wallLeftX, wallRightX, wallY);
		}
	}
	int wallY = CommonUtil.screenHeight;
	int offsetX = BitmapUtil.wall.getWidth();
	int offsetY = 100;
	int wallLeftX, wallRightX;
	
	private void createWallLine(int wallLeftX, int wallRightX, int wallY){
		this.wallY = wallY;
		this.wallLeftX = wallLeftX;
		this.wallRightX = wallRightX;
		if (this.wallLeftX < 80) {
			offsetX = -offsetX;
		} else if (this.wallRightX > CommonUtil.screenWidth - 80
				- BitmapUtil.wall.getWidth()) {
			offsetX = -offsetX;
		}

		this.wallLeftX += offsetX;
		this.wallLeftX = CommonUtil.screenWidth;
		this.wallRightX += offsetX;

		if (this.wallY <= -offsetY)
			return;

		this.wallY -= offsetY;

		System.out.println("wallY" + wallY);
		Log.e("wallY", wallY+"");
		
		Enemy footboard = new Enemy(CommonUtil.screenWidth, platerStartY);

		footboards.add(footboard);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			isPlayerJumping = true;
			player.setPlayerJumping(isPlayerJumping);
		}
		
		return true;
	}

	private void createPlayer() {
		player = new Player(playerStartX, platerStartY);
	}

	private void move() {
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doWallMoveAndCollisionDetectedAndCreateAndRemoveWall();
		player.move(0);
		
	}
	
	private void doWallMoveAndCollisionDetectedAndCreateAndRemoveWall() {
		
		boolean isNeedCreateNewInstance = false;
		boolean isNeedRemoveInstance = false;
		int firstCarPosition = 0;
		int LastCatPosition = footboards.size() - 1;
		Enemy lastLeftWall = null;
		
		for (int wallLinePosition = 0; wallLinePosition < footboards.size(); wallLinePosition++) {

			Enemy wall = footboards.get(wallLinePosition);

					wall.move(-8);
				
					if (wallLinePosition == LastCatPosition) {
						isNeedCreateNewInstance = wall.isNeedCreateNewInstance();
						lastLeftWall = wall;
					}
					if (wallLinePosition == firstCarPosition) {
						isNeedRemoveInstance = wall.isNeedRemoveInstance();
					}
				if (!isCollsionWithTop)
					isCollsionWithTop = isCollisionWithTop(player, wall);
		}
		if (isNeedCreateNewInstance) {
			int wallLeftX = lastLeftWall.getX();
			int wallRightX = wallLeftX + BitmapUtil.wall.getWidth();
			int wallY = lastLeftWall.getY();
			createWallLine(wallLeftX, wallRightX, wallY);
		}
		if (isNeedRemoveInstance) {
			footboards.remove(footboards.get(firstCarPosition));
		}
		
		if(!isCollsionWithTop){		
//			speedY += speedY_offset;
			
		}else{
//			speedY = INIT_SPEEDY;
			isCollsionWithTop = false;
			isLastPositionXUpperForEnemy = true;
			player.jumpByCollission();
		}

//		speedY += speedY_offset;
//		gameFlag = !isCollision;
	}
	boolean isLastPositionXUpperForEnemy = true;
	boolean isCollisionx = false;
	boolean isCollsionWithTop = false;
	private boolean isCollisionWithTop(Player player, Enemy footboard) {
		boolean isCollsionWithTop = false;
		Rect rectPlayer = player.getRect();
		Rect rectFootboard = footboard.getRect();
		
		isLastPositionXUpperForEnemy = footboard.isLastPositionXUpperForEnemy();
		
		if(rectPlayer.bottom <= rectFootboard.top){
			footboard.setLastPositionXUpperForEnemy(true);
		}else{
			footboard.setLastPositionXUpperForEnemy(false);
		}
		
		if(rectPlayer.right >= rectFootboard.left && rectPlayer.left <= rectFootboard.right && rectPlayer.bottom >= rectFootboard.top && rectPlayer.bottom <= rectFootboard.bottom){
			if(isLastPositionXUpperForEnemy){
				isCollsionWithTop = true;
				footboard.setCollision(isCollsionWithTop);
			}else {
//				isCollisionx = true;
			}
		}
		
		return isCollsionWithTop;
	}

	
	private void draw(){
		Canvas canvas = surfaceHolder.lockCanvas();
		canvas.drawColor(Color.WHITE);
		
		player.draw(canvas);

			for (Enemy wall : footboards) {
				wall.draw(canvas);
			}
		
		surfaceHolder.unlockCanvasAndPost(canvas);
	}

	Thread gameThread = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (gameFlag) {
				move();
				draw();
			}
		}
	});
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		gameThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
}
