package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;

public class MyDatePickerDemo extends Activity {
	private DatePicker mydp = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.mydp = (DatePicker) super.findViewById(R.id.dp2); // 取得组件
		this.mydp.updateDate(1998, 7, 27); // 更新日期
	}
}