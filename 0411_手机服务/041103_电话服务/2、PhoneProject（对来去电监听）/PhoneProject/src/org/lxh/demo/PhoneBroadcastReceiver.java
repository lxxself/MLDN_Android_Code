package org.lxh.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PhoneBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_NEW_OUTGOING_CALL.equals(intent.getAction())) { // 去电
			String outgoingNumber = intent
					.getStringExtra(Intent.EXTRA_PHONE_NUMBER); // 去电号码
			Intent pit = new Intent(context, PhoneService.class);
			pit.putExtra("outgoingNumber", outgoingNumber);
			context.startService(pit);
		} else { // 来电
			context.startService(new Intent(context, PhoneService.class));
		}
	}

}
