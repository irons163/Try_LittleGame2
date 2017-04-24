package com.example.try_littlegame.game6;

import com.example.try_littlegame.game1.BitmapUtil;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Player {
	private int initY;
	private int x, y;
	private Rect rectPlayer;
	private boolean isPlayerJumping = false;
	private int jumpingCount = 0;
	private final int JUMPING_LIMIT_ONCE = 2;
	private MathLogic mathLogic;
	private Rope rope;
	
	public Player(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.initY = y;
		rectPlayer = new Rect(x, y, x + BitmapUtil.player.getWidth(), y
				+ BitmapUtil.player.getHeight());
		this.speedY = SPEED_JUMP;
		mathLogic = new MathLogic();
		rope = new Rope(x, GameView.celinY);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rect getRect(){
		return rectPlayer;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(BitmapUtil.player, x, y, null);
	}

	public void move(int speedX){
		
		if(isPlayerJumping){
		x += mathLogic.getSpeedX();
		y += mathLogic.getSpeedY();
		
		rope.setTailX(x);
		rope.setTailY(y);
		
		mathLogic.genAngle(x, y, rope.getX(), rope.getY());
		mathLogic.genMoveAngle();
		mathLogic.genSpeed();
		
//		x += speedX;
		rectPlayer.left = x;
		rectPlayer.right = x + BitmapUtil.player.getWidth();
		
		
//		speedY -= speedG;
		
//		y -= speedY;
		rectPlayer.top = y;
		rectPlayer.bottom = y + BitmapUtil.player.getHeight();
//		if(y >= initY){
//			isPlayerJumping = false;
//			jumpingCount = 0;
//			y = initY;
//		}
		}
	}
	
	public void jumpByCollission(){
		this.speedY = SPEED_JUMP;
		this.isPlayerJumping = true;
	}
	
	private static final int SPEED_JUMP = 30;
	private int speedY;
	private int speedG = 2;
	
//	public void setSpeedY(int speedY){
//		this.speedY = speedY;
//	}
	
	private void moveAnimaiton() {

	}

	public boolean isPlayerJumping() {
		
		return isPlayerJumping;
	}

	public void setPlayerJumping(boolean isPlayerJumping) {
		this.isPlayerJumping = isPlayerJumping;
		if(isPlayerJumping){
			mathLogic.initAngle();
			mathLogic.genMoveAngle();
			shootRope();
		}else{
			
		}
	}
	
	public void shootRope(){
		int distance = y - GameView.celinY;
		int ropeX = x + distance;
		rope.setX(ropeX);
	}
	
	public Rope getRope(){
		return rope;
		
	}
}
