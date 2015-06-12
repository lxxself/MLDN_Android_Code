package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity {
	private Button mybut = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("*** {A} FirstActivity --> onCreate()");
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.first_main);
		this.mybut = (Button) super.findViewById(R.id.mybut) ;	// 取得组件
		this.mybut.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent it = new Intent(FirstActivity.this,SecondActivity.class) ;
				FirstActivity.this.startActivity(it) ;	// 执行跳转
			}}) ;	// 单击事件
	}

	@Override
	protected void onDestroy() {
		System.out.println("*** {A} FirstActivity --> onDestroy()");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		System.out.println("*** {A} FirstActivity --> onPause()");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		System.out.println("*** {A} FirstActivity --> onRestart()");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		System.out.println("*** {A} FirstActivity --> onResume()");
		super.onResume();
	}

	@Override
	protected void onStart() {
		System.out.println("*** {A} FirstActivity --> onStart()");
		super.onStart();
	}

	@Override
	protected void onStop() {
		System.out.println("*** {A} FirstActivity --> onStop()");
		super.onStop();
	}
	
}