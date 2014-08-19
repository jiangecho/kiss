package com.echo.kiss;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity{


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