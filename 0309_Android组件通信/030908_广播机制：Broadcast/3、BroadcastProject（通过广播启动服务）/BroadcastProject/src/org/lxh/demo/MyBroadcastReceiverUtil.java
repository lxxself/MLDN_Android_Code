package org.lxh.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiverUtil extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		context.startService(new Intent(context,MyServiceUtil.class)) ;
	}

}
