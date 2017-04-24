package com.example.try_littlegame.game11;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.try_littlegame.game1.BitmapUtil;

public class Ground {
	private int x, y;
	private Rect rectPlayer;

	public Ground(int x, int y, int speedR) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.speedR = speedR;
		rectPlayer = new Rect(x, y, x + BitmapUtil.rotationGround.getWidth(), y
				+ BitmapUtil.rotationGround.getHeight());
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
		canvas.translate( x + BitmapUtil.rotationGround.getWidth()/2, y + BitmapUtil.rotationGround.getHeight()/2);
		canvas.rotate(r+=speedR);

//		Paint paint = new Paint();
//		paint.setColor(startTrianglebarColor);
//		paint.setStyle(Paint.Style.FILL);
//		Path path = new Path();
//		path.moveTo(0f, 40f);
//		path.lineTo(0f - 30f, 90f);
//		path.lineTo(0f + 30f, 90f);
//		path.close();
//		canvas.drawPath(path, paint);
		canvas.drawBitmap(BitmapUtil.rotationGround, -BitmapUtil.rotationGround.getWidth()/2, -BitmapUtil.rotationGround.getHeight()/2, null);
		canvas.restore();
	}

	public void move(int speedX){
		x += speedX;
		rectPlayer.left = x;
		rectPlayer.right = x + BitmapUtil.rotationGround.getWidth();
	}
	
	public int getR(){
		return r;
	}
	
	public void rotation(){
		
	}
}
