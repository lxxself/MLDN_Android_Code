package org.lxh.demo;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MySMSListenerDemo extends Activity {
	private EditText tel = null;
	private EditText content = null;
	private Button send = null;
	private SMSSendBroadcastReceiver sendRec = null;
	private SMSDeliveredBroadcastReceiver delRec = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.sendRec = new SMSSendBroadcastReceiver();
		this.delRec = new SMSDeliveredBroadcastReceiver();
		this.tel = (EditText) super.findViewById(R.id.tel);
		this.content = (EditText) super.findViewById(R.id.content);
		this.send = (Button) super.findViewById(R.id.send);
		this.send.setOnClickListener(new SendOnClickListenerImpl());
	}

	private class SendOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent sentIntent = new Intent("SMS_SEND_ACTION");
			Intent deliveredIntent = new Intent("SMS_DELIVERED_ACTION");
			SmsManager smsManager = SmsManager.getDefault();
			String telphone = MySMSListenerDemo.this.tel.getText().toString();
			String content = MySMSListenerDemo.this.content.getText()
					.toString();
			PendingIntent sendPendIntent = PendingIntent.getBroadcast(
					MySMSListenerDemo.this, 0, sentIntent, 0);
			PendingIntent deliveredPendIntent = PendingIntent.getBroadcast(
					MySMSListenerDemo.this, 0, deliveredIntent, 0);
			MySMSListenerDemo.this.registerReceiver(
					MySMSListenerDemo.this.sendRec, new IntentFilter(
							"SMS_SEND_ACTION"));
			MySMSListenerDemo.this.registerReceiver(
					MySMSListenerDemo.this.delRec, new IntentFilter(
							"SMS_DELIVERED_ACTION"));
			if (content.length() > 70) {
				List<String> msgs = smsManager.divideMessage(content); // ²ğ·ÖĞÅÏ¢
				Iterator<String> iter = msgs.iterator();
				while (iter.hasNext()) {
					String msg = iter.next();
					smsManager.sendTextMessage(telphone, null, msg,
							sendPendIntent, deliveredPendIntent);
				}
			} else {
				smsManager.sendTextMessage(telphone, null, content,
						sendPendIntent, deliveredPendIntent);
			}
		}

	}
}