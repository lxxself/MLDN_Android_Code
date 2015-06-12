package org.lxh.demo;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyWifiDemo extends Activity {
	private Button open = null ;
	private Button close = null ;
	private Button check = null ;
	private TextView msg = null ;
	private WifiManager wifiManager = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.open = (Button) super.findViewById(R.id.open) ;
		this.close = (Button) super.findViewById(R.id.close) ;
		this.check = (Button) super.findViewById(R.id.check) ;
		this.msg = (TextView) super.findViewById(R.id.msg) ;
		this.wifiManager = (WifiManager) super
				.getSystemService(Context.WIFI_SERVICE);
		this.open.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				MyWifiDemo.this.wifiManager.setWifiEnabled(true) ;	// 启用wifi
				MyWifiDemo.this.msg.setText("打开WIFI，状态：" + MyWifiDemo.this.wifiManager.getWifiState()) ;
			}
 			
		}) ;
		this.close.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				MyWifiDemo.this.wifiManager.setWifiEnabled(false) ;	// 启用wifi
				MyWifiDemo.this.msg.setText("打开WIFI，状态：" + MyWifiDemo.this.wifiManager.getWifiState()) ;
			}
 			
		}) ;
		this.check.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				MyWifiDemo.this.msg.setText("检查WIFI，状态：" + MyWifiDemo.this.wifiManager.getWifiState()) ;
			}
 			
		}) ;
	}
}