package org.lxh.demo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyAppWidget extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for (int x = 0; x < appWidgetIds.length; x++) {
			Intent intent = new Intent(context,MyAppWidgetDemo.class) ;
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
					intent, PendingIntent.FLAG_UPDATE_CURRENT);
			RemoteViews remote = new RemoteViews(context.getPackageName(),
					R.layout.mldn_appwidget);
			remote.setOnClickPendingIntent(R.id.but, pendingIntent) ;
			appWidgetManager.updateAppWidget(appWidgetIds[x], remote) ;
		}
	}

}
