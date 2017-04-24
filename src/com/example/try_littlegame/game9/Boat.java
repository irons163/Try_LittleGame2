package com.example.try_littlegame.game9;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.try_littlegame.game1.BitmapUtil;

public class Boat {
	private int x, y;
	private Rect rectPlayer;
	private boolean isRightMove = true;

	public boolean isRightMove() {
		return isRightMove;
	}

	public void setRightMove(boolean isRightMove) {
		this.isRightMove = isRightMove;
	}

	public Boat(int x, int y, int speedR) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.speedR = speedR;
		rectPlayer = new Rect(x, y, x + BitmapUtil.boat.getWidth(), y
				+ BitmapUtil.boat.getHeight());
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

	int speedR;
	int r = 0;
	
	public void draw(Canvas canvas) {
//		canvas.drawBitmap(BitmapUtil.player, x, y, null);
		
		canvas.save();
		canvas.translate( x + BitmapUtil.boat.getWidth()/2, y + BitmapUtil.boat.getHeight()/2);
		if(isRightMove){
			canvas.rotate(r-=speedR);
		}else{
			canvas.rotate(r+=speedR);
		}
		

//		Paint paint = new Paint();
//		paint.setColor(startTrianglebarColor);
//		paint.setStyle(Paint.Style.FILL);
//		Path path = new Path();
//		path.moveTo(0f, 40f);
//		path.lineTo(0f - 30f, 90f);
//		path.lineTo(0f + 30f, 90f);
//		path.close();
//		canvas.drawPath(path, paint);
		canvas.drawBitmap(BitmapUtil.boat, -BitmapUtil.boat.getWidth()/2, -BitmapUtil.boat.getHeight()/2, null);
		canvas.restore();
	}

	public void move(int speedX){
		x += speedX;
		rectPlayer.left = x;
		rectPlayer.right = x + BitmapUtil.boat.getWidth();
	}
	
	public int getR(){
		return r;
	}
	
	public void rotation(){
		
	}
}
