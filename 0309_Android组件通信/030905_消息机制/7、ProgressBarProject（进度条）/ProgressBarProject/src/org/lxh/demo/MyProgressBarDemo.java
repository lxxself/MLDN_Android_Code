package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MyProgressBarDemo extends Activity {
	private static final int STOP = 1 ;
	private static final int CONTINUE = 2 ;
	private ProgressBar myprobarA, myprobarB, myprobarC, myprobarD, myprobarE;
	private Button mybut ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.myprobarA = (ProgressBar) super.findViewById(R.id.myprobarA) ;
		this.myprobarB = (ProgressBar) super.findViewById(R.id.myprobarB) ;
		this.myprobarC = (ProgressBar) super.findViewById(R.id.myprobarC) ;
		this.myprobarD = (ProgressBar) super.findViewById(R.id.myprobarD) ;
		this.myprobarE = (ProgressBar) super.findViewById(R.id.myprobarE) ;
		this.mybut = (Button) super.findViewById(R.id.mybut) ;
		this.myprobarA.setIndeterminate(false) ;	// 不确定模式
		this.myprobarB.setIndeterminate(false) ;	// 不确定模式
		this.myprobarC.setIndeterminate(true) ;	// 确定模式
		this.myprobarD.setIndeterminate(false) ;	// 不确定模式
		this.myprobarE.setIndeterminate(false) ;	// 不确定模式
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;	// 单击事件
	}
	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyProgressBarDemo.this.myprobarB.setSecondaryProgress(50) ;
			MyProgressBarDemo.this.myprobarA.setVisibility(View.VISIBLE) ;
			MyProgressBarDemo.this.myprobarB.setVisibility(View.VISIBLE) ;
			MyProgressBarDemo.this.myprobarC.setVisibility(View.VISIBLE) ;
			MyProgressBarDemo.this.myprobarD.setVisibility(View.VISIBLE) ;
			MyProgressBarDemo.this.myprobarE.setVisibility(View.VISIBLE) ;
			
			MyProgressBarDemo.this.myprobarA.setMax(120) ;
			MyProgressBarDemo.this.myprobarB.setMax(120) ;
			MyProgressBarDemo.this.myprobarA.setProgress(0) ;
			MyProgressBarDemo.this.myprobarB.setProgress(0) ;
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					int count = 0; // 保存当前进度的值
					for (int i = 0; i < 10; i++) {
						count = (i + 1) * 20; // 进度的增长快一些
						try {// 每次操作延迟500MS
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
						if (i == 6) {	// 正好增长到120
							Message m = new Message() ;
							m.what = MyProgressBarDemo.STOP ;	// 停止
							MyProgressBarDemo.this.myMessageHandler.sendMessage(m) ;// 停止
						} else {
							Message m = new Message() ;
							m.what = MyProgressBarDemo.CONTINUE ;
							m.arg1 = count ;
							MyProgressBarDemo.this.myMessageHandler.sendMessage(m) ;
						}
					}
				}
			}).start();
		}
		
	}
	private Handler myMessageHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case MyProgressBarDemo.STOP:
				MyProgressBarDemo.this.myprobarA.setVisibility(View.GONE) ;
				MyProgressBarDemo.this.myprobarB.setVisibility(View.GONE) ;
				MyProgressBarDemo.this.myprobarC.setVisibility(View.GONE) ;
				MyProgressBarDemo.this.myprobarD.setVisibility(View.GONE) ;
				MyProgressBarDemo.this.myprobarE.setVisibility(View.GONE) ;
				Thread.currentThread().interrupt() ;
				break;
			case MyProgressBarDemo.CONTINUE:
				if (!Thread.currentThread().isInterrupted()) {	// 线程没有中断
					MyProgressBarDemo.this.myprobarA.setProgress(msg.arg1) ;
					MyProgressBarDemo.this.myprobarB.setProgress(msg.arg1) ;
					MyProgressBarDemo.this.myprobarC.setProgress(msg.arg1) ;
					MyProgressBarDemo.this.myprobarD.setProgress(msg.arg1) ;
					MyProgressBarDemo.this.myprobarE.setProgress(msg.arg1) ;
				}
				break;
			}
		}
	} ;
}