package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {
	private Button mybut = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("*** [B] SecondActivity --> onCreate()");
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.second_main);
		this.mybut = (Button) super.findViewById(R.id.mybut) ;	// 取得组件
		this.mybut.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent it = new Intent(SecondActivity.this,FirstActivity.class) ;
				SecondActivity.this.startActivity(it) ;
				SecondActivity.this.finish() ;
			}}) ;	// 单击事件
	}

	@Override
	protected void onDestroy() {
		System.out.println("*** [B] SecondActivity --> onDestroy()");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		System.out.println("*** [B] SecondActivity --> onPause()");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		System.out.println("*** [B] SecondActivity --> onRestart()");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		System.out.println("*** [B] SecondActivity --> onResume()");
		super.onResume();
	}

	@Override
	protected void onStart() {
		System.out.println("*** [B] SecondActivity --> onStart()");
		super.onStart();
	}

	@Override
	protected void onStop() {
		System.out.println("*** [B] SecondActivity --> onStop()");
		super.onStop();
	}
	
}