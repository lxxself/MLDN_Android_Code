package org.lxh.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiverUtil extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if ("org.lxh.action.MLDN".equals(intent.getAction())) {	// 指定的Action
			String msg = intent.getStringExtra("msg") ;	// 取得附加信息
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}
	}

}
