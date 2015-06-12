package org.lxh.demo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Bundle;

public class MyNotificationDemo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		NotificationManager notificationManager = (NotificationManager) super
				.getSystemService(Activity.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.pic_m,
				"来自MLDN的消息。", System.currentTimeMillis()); // 立刻发送一个消息
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				super.getIntent(), PendingIntent.FLAG_UPDATE_CURRENT); // 创建了一个PendingIntent对象
		notification.setLatestEventInfo(this, "魔乐科技",
				"北京魔乐科技软件学院（www.MLDNJAVA.cn）", contentIntent);
		notificationManager.notify("MLDN", R.drawable.pic_m, notification);
	}
}