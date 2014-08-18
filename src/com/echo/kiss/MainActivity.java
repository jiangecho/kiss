package com.echo.kiss;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;

public class MainActivity extends Activity{

	GridLayout gridLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kiss_layout);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
}