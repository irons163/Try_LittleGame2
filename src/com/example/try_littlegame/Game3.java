package com.example.try_littlegame;

import com.example.try_littlegame.MyGameView;
import com.example.try_littlegame.R;
import com.example.try_littlegame.R.id;
import com.example.try_littlegame.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Game3 extends Activity {
	private LinearLayout mLayout;
	private MyGameView mGameView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ���ê��A�C */
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/* ���ü��D�C */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/* �M��main.xml */
		setContentView(R.layout.main_game3);
		/* ���o�ù��e���Pdensity */
		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		float density = dm.density;

		/* �[�J�۩w�q��MyGameView */
		mLayout = (LinearLayout) this.findViewById(R.id.layout1);
		mGameView = new MyGameView(Game3.this, width, height, density);
		mLayout.addView(mGameView);
	}

}
