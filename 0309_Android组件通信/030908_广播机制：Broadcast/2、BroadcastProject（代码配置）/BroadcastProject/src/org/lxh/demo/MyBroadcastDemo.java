package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyBroadcastDemo extends Activity {
	private Button mybut = null ;
	private MyBroadcastReceiverUtil broadUtil = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		this.mybut = (Button) super.findViewById(R.id.mybut);
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;
	}
	
	private class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent it = new Intent("org.lxh.action.MLDN"); // 操作的过滤
			it.putExtra("msg", "www.mldnjava.cn") ;	// 附加信息
			IntentFilter filter = new IntentFilter("org.lxh.action.MLDN") ;
			MyBroadcastDemo.this.broadUtil = new MyBroadcastReceiverUtil() ;
			MyBroadcastDemo.this.registerReceiver(MyBroadcastDemo.this.broadUtil, filter) ;
			MyBroadcastDemo.this.sendBroadcast(it) ;
		}
		
	}
}