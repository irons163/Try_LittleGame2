package com.example.try_littlegame.game3;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.try_littlegame.game1.BitmapUtil;

public class Player {
	private int x, y;
	private Rect rectPlayer;

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
		canvas.drawBitmap(BitmapUtil.player, x, y, null);
	}

	public void move(int speedX){
		x += speedX;
		rectPlayer.left = x;
		rectPlayer.right = x + BitmapUtil.player.getWidth();
	}
	
	private void moveAnimaiton() {

	}
}
