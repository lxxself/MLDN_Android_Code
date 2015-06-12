package org.lxh.demo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyAppWidget extends AppWidgetProvider {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if ("org.lxh.action.MYAPPWIDGET_UPDATE".equals(intent.getAction())) {
			RemoteViews remote = new RemoteViews(context.getPackageName(),
					R.layout.mldn_appwidget);
			remote.setImageViewResource(R.id.img, R.drawable.mldn_man);
			remote.setTextViewText(R.id.but, "www.MLDNJAVA.cn") ;
			AppWidgetManager appWidgetManager = AppWidgetManager
					.getInstance(context);
			ComponentName componentName = new ComponentName(context,
					MyAppWidget.class);
			appWidgetManager.updateAppWidget(componentName, remote) ;
		} else {
			super.onReceive(context, intent) ;	// 如果不写此代码，表示无法调用onUpdate()
		}
	}
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Intent intent = new Intent();
		intent.setAction("org.lxh.action.MYAPPWIDGET_UPDATE") ;
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		RemoteViews remote = new RemoteViews(context.getPackageName(),
				R.layout.mldn_appwidget);
		remote.setOnClickPendingIntent(R.id.but, pendingIntent);
		appWidgetManager.updateAppWidget(appWidgetIds, remote);
	}

}
