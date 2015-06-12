package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MyBroadcastDemo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		Intent it = new Intent("org.lxh.action.MLDN"); // ²Ù×÷µÄ¹ýÂË
		MyBroadcastDemo.this.sendBroadcast(it) ;
	}
	@Override
	protected void onStop() {
		super.onStop();
	}
	
}