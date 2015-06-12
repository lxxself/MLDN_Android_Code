package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Receive extends Activity {
	private TextView show = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.receive_main);
		this.show = (TextView) super.findViewById(R.id.show) ;
		Intent it = super.getIntent() ;	// 取得当前的Intent
		String info = it.getStringExtra("myinfo") ;
		this.show.setText(info) ; 
	}
}