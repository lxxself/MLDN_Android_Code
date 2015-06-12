package org.lxh.demo;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class MyAppWidget extends AppWidgetProvider {

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		System.out.println("*** MyAppWidget onDeleted") ;
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		System.out.println("*** MyAppWidget onDisabled") ;
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		System.out.println("*** MyAppWidget onEnabled") ;
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("*** MyAppWidget onReceive") ;
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		System.out.println("*** MyAppWidget onUpdate") ;
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

}
