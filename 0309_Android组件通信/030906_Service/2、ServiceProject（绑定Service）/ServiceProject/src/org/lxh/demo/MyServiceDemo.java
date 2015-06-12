package org.lxh.demo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyServiceDemo extends Activity {
	private Button start;
	private Button stop;
	private Button bind;
	private Button unbind;
	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			try {
				System.out.println("### Service Connect Success . service = "
						+ service.getInterfaceDescriptor());
			} catch (RemoteException e) {
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("### Service Connect Failure.");
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.start = (Button) super.findViewById(R.id.start);
		this.stop = (Button) super.findViewById(R.id.stop);
		this.bind = (Button) super.findViewById(R.id.bind);
		this.unbind = (Button) super.findViewById(R.id.unbind);
		this.start.setOnClickListener(new StartOnClickListenerImpl());
		this.stop.setOnClickListener(new StopOnClickListenerImpl());
		this.bind.setOnClickListener(new BindOnClickListenerImpl());
		this.unbind.setOnClickListener(new UnbindOnClickListenerImpl());
	}

	private class StartOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyServiceDemo.this.startService(new Intent(MyServiceDemo.this,
					MyServiceUtil.class));
		}
	}

	private class StopOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyServiceDemo.this.stopService(new Intent(MyServiceDemo.this,
					MyServiceUtil.class));
		}

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
			MyServiceDemo.this
					.unbindService(MyServiceDemo.this.serviceConnection);
		}

	}
}