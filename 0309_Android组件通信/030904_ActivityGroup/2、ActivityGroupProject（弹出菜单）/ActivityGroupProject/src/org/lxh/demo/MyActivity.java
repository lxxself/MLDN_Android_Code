package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;

public class MyActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.mylayout);
	}
}