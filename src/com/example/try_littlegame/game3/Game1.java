package com.example.try_littlegame.game3;

import android.app.Activity;
import android.os.Bundle;

public class Game1 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		GameView gameView = new GameView(this);
		setContentView(gameView);
	}
}
