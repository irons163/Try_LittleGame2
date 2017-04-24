package com.example.try_littlegame.game1;

import com.example.try_littlegame.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class BitmapUtil {
	static Context context;
	
	public static Bitmap gameBg;
	public static Bitmap wall;
	public static Bitmap player;
	public static Bitmap rotationGround;
	public static Bitmap boat;
	
	public static Bitmap redPoint;
	public static Bitmap yellowPoint;
	
	public static Bitmap hamster;
	
	public static Bitmap bg;
	public static Bitmap flower;
	public static Bitmap fireball;
	public static Bitmap cloud1;
	public static Bitmap cloud2;
	public static Bitmap cloud3;
	
	public static Bitmap restartBtn01;
	public static Bitmap restartBtn02;
	public static Bitmap gameover;
	
	public static void initBitmap(Context context){
		BitmapUtil.context = context;
		initBitmap();
	}
	
	private static void initBitmap(){
		wall = BitmapFactory.decodeResource(context.getResources(), R.drawable.bubble_1, null);
		player = BitmapFactory.decodeResource(context.getResources(), R.drawable.bubble_2, null);
		rotationGround = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball, null);
		boat = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher, null);
		
		redPoint = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.red_point);
		yellowPoint = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.yellow_point);
		
		hamster = BitmapUtil.createSpecificSizeBitmap(
				context.getResources().getDrawable(
						R.drawable.hamster), 150*7, 150*2);
		
		bg = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.bgmainmenu_hd), CommonUtil.screenWidth, CommonUtil.screenHeight);
		
		flower = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.bgfood_hd), CommonUtil.screenWidth, (int) (CommonUtil.screenHeight/4.0f));
		
		fireball = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.fireball), 150, 200);
		
		cloud1 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.c1_hd), 250, 150);
		cloud2 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.c2_hd), 300, 200);
		cloud3 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.c3_hd), 350, 150);
		
		restartBtn01 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.game_restart_btn01), 350, 200);
		restartBtn02 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.game_restart_btn02), 350, 200);
		
		gameover = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.game_over), CommonUtil.screenWidth, (int) (CommonUtil.screenWidth/6.0f));
	}
	
	public static Bitmap createSpecificSizeBitmap(Drawable drawable, int width,
			int height) {
		// �s�ؤ@��bitmap�A���e20�A�ϥ�ARGB_8888�]�w�A��bitmap�{�b�ť�bitmap���Dnull�C
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap); // �s�صe���A�Ϊť�bitmap��e��
		drawable.setBounds(0, 0, width, height);// �]�wdrawable�����(��Ϥ����ۤv�����e)
		drawable.draw(canvas); // �b�e���W�e�W��drawable(����bitmap�w�g�Q�e�W�F��A���O�ťդF)
		return bitmap;
	}
}
