package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyServiceDemo extends Activity {
	private Button start ;
	private Button stop ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.start = (Button) super.findViewById(R.id.start) ;
		this.stop = (Button) super.findViewById(R.id.stop) ;
		this.start.setOnClickListener(new StartOnClickListenerImpl()) ;
		this.stop.setOnClickListener(new StopOnClickListenerImpl()) ;
	}
	private class StartOnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			MyServiceDemo.this.startService(new Intent(MyServiceDemo.this,MyServiceUtil.class)) ;
		}
	}
	private class StopOnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			MyServiceDemo.this.stopService(new Intent(MyServiceDemo.this,MyServiceUtil.class)) ;
		}
		
	}
}