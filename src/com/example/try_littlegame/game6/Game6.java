package com.example.try_littlegame.game6;

import android.app.Activity;
import android.os.Bundle;

public class Game6 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		GameView gameView = new GameView(this);
		setContentView(gameView);
	}
}
