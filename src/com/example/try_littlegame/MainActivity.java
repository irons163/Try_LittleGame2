package com.example.try_littlegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.try_littlegame.game1.BitmapUtil;
import com.example.try_littlegame.game1.CommonUtil;
import com.example.try_littlegame.game1.Game1;
import com.example.try_littlegame.game10.Game10;
import com.example.try_littlegame.game11.Game11;
import com.example.try_littlegame.game4.Game4;
import com.example.try_littlegame.game6.Game6;
import com.example.try_littlegame.game9.Game9;

public class MainActivity extends Activity {
	Button button,button2,button3,button4,button5,button6,button7,button8,button9;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        CommonUtil.screenWidth = dm.widthPixels;
        CommonUtil.screenHeight = dm.heightPixels;
        
        BitmapUtil.initBitmap(this);
        
		button=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		button3=(Button)findViewById(R.id.button3);
		button4=(Button)findViewById(R.id.button4);
		button5=(Button)findViewById(R.id.button5);
		button6=(Button)findViewById(R.id.button6);
		button7=(Button)findViewById(R.id.button7);
		button8=(Button)findViewById(R.id.button8);
		
		button.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Game1.class);
				startActivity(intent);
			}
		});
		
		button2.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Game2.class);
				startActivity(intent);
			}
		});
		
		button3.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Game11.class);
				startActivity(intent);
			}
		});
		
		button4.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Game10.class);
				startActivity(intent);
			}
		});
		
		button5.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Game9.class);
				startActivity(intent);
			}
		});
		
		button6.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Game3.class);
				startActivity(intent);
			}
		});
		
		button7.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Game4.class);
				startActivity(intent);
			}
		});
		
		button8.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Game6.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
