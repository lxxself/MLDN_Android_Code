package org.lxh.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneService extends Service {
	private TelephonyManager telephony = null;

	@Override
	public void onCreate() { // 服务创建的时候操作
		super.onCreate();
		this.telephony = (TelephonyManager) super
				.getSystemService(Context.TELEPHONY_SERVICE);
		this.telephony.listen(new PhoneStateListenerImpl(),
				PhoneStateListener.LISTEN_CALL_STATE); // 设置监听操作
	}

	private class PhoneStateListenerImpl extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE: // 挂断电话
				break;
			case TelephonyManager.CALL_STATE_RINGING: // 领音响起
				System.out.println("拨入电话号码："
						+ incomingNumber
						+ "，拨入时间："
						+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date()));
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK: // 接听电话
				break;
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
