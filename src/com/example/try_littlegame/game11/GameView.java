package com.example.try_littlegame.game11;

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
	private Ground ground;
	private ArrayList<Ground> grounds = new ArrayList<Ground>();
	private Player player;
	private boolean isPlayerJumping = false;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		player = new Player(120, 120);
		
		ground = new Ground(100, 100, 1);
		grounds.add(ground);
		player.setStandGround(ground);
		ground = new Ground(100 + BitmapUtil.rotationGround.getWidth(), 100, -1);
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
		}
		
		return true;
	}
	
	private void move() {
		if(isPlayerJumping){
			
		}
	}
	
	private void draw(){
		Canvas canvas = surfaceHolder.lockCanvas();
		canvas.drawColor(Color.WHITE);
		
		for(Ground ground : grounds){
			ground.draw(canvas);
		}
		
		player.draw(canvas);
		
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
