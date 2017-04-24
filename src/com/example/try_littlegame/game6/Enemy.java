package com.example.try_littlegame.game6;

import com.example.try_littlegame.game1.BitmapUtil;
import com.example.try_littlegame.game1.CommonUtil;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Enemy {
	private int x, y;
	private Rect rectPlayer;
	private boolean isCollision = false;

	public Enemy(int x, int y) {
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

	public Rect getRect() {
		return rectPlayer;
	}

	public void draw(Canvas canvas) {
		if (isCollision)
			removeAnimation(canvas);
		else
			canvas.drawBitmap(BitmapUtil.player, x, y, null);
	}

	public void move(int speedX) {
		x += speedX;
		rectPlayer.left = x;
		rectPlayer.right = x + BitmapUtil.player.getWidth();
	}

	private void moveAnimaiton() {

	}

	public static final int CAR_DISTANCE = 100;

	// private boolean isCarStartFromLeft = true;

	public boolean isNeedCreateNewInstance() {
		boolean isNeedCreateNewInstance = false;
		if (x <= CommonUtil.screenWidth - BitmapUtil.player.getWidth()
				- CAR_DISTANCE) {
			isNeedCreateNewInstance = true;
		}
		return isNeedCreateNewInstance;
	}

	public boolean isNeedRemoveInstance() {
		boolean isNeedRemoveInstance = false;
		if (x <= 0) {
			isNeedRemoveInstance = true;
		}
		return isNeedRemoveInstance;
	}

	int getTop() {
		// TODO Auto-generated method stub
		return rectPlayer.top;
	}

	public boolean isCollision() {
		return isCollision;
	}

	public void setCollision(boolean isCollision) {
		this.isCollision = isCollision;
	}

	private int alpha = 255;
	private int removeAnimationSprikeTimes = 0;
	private final int MAX_REMOVE_SPRIKE_TIMES = 3;
	
	private void removeAnimation(Canvas canvas) {
		Paint paint = new Paint();
		if(removeAnimationSprikeTimes >= MAX_REMOVE_SPRIKE_TIMES){		
			return;
		}
		
		if(alpha<=0){
			removeAnimationSprikeTimes++;
			alpha = 255;
		}else{
			alpha-=85;
		
		}
		paint.setAlpha(alpha);
		canvas.drawBitmap(BitmapUtil.player, x, y, paint);
	}
	
	private boolean isLastPositionXUpperForEnemy = false;

	public boolean isLastPositionXUpperForEnemy() {
		return isLastPositionXUpperForEnemy;
	}

	public void setLastPositionXUpperForEnemy(boolean isLastPositionXUpperForEnemy) {
		this.isLastPositionXUpperForEnemy = isLastPositionXUpperForEnemy;
	}
	
	
}
