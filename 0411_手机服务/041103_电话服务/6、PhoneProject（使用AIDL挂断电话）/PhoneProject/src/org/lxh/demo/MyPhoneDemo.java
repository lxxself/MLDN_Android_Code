package org.lxh.demo;

import org.lxh.demo.PhoneService.BinderImpl;

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
import android.widget.EditText;
import android.widget.Toast;

public class MyPhoneDemo extends Activity {
	private EditText phoneNumber = null ;
	private Button setNumber = null ;
	private Button cancelNumber = null ;
	private IService service = null ;
	private ServiceConnectionImpl serviceConnection = new ServiceConnectionImpl() ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.phoneNumber = (EditText) super.findViewById(R.id.phonenumber) ;
		this.setNumber = (Button) super.findViewById(R.id.setnumber) ;
		this.cancelNumber = (Button) super.findViewById(R.id.cancelnumber) ;
		this.setNumber.setOnClickListener(new SetOnClickListenerImpl()) ;
		this.cancelNumber.setOnClickListener(new CancelOnClickListenerImpl()) ;
	}

	private class SetOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MyPhoneDemo.this,PhoneService.class) ;
			intent.putExtra("phonenumber", MyPhoneDemo.this.phoneNumber
					.getText().toString());
			MyPhoneDemo.this.bindService(intent,
					MyPhoneDemo.this.serviceConnection,
					Context.BIND_AUTO_CREATE);
			
		}
	}
	private class CancelOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			if(MyPhoneDemo.this.service != null) {
				MyPhoneDemo.this.unbindService(MyPhoneDemo.this.serviceConnection) ;
				MyPhoneDemo.this.stopService(new Intent(MyPhoneDemo.this,PhoneService.class)) ;
				Toast.makeText(MyPhoneDemo.this, "黑名单已取消", Toast.LENGTH_LONG)
						.show();
				MyPhoneDemo.this.service = null ;
			}
		}
	}
	private class ServiceConnectionImpl implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			MyPhoneDemo.this.service = (BinderImpl) service ;
			try {
				Toast.makeText(MyPhoneDemo.this, service.getInterfaceDescriptor(), Toast.LENGTH_LONG).show() ;
			} catch (RemoteException e) {
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}
}