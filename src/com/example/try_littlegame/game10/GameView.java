package com.example.try_littlegame.game10;

import java.util.ArrayList;

import com.example.try_littlegame.game1.BitmapUtil;
import com.example.try_littlegame.game1.CommonUtil;
import com.example.try_littlegame.game1.Player;
import com.example.try_littlegame.game1.Wall;
import com.example.try_littlegame.game11.Ground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

	private SurfaceHolder surfaceHolder;
	private boolean gameFlag = true;
	private ArrayList<Footboard> footboards = new ArrayList<Footboard>();
	private Player player;
	private int playerStartX = 400;
	private int platerStartY = 1000;
	private static final int INIT_SPEEDY = 23;
	private int speedX = 2;
	private int speedY = INIT_SPEEDY;
	private int speedY_offset = -1;
	
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
		
		
		for(int i=0; i<45; i++){
			createWallLine(wallLeftX, wallRightX, wallY);
		}
	}
	int wallY = CommonUtil.screenHeight;
	int offsetX = BitmapUtil.wall.getWidth();
	int offsetY = BitmapUtil.wall.getHeight()/3;
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
		this.wallRightX += offsetX;

		if (this.wallY <= -offsetY)
			return;

		this.wallY -= offsetY;

		System.out.println("wallY" + wallY);
		Log.e("wallY", wallY+"");
		
//		Wall wallLeft = new Wall(wallLeftX, wallY);
//		Wall wallRight = new Wall(wallRightX, wallY);
		
		Footboard footboard = new Footboard(this.wallLeftX, this.wallY);

//		ArrayList<Wall> wallLine = new ArrayList<Wall>();
//		wallLine.add(wallLeft);
//		wallLine.add(wallRight);
		footboards.add(footboard);
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
	}
	
	private void doWallMoveAndCollisionDetectedAndCreateAndRemoveWall() {
		
		boolean isNeedCreateNewInstance = false;
		boolean isNeedRemoveInstance = false;
		int firstCarPosition = 0;
		int LastCatPosition = footboards.size() - 1;
		Footboard lastLeftWall = null;
//		boolean isChecked = false;
		
//		if(!isCollsionWithTop && speedY <= 0 && isCollisionx){
//			isCollision = false;
//		}
		
		for (int wallLinePosition = 0; wallLinePosition < footboards.size(); wallLinePosition++) {
			
//			for (Wall wall : walls.get(wallLinePosition)) {

			Footboard wall = footboards.get(wallLinePosition);

					wall.move(speedY);
				
//				if(!isChecked){
//					isChecked = true;
					if (wallLinePosition == LastCatPosition) {
						isNeedCreateNewInstance = wall.isNeedCreateNewInstance();
						lastLeftWall = wall;
					}
					if (wallLinePosition == firstCarPosition) {
						isNeedRemoveInstance = wall.isNeedRemoveInstance();
					}
//				}
				if (!isCollsionWithTop)
					isCollsionWithTop = isCollisionWithTop(player, wall);
//			}

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
			speedY += speedY_offset;
		}else{
			speedY = INIT_SPEEDY;
			isCollsionWithTop = false;
			isCollision = true;
		}

//		speedY += speedY_offset;
//		gameFlag = !isCollision;
	}
	boolean isCollision = true;
	boolean isCollisionx = false;
	boolean isCollsionWithTop = false;
	private boolean isCollisionWithTop(Player player, Footboard footboard) {
		boolean isCollsionWithTop = false;
		Rect rectPlayer = player.getRect();
		Rect rectFootboard = footboard.getRect();
		
		isCollision = footboard.isCollision();
		
		if(rectPlayer.bottom <= rectFootboard.top){
			footboard.setCollision(false);
		}else{
			footboard.setCollision(true);
		}
		
		if(rectPlayer.right >= rectFootboard.left && rectPlayer.left <= rectFootboard.right && rectPlayer.bottom >= rectFootboard.top && rectPlayer.bottom <= rectFootboard.bottom){
			if(!isCollision && speedY <= 0){
				isCollsionWithTop = true;
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

			for (Footboard wall : footboards) {
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
