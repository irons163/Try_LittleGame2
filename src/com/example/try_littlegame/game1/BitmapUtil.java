package com.example.try_littlegame.game1;

import com.example.try_littlegame.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {
	static Context context;
	
	public static Bitmap gameBg;
	public static Bitmap wall;
	public static Bitmap player;
	public static Bitmap rotationGround;
	public static Bitmap boat;
	
	public static void initBitmap(Context context){
		BitmapUtil.context = context;
		initBitmap();
	}
	
	private static void initBitmap(){
		wall = BitmapFactory.decodeResource(context.getResources(), R.drawable.bubble_1, null);
		player = BitmapFactory.decodeResource(context.getResources(), R.drawable.bubble_2, null);
		rotationGround = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball, null);
		boat = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher, null);
	}
}
