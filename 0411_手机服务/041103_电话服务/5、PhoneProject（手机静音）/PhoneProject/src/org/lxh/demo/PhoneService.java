package org.lxh.demo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneService extends Service {
	private TelephonyManager telephony = null;
	private AudioManager audio = null; // 声音服务
	private String phoneNumber = null; // 要过滤的电话
	private IBinder myBinder = new BinderImpl();

	class BinderImpl extends Binder implements IService {

		@Override
		public String getInterfaceDescriptor() {
			return "过滤电话“" + PhoneService.this.phoneNumber + "”设置成功！";
		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		this.phoneNumber = intent.getStringExtra("phonenumber"); // 取得电话号码
		this.audio = (AudioManager) super
				.getSystemService(Context.AUDIO_SERVICE); // 声音服务
		this.telephony = (TelephonyManager) super
				.getSystemService(Context.TELEPHONY_SERVICE);
		this.telephony.listen(new PhoneStateListenerImpl(),
				PhoneStateListener.LISTEN_CALL_STATE); // 设置监听操作
		return this.myBinder;
	}

	private class PhoneStateListenerImpl extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE: // 挂断电话
				PhoneService.this.audio
						.setRingerMode(AudioManager.RINGER_MODE_NORMAL); // 正常音
				break;
			case TelephonyManager.CALL_STATE_RINGING: // 领音响起
				if (incomingNumber.equals(PhoneService.this.phoneNumber)) { // 电话号码匹配
					PhoneService.this.audio
							.setRingerMode(AudioManager.RINGER_MODE_SILENT); // 静音
				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK: // 接听电话
				break;
			}
		}
	}

}
