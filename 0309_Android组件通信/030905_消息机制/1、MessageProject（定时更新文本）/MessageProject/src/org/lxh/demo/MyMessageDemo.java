package org.lxh.demo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MyMessageDemo extends Activity {
	private TextView info = null ;
	private static int count = 0 ;	// 表示更新后的记录
	private static final int SET = 1 ;	// 操作的what状态
	
	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {	// 判断操作的消息类型
			case SET:	// 更新组件
				MyMessageDemo.this.info.setText("MLDN - " + count ++) ;
			}
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.info = (TextView) super.findViewById(R.id.info) ;	// 取得组件
		Timer timer = new Timer() ;
		timer.schedule(new MyTask(), 0,1000) ;
		
	}
	private class MyTask extends TimerTask{

		@Override
		public void run() {
			Message msg = new Message() ;	// 设置更新
			msg.what = SET ;	// 操作的标记
			MyMessageDemo.this.myHandler.sendMessage(msg) ;	// 发送消息
		}
		
	}
}