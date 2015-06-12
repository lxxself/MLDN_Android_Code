package org.lxh.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiverUtil extends BroadcastReceiver {
	public MyBroadcastReceiverUtil() { // 构造方法
		System.out.println("** 每次广播都会实例化一个新的广播组件进行操作。");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "广播已经启动", Toast.LENGTH_SHORT).show();
	}

}
