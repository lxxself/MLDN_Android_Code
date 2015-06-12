package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyThreadDemo extends Activity {
	public static final int SETMAIN = 1; // 设置一个what标记
	public static final int SETCHILD = 2; // 设置what的标记]
	private Handler mainHandler, childHandler;
	private TextView msg = null;
	private Button but;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.msg = (TextView) super.findViewById(R.id.msg);
		this.but = (Button) super.findViewById(R.id.but);
		this.mainHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				switch(msg.what) {
				case SETMAIN :
					MyThreadDemo.this.msg.setText("主线程接收数据：" + msg.obj.toString()) ;
					break ;
				}
			}
			
		} ;
		new Thread(new ChildThread(), "Child Thread").start();
		this.but.setOnClickListener(new OnClickListenerImpl()) ;
	}

	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {	// 是将信息发送到子线程之中
			if (MyThreadDemo.this.childHandler != null) {
				Message childMsg = MyThreadDemo.this.childHandler
						.obtainMessage(); // 创建消息
				childMsg.obj = MyThreadDemo.this.mainHandler.getLooper()
						.getThread().getName()
						+ " --> Hello MLDN .";
				childMsg.what = SETCHILD ;
				MyThreadDemo.this.childHandler.sendMessage(childMsg) ;
			}
		}

	}
	
	class ChildThread implements Runnable {

		@Override
		public void run() {	// 子线程
			Looper.prepare() ;	// 
			MyThreadDemo.this.childHandler = new Handler(){

				@Override
				public void handleMessage(Message msg) {
					switch(msg.what) {
					case SETCHILD: // 子线程接收主线程发送来的消息
						System.out.println("*** Main Child Message : " + msg.obj) ;	// 输出数据
						Message toMain = MyThreadDemo.this.mainHandler.obtainMessage() ;
						toMain.obj = "\n\n[B]这是子线程发送给主线程的信息：" + super.getLooper().getThread().getName() ;
						toMain.what = SETMAIN ;
						MyThreadDemo.this.mainHandler.sendMessage(toMain) ;
						break ;
					}
				}
				
			} ;
			Looper.loop() ;	// 创建消息队列
		}
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		MyThreadDemo.this.childHandler.getLooper().quit() ;
	}
	
	
}