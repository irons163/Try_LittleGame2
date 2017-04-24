package com.example.try_littlegame.game11;

import com.example.try_littlegame.game1.BitmapUtil;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Player {
	private int x, y;
	private Rect rectPlayer;
	private boolean isStandOnRotationGround = true;
	private Ground ground;


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
			int r = ground.getR();
			canvas.save();
			int rotationX = ground.getX() + BitmapUtil.rotationGround.getWidth()/2;
			int rotationY = ground.getY() + BitmapUtil.rotationGround.getHeight()/2;
			canvas.translate(rotationX , rotationY);
			canvas.rotate(r);
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

	public Ground getStandGround() {
		return ground;
	}

	public void setStandGround(Ground ground) {
		this.ground = ground;
	}
	
}
