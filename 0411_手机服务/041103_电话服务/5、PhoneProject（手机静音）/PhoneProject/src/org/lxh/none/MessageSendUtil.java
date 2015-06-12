package org.lxh.none;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class MessageSendUtil {
	private Context context = null;
	private Intent intent = null;

	public MessageSendUtil(Context context, Intent intent) {
		this.context = context;
		this.intent = intent;
	}

	/**
	 * 发送短信的操作
	 * 
	 * @param recieveNumber
	 *            短信接收人的电话号码
	 * @param phoneNumber
	 *            保留来电或去电的电话号码
	 * @param type
	 *            电话的呼叫类型
	 */
	public void send(String recieveNumber, String phoneNumber, String type) {
		SmsManager smsManager = SmsManager.getDefault();
		PendingIntent sentIntent = PendingIntent.getActivity(this.context, 0,
				this.intent, PendingIntent.FLAG_UPDATE_CURRENT);
		String content = "电话号码："
				+ phoneNumber
				+ "\n类型："
				+ type
				+ "\n操作时间："
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
						.format(new Date());
		smsManager.sendTextMessage(recieveNumber, null, content, sentIntent,
				null);
	}
}
