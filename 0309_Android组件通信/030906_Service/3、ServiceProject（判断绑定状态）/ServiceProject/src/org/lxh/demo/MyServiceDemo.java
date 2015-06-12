package org.lxh.demo;

import org.lxh.demo.MyServiceUtil.BinderImpl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyServiceDemo extends Activity {
	private Button bind;
	private Button unbind;
	private IService service = null ;
	private ServiceConnection serviceConnection = new ServiceConnectionImpl() ;
	
	private class ServiceConnectionImpl implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			MyServiceDemo.this.service = (BinderImpl) service ;
			// BinderImpl是IBinder接口的子类，所以将IBinder向下转型为BinderImpl类
			// 而后通过此类对象为Service接口实例化
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.bind = (Button) super.findViewById(R.id.bind);
		this.unbind = (Button) super.findViewById(R.id.unbind);
		this.bind.setOnClickListener(new BindOnClickListenerImpl());
		this.unbind.setOnClickListener(new UnbindOnClickListenerImpl());
	}
	private class BindOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyServiceDemo.this.bindService(new Intent(MyServiceDemo.this,
					MyServiceUtil.class), MyServiceDemo.this.serviceConnection,
					Context.BIND_AUTO_CREATE);
		}

	}

	private class UnbindOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			if(MyServiceDemo.this.service != null) {
				MyServiceDemo.this
						.unbindService(MyServiceDemo.this.serviceConnection);
				MyServiceDemo.this.service = null ;
			}
		}

	}
}