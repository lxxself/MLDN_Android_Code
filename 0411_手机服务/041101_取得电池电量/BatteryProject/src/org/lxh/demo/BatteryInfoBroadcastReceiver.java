package org.lxh.demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class BatteryInfoBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
			int level = intent.getIntExtra("level", 0);
			int scale = intent.getIntExtra("scale", 100);
			Dialog dialog = new AlertDialog.Builder(context)
					.setTitle("电池电量")
					.setMessage(
							"电池电量为：" + String.valueOf(level * 100 / scale)
									+ "%")
					.setNegativeButton("关闭",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									
								}
							}).create();
			dialog.show();
		}
	}

}
