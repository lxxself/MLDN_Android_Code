package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyBatteryDemo extends Activity {
	private Button but = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.but = (Button) super.findViewById(R.id.but) ;
		this.but.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			BatteryInfoBroadcastReceiver receiver = new BatteryInfoBroadcastReceiver() ;
			IntentFilter filter = new IntentFilter(
					Intent.ACTION_BATTERY_CHANGED) ;
			MyBatteryDemo.this.registerReceiver(receiver, filter) ;
		}
		
	}

}