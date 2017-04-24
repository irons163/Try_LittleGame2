package com.example.try_littlegame.game10;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.try_littlegame.game1.BitmapUtil;
import com.example.try_littlegame.game1.CommonUtil;

public class Footboard {
	private int x, y;
	private Rect rectWall;
	private boolean isCollision = false;
	
	public boolean isCollision() {
		return isCollision;
	}

	public void setCollision(boolean isCollision) {
		this.isCollision = isCollision;
	}

	public Footboard(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		rectWall = new Rect(x, y, x + BitmapUtil.wall.getWidth(), y
				+ BitmapUtil.wall.getHeight());
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Rect getRect(){
		return rectWall;
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(BitmapUtil.wall, x, y, null);
	}
	
	public void move(int speedY){
		y += speedY;
		rectWall.top = y;
		rectWall.bottom = y + BitmapUtil.wall.getHeight();
	}
	
	public static final int CAR_DISTANCE = 50;
	
//	private boolean isCarStartFromLeft = true;
	
	public boolean isNeedCreateNewInstance(){
		boolean isNeedCreateNewInstance = false;
		if(y >= BitmapUtil.wall.getHeight()){
			isNeedCreateNewInstance = true;
		}
		return isNeedCreateNewInstance; 
	}

	public boolean isNeedRemoveInstance(){
		boolean isNeedRemoveInstance = false;
		if(y >= CommonUtil.screenHeight){
			isNeedRemoveInstance = true;
		}
		return isNeedRemoveInstance; 
	}

	int getTop() {
		// TODO Auto-generated method stub
		return rectWall.top;
	}
	
}
