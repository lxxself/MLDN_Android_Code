package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TimePicker;

public class MyTimePicker extends Activity {
	private TimePicker mytp = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.mytp = (TimePicker) super.findViewById(R.id.tp2) ;	// 取得组件
		this.mytp.setIs24HourView(true) ;	// 设置为24小时制
		this.mytp.setCurrentHour(18) ;	// 设置时
		this.mytp.setCurrentMinute(30) ;	// 设置分
	}
}