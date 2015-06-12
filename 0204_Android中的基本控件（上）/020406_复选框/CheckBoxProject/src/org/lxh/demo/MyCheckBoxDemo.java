package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;

public class MyCheckBoxDemo extends Activity {
	private CheckBox box = null; // 定义组件

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.box = (CheckBox) super.findViewById(R.id.url3); // 取得组件
		this.box.setChecked(true); // 将此组件设置为默认选中
		this.box.setText("www.jiangker.com"); // 设置显示文字
	}
}