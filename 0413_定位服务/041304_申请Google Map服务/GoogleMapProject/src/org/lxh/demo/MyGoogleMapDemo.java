package org.lxh.demo;

import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class MyGoogleMapDemo extends MapActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}