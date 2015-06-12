package org.lxh.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyServiceUtil extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		System.out.println("*** Service onCreate()") ;
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		System.out.println("*** Service onDestroy()") ;
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("*** Service onStartCommand()") ;
		return Service.START_CONTINUATION_MASK; 
	}
	
}
