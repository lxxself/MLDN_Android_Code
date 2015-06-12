package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Hello extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // 生命周期方法
		TextView text = new TextView(this); // 要根据上下文（Context）创建组件
		text.setText(super.getString(R.string.info)); // 通过strings.xml文件设置文字
		super.setContentView(text); // 设置要使用的布局管理器
	}
}