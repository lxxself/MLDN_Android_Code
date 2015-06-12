package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;

public class MyChronometerDemo extends Activity {
	private Chronometer myChronometer = null ;
	private Button butStart = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		super.setContentView(R.layout.main);
		this.myChronometer = (Chronometer) super.findViewById(R.id.myChronometer) ;
		this.butStart = (Button) super.findViewById(R.id.butStart) ;
		this.butStart.setOnClickListener(new OnClickListenerImplStart()) ;
	}
	private class OnClickListenerImplStart implements OnClickListener{

		@Override
		public void onClick(View v) {
			MyChronometerDemo.this.myChronometer.start() ;	// 开始计时
		}
		
	}
}