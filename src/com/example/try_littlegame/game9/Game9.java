package com.example.try_littlegame.game9;



import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Game9 extends Activity {
	private LinearLayout mLayout;
	private GameView mGameView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		GameView gameView = new GameView(this);
		setContentView(gameView);
	}

}
