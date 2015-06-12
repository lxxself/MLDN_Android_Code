package org.lxh.demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SMSDeliveredBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("SMS_DELIVERED_ACTION")) { // 短信发送
			switch (super.getResultCode()) { // 操作结果
			case Activity.RESULT_OK:
				Toast.makeText(context, "短信已接收！", Toast.LENGTH_SHORT).show(); 
				break;
			case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				Toast.makeText(context, "短信发送失败！", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}

}
