package com.example.try_littlegame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Game2 extends Activity{
	MySurfaceView view;
	Thread mainLoop;
	
	class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable{
		Bitmap bitmap;
		Paint paint = new Paint();
		int tx, ty, right, below;
		long time;
		boolean gameFlag = true;
		SurfaceHolder surfaceHolder;
		
		public MySurfaceView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			setFocusable(true);
			requestFocus();
			bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
			paint.setColor(Color.BLUE);
			paint.setTextSize(12);
			tx = bitmap.getWidth();
			ty =10;
			
			surfaceHolder = getHolder();
			surfaceHolder.addCallback(this);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(gameFlag){
				doDraw();
			}
			
		}
		
		void doDraw(){
			Canvas canvas = getHolder().lockCanvas();
			if(canvas !=null){
				canvas.drawColor(Color.WHITE);
				right++;
				canvas.drawBitmap(bitmap, right*10, below*10, null);
				long now = System.currentTimeMillis();
				Log.d("T", now-time+"");
				canvas.drawText(""+1000f/(now-time)+"fps", tx, ty, paint);
				time=now;
				getHolder().unlockCanvasAndPost(canvas);
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			mainLoop = new Thread(this);
			mainLoop.start();
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		view=new MySurfaceView(this);
		setContentView(view);
	}
}
