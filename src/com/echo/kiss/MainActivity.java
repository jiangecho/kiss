package com.echo.kiss;


import java.util.Random;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	private ImageView[] imageViews;
	private ImageView kissGirlImageView;
	private TextView scoreTextView, timerTextView;
	private TextView kissResultTextView, kissReportTitleTextView, kissReportTextView;
	
	private int score;
	private int lastKissGirlIndex;
	private Random random;
	
	private static final int[] GIRLS_DRAWABLE = {R.drawable.mm0,R.drawable.mm1,R.drawable.mm2,
		R.drawable.mm3,R.drawable.mm4,R.drawable.mm5,
		R.drawable.mm5,R.drawable.mm7, R.drawable.mm8};
	private static final int RUHUA_DRAWABLE = R.drawable.ruhua;
	private static final int BG_DRAWABLE = R.drawable.bg;
	
	private static final String TAG_RUHUA = "ruhua";
	private static final String TAG_GIRL = "girl";
	private static final String TAG_BG = "bg";
	
	private RelativeLayout startLayout, endLayout;
	private LinearLayout kissLayout;
	
	private CountDownTimer gameCountDownTimer;
	private Handler handler;
	
	private Drawable lastDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		startLayout = (RelativeLayout) findViewById(R.id.start_layout);
		endLayout = (RelativeLayout) findViewById(R.id.end_layout);
		kissLayout = (LinearLayout) findViewById(R.id.kiss_layout);
		
		imageViews = new ImageView[9];
		
		imageViews[0] = (ImageView) findViewById(R.id.img0);
		imageViews[1] = (ImageView) findViewById(R.id.img1);
		imageViews[2] = (ImageView) findViewById(R.id.img2);
		imageViews[3] = (ImageView) findViewById(R.id.img3);
		imageViews[4] = (ImageView) findViewById(R.id.img4);
		imageViews[5] = (ImageView) findViewById(R.id.img5);
		imageViews[6] = (ImageView) findViewById(R.id.img6);
		imageViews[7] = (ImageView) findViewById(R.id.img7);
		imageViews[8] = (ImageView) findViewById(R.id.img8);
		
		for (ImageView imageView : imageViews) {
			imageView.setOnClickListener(this);
			imageView.setTag(TAG_BG);
		}
		
		kissGirlImageView = (ImageView) findViewById(R.id.kiss_girl);
		scoreTextView = (TextView) findViewById(R.id.scoreTV);
		timerTextView = (TextView) findViewById(R.id.timerTV);
		
		kissResultTextView = (TextView) findViewById(R.id.kiss_result);
		kissReportTitleTextView = (TextView) findViewById(R.id.kiss_report_title);
		kissReportTextView = (TextView) findViewById(R.id.kiss_report);
		

		random = new Random();
		gameCountDownTimer = new GameCountDownTimer(30 * 1000, 1000);
		handler = new Handler();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		if (!(v instanceof ImageView)) {
			return;
		}
		
		final ImageView imageView = (ImageView) v;
		lastDrawable = imageView.getBackground();
		if (imageView.getTag().equals(TAG_GIRL)) {
			score ++;
			//TODO update score
			String value = getResources().getString(R.string.score, score);
			scoreTextView.setText(value);

			imageView.setImageResource(R.drawable.kiss2);
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					imageView.setImageDrawable(null);
				}
			}, 50);
		}else if(imageView.getTag().equals(TAG_RUHUA)){
			imageView.setImageResource(R.drawable.kiss2);
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					imageView.setImageDrawable(null);
				}
			}, 50);

			//TODO Toast
			gameOver(false);
		}
		
	}
	
	private void randomGirls(){
		int tmp;
		int girlIndex;
		for (ImageView imageView : imageViews) {
			tmp = random.nextInt(10);
			girlIndex = random.nextInt(9);
			if (tmp < 1) {
				imageView.setBackgroundResource(RUHUA_DRAWABLE);
				imageView.setTag(TAG_RUHUA);
			}else if(tmp < 7){
				imageView.setBackgroundResource(GIRLS_DRAWABLE[girlIndex]);
				imageView.setTag(TAG_GIRL);
			}else if(tmp < 9){
				imageView.setBackgroundResource(BG_DRAWABLE);
				imageView.setTag(TAG_BG);
			}
		}
		
	}
	
	private void gameOver(boolean isTimeOut){
		kissLayout.setVisibility(View.INVISIBLE);
		updateEndLayout(isTimeOut);
		endLayout.setVisibility(View.VISIBLE);
		gameCountDownTimer.cancel();
	}
	
	//TODO
	private void updateEndLayout(boolean isTimeOut){
		kissGirlImageView.setImageDrawable(lastDrawable);
		
		kissResultTextView.setText(getString(R.string.kiss_result, score));
		
		String value;
		
		if (score < 20) {
			value = getString(R.string.rank_20);
		}else if(score < 40){
			value = getString(R.string.rank_40);
		}else if(score < 60){
			value = getString(R.string.rank_60);
		}else if(score < 80){
			value = getString(R.string.rank_80);
		}else{
			value = getString(R.string.rank_100);
		}
		
		kissReportTitleTextView.setText(value);
		
		
		if (score < 20) {
			if (isTimeOut) {
				value = getString(R.string.time_out_score_20, score);
			}else {
				value = getString(R.string.click_error_score_20);
			}
		}else if(score < 40){
			if (isTimeOut) {
				value = getString(R.string.time_out_score_40, score);
			}else {
				value = getString(R.string.click_error_score_40);
			}
			
		}else if(score < 60){
			if (isTimeOut) {
				value = getString(R.string.time_out_score_60, score);
			}else {
				value = getString(R.string.click_error_score_60);
			}
			
		}else if(score < 80){
			if (isTimeOut) {
				value = getString(R.string.time_out_score_80, score);
			}else {
				value = getString(R.string.click_error_score_80);
			}
			
		}else{
			if (isTimeOut) {
				value = getString(R.string.time_out_score_100, score);
			}else {
				value = getString(R.string.click_error_score_100);
			}
		}
		kissReportTextView.setText(value);
		
		
	}
	
	
	private class GameCountDownTimer extends CountDownTimer{

		public GameCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			gameOver(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			timerTextView.setText("" + millisUntilFinished / 1000);
			randomGirls();
		}
	}
	
	public void onStartButtonClick(View view){
		score = 0;
		scoreTextView.setText(getString(R.string.score, 0));
		startLayout.setVisibility(View.INVISIBLE);
		kissLayout.setVisibility(View.VISIBLE);
		timerTextView.setText("30");
		gameCountDownTimer.start();
		randomGirls();
	}
	
	public void onRestartButtonClick(View view){
		score = 0;
		scoreTextView.setText(getString(R.string.score, 0));
		endLayout.setVisibility(View.INVISIBLE);
		kissLayout.setVisibility(View.VISIBLE);
		timerTextView.setText("30");
		gameCountDownTimer.start();
		randomGirls();
		
	}
	
	public void onShareButtonClcik(View view){
		
	}
	
}