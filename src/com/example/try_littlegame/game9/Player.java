package com.example.try_littlegame.game9;

import com.example.try_littlegame.game1.BitmapUtil;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Player {
	private int x, y;
	private Rect rectPlayer;
	private boolean isStandOnRotationGround = true;
	private 
	Boat ground;

	public Player(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		rectPlayer = new Rect(x, y, x + BitmapUtil.player.getWidth(), y
				+ BitmapUtil.player.getHeight());
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
		if(isStandOnRotationGround){
			Matrix matrix = new Matrix();
			
			matrix.mapPoints(new float[10]);
			int r = ground.getR();
			System.out.println("r"+r);
			boolean isRightMove = ground.isRightMove();
			canvas.save();
			int rotationX = x + BitmapUtil.player.getWidth()/2;
			int rotationY = y + BitmapUtil.player.getHeight()/2;
			canvas.translate(rotationX , rotationY);
//			canvas.rotate(r);
			if(isRightMove){
				canvas.rotate(r);
			}else{
				canvas.rotate(r);
			}
//			canvas.restore();
			canvas.drawBitmap(BitmapUtil.player, x - rotationX, y -rotationY, null);
			canvas.restore();
			
			
		}else{
			canvas.drawBitmap(BitmapUtil.player, x, y, null);
		}
		
	}

	public void move(int speedX){
		x += speedX;
		rectPlayer.left = x;
		rectPlayer.right = x + BitmapUtil.player.getWidth();
	}
	
	private void moveAnimaiton() {

	}
	
	public boolean isStandOnRotationGround() {
		return isStandOnRotationGround;
	}

	public void setStandOnRotationGround(boolean isStandOnRotationGround) {
		this.isStandOnRotationGround = isStandOnRotationGround;
	}

	public Boat getStandGround() {
		return ground;
	}

	public void setStandGround(Boat ground) {
		this.ground = ground;
	}
	
}
