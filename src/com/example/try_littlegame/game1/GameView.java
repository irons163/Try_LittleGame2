package com.example.try_littlegame.game1;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	private int speedX = 2;
	private int speedY = 3;

	public GameView(Context context) {
		super(context);

		createInitWall();
		createPlayer();

		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
	}

	private boolean gameFlag = true;
	private ArrayList<ArrayList<Wall>> walls = new ArrayList<ArrayList<Wall>>();
	private SurfaceHolder surfaceHolder;
	private Player player;
	private int playerStartX = 400;
	private int platerStartY = 1000;

	int offsetX = BitmapUtil.wall.getWidth();
	int offsetY = BitmapUtil.wall.getHeight();
	
	private int DIRECTION_LEFT = -1;
	private int DIRECTION_RIGHT = 1;
	private int direction = DIRECTION_RIGHT;
	
	private void createInitWall() {
		int wallLeftX = 80, wallRightX = wallLeftX + 600;
		int wallY = CommonUtil.screenHeight;


		for (int i = 0; i < 20; i++) {
			createWallLine(wallLeftX, wallRightX, wallY);
		}

	}
	
	private void createWallLine(int wallLeftX, int wallRightX, int wallY){
		if (wallLeftX < 80) {
			offsetX = -offsetX;
		} else if (wallRightX > CommonUtil.screenWidth - 80
				- BitmapUtil.wall.getWidth()) {
			offsetX = -offsetX;
		}

		wallLeftX += offsetX;
		wallRightX += offsetX;

		if (wallY <= -offsetY)
			return;

		wallY -= offsetY;

		System.out.println("wallY" + wallY);
		
		Wall wallLeft = new Wall(wallLeftX, wallY);
		Wall wallRight = new Wall(wallRightX, wallY);

		ArrayList<Wall> wallLine = new ArrayList<Wall>();
		wallLine.add(wallLeft);
		wallLine.add(wallRight);
		walls.add(wallLine);
	}

	private void createPlayer() {
		player = new Player(playerStartX, platerStartY);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			direction = -direction;
			speedX = -speedX;
		}
		
		return true;
	}

	private void move() {

		player.move(speedX);

		doWallMoveAndCollisionDetectedAndCreateAndRemoveWall();

	}

	private boolean isCollision(Player player, Wall wall) {
		Rect rectPlayer = player.getRect();
		Rect rectWall = wall.getRect();
		return Rect.intersects(rectPlayer, rectWall);
	}

	private void doWallMoveAndCollisionDetectedAndCreateAndRemoveWall() {
		boolean isCollision = false;
		boolean isNeedCreateNewInstance = false;
		boolean isNeedRemoveInstance = false;
		int firstCarPosition = 0;
		int LastCatPosition = walls.size() - 1;
		Wall lastLeftWall = null;
		for (int wallLinePosition = 0; wallLinePosition < walls.size(); wallLinePosition++) {
			boolean isChecked = false;
			for (Wall wall : walls.get(wallLinePosition)) {

				wall.move(speedY);
				if(!isChecked){
					isChecked = true;
					if (wallLinePosition == LastCatPosition) {
						isNeedCreateNewInstance = wall.isNeedCreateNewInstance();
						lastLeftWall = wall;
					}
					if (wallLinePosition == firstCarPosition) {
						isNeedRemoveInstance = wall.isNeedRemoveInstance();
					}
				}
				if (!isCollision)
					isCollision = isCollision(player, wall);
			}

		}
		if (isNeedCreateNewInstance) {
			int wallLeftX = lastLeftWall.getX();
			int wallRightX = wallLeftX + 600;
			int wallY = lastLeftWall.getY();
			createWallLine(wallLeftX, wallRightX, wallY);
		}
		if (isNeedRemoveInstance) {
			walls.remove(walls.get(firstCarPosition));
		}
		gameFlag = !isCollision;
	}

	private void draw() {
		Canvas canvas = surfaceHolder.lockCanvas();
		canvas.drawColor(Color.WHITE);

		player.draw(canvas);

		for (ArrayList<Wall> wallLine : walls) {
			for (Wall wall : wallLine) {
				wall.draw(canvas);
			}
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
