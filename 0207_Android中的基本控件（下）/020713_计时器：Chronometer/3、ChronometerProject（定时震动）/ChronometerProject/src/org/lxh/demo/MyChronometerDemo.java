package org.lxh.demo;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

public class MyChronometerDemo extends Activity {
	private Chronometer myChronometer = null ;
	private Button butStart = null ;
	private Button butStop = null ;
	private Vibrator vibrator = null ;	// 震动操作
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		super.setContentView(R.layout.main);
		
		this.vibrator = (Vibrator) super.getApplication().getSystemService(
				Service.VIBRATOR_SERVICE);	// 取得震动服务
		
		this.myChronometer = (Chronometer) super.findViewById(R.id.myChronometer) ;
		this.butStart = (Button) super.findViewById(R.id.butStart) ;
		this.butStop = (Button) super.findViewById(R.id.butStop) ;
		
		this.myChronometer.setFormat("当前计时时间：%s。") ;
		
		this.myChronometer.setOnChronometerTickListener(new OnChronometerTickListenerImpl()) ;
		
		this.butStart.setOnClickListener(new OnClickListenerImplStart()) ;
		this.butStop.setOnClickListener(new OnClickListenerImplStop()) ;
	}
	
	private class OnChronometerTickListenerImpl implements OnChronometerTickListener {

		@Override
		public void onChronometerTick(Chronometer chronometer) {
			String time = chronometer.getText().toString().replaceAll("[^(\\d{2}:\\d{2})]", "") ;
			Log.i("MyChronometerDemo", time) ;
			if("01:00".equals(time)) {
				MyChronometerDemo.this.vibrator.vibrate(new long[] { 1000, 10,
						100, 100 }, 0);	// 重复震动
			}
		}
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
}