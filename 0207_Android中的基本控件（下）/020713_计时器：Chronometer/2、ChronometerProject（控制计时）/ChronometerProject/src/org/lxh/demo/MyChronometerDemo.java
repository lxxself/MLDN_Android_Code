package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;

public class MyChronometerDemo extends Activity {
	private Chronometer myChronometer = null ;
	private Button butStart = null ;
	private Button butStop = null ;
	private Button butBase = null ;
	private Button butFormat = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		super.setContentView(R.layout.main);
		this.myChronometer = (Chronometer) super.findViewById(R.id.myChronometer) ;
		this.butStart = (Button) super.findViewById(R.id.butStart) ;
		this.butStop = (Button) super.findViewById(R.id.butStop) ;
		this.butBase = (Button) super.findViewById(R.id.butBase) ;
		this.butFormat = (Button) super.findViewById(R.id.butFormat) ;
		this.butStart.setOnClickListener(new OnClickListenerImplStart()) ;
		this.butStop.setOnClickListener(new OnClickListenerImplStop()) ;
		this.butBase.setOnClickListener(new OnClickListenerImplBase()) ;
		this.butFormat.setOnClickListener(new OnClickListenerImplFormat()) ;
	}
	private class OnClickListenerImplStart implements OnClickListener{

		@Override
		public void onClick(View v) {
			MyChronometerDemo.this.myChronometer.start() ;	// 开始计时
		}
		
	}
	private class OnClickListenerImplStop implements OnClickListener{

		@Override
		public void onClick(View v) {
			MyChronometerDemo.this.myChronometer.stop() ;	// 停止计时
		}
		
	}
	private class OnClickListenerImplBase implements OnClickListener{

		@Override
		public void onClick(View v) {
			MyChronometerDemo.this.myChronometer.setBase(SystemClock
					.elapsedRealtime());	 				// 开始计时
		}
		
	}
	private class OnClickListenerImplFormat implements OnClickListener{

		@Override
		public void onClick(View v) {
			MyChronometerDemo.this.myChronometer.setFormat("新的显示格式：%s。") ;
		}
		
	}
}