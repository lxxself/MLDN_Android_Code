package org.lxh.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyServiceUtil extends Service {
	private IBinder myBinder = new BinderImpl() ;
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("*** Service onBind()") ;
		return this.myBinder ;	// 此处暂时不做任何的处理
	}
	class BinderImpl extends Binder implements IService{
		@Override
		public String getInterfaceDescriptor() {
			return "MyServiceUitl Class .." ;
		}
	}
}
