package com.example.try_littlegame.game6;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Rope {
	private int x, y;
	private int tailX, tailY;
	private Paint paint;
	
	public Rope(int x, int y){
		this.y = y;
		this.x = x;
		paint = new Paint();
		paint.setColor(Color.BLUE);
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setTailX(int tailX){
		this.tailX = tailX;
	}
	
	public void setTailY(int tailY){
		this.tailY = tailY;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawLine(x, y, tailX, tailY, paint);
//		canvas.drawBitmap(BitmapUtil.player, x, y, null);
	}
}
