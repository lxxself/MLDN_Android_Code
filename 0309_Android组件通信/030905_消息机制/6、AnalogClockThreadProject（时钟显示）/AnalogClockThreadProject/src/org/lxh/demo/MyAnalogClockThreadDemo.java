package org.lxh.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MyAnalogClockThreadDemo extends Activity {
	private TextView info = null ;
	private static final int SET = 1 ;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case SET :
				MyAnalogClockThreadDemo.this.info.setText("当前时间为："
						+ msg.obj.toString());
				break ;
			}
		}
	} ;
	
	private class ClockThread implements Runnable {

		@Override
		public void run() {
			while(true) {	// 一直更新
				Message msg = MyAnalogClockThreadDemo.this.handler
						.obtainMessage(MyAnalogClockThreadDemo.SET,
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
										.format(new Date()));
				MyAnalogClockThreadDemo.this.handler.sendMessage(msg) ;
				try {
					Thread.sleep(1000) ;
				} catch (InterruptedException e) {
				}
			}
		}
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.info = (TextView) super.findViewById(R.id.info) ;
		new Thread(new ClockThread()).start() ;
	}
}