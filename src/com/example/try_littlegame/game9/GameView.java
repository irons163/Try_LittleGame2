package com.example.try_littlegame.game9;

import java.util.ArrayList;

import com.example.try_littlegame.game1.BitmapUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	private SurfaceHolder surfaceHolder;
	private boolean gameFlag = true;
	private Boat ground;
	private ArrayList<Boat> grounds = new ArrayList<Boat>();
	private Player player;
	private boolean isPlayerJumping = false;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		player = new Player(120, 120);
		
//		ground = new Boat(100, 100, 1);
//		grounds.add(ground);
		
		ground = new Boat(100 + BitmapUtil.rotationGround.getWidth(), 100, -1);
		player.setStandGround(ground);
		grounds.add(ground);
		
		
		
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			isPlayerJumping = true;
			player.setStandOnRotationGround(isPlayerJumping);
			
			ground.setRightMove(!ground.isRightMove());
		}
		
		return true;
	}
	
	private int speedX = 1;
	
	private void move() {
		if(isPlayerJumping){
			player.move(speedX);
		}
	}
	
	private void draw(){
		Canvas canvas = surfaceHolder.lockCanvas();
		canvas.drawColor(Color.WHITE);
		
//		for(Boat ground : grounds){
//			ground.draw(canvas);
//		}
		
		player.draw(canvas);
		
		ground.draw(canvas);
		
		
		
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
