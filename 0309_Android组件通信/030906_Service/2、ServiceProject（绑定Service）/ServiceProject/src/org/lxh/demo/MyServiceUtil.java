package org.lxh.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyServiceUtil extends Service {
	private IBinder myBinder = new Binder() {

		@Override
		public String getInterfaceDescriptor() {
			return "MyServiceUitl Class .." ;
		}
	} ;
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("*** Service onBind()") ;
		return this.myBinder ;	// 此处暂时不做任何的处理
	}

	
	
	@Override
	public void onRebind(Intent intent) {
		System.out.println("*** Service onRebind()") ;
		super.onRebind(intent);
	}



	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("*** Service onUnbind()") ;
		return super.onUnbind(intent);
	}

	@Override
	public void onCreate() {
		System.out.println("*** Service onCreate()") ;
	}

	@Override
	public void onDestroy() {
		System.out.println("*** Service onDestroy()") ;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("*** Service onStartCommand") ;
		return Service.START_CONTINUATION_MASK; // 继续执行
	}

	
}
