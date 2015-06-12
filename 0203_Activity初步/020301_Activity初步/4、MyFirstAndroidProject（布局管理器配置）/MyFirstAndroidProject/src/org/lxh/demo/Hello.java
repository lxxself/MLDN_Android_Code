package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Hello extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // 生命周期方法
		LinearLayout layout = new LinearLayout(this); // 定义布局管理器
		layout.setOrientation(LinearLayout.VERTICAL) ;	// 所有组件垂直摆放
		TextView text = new TextView(this); // 要根据上下文（Context）创建组件
		text.setText(super.getString(R.string.info)); // 通过strings.xml文件设置文字
		Button but = new Button(this); // 定义按钮
		but.setText(super.getString(R.string.msg)); // 配置组件文字
		layout.addView(text); // 向布局管理器之中增加文本组件
		layout.addView(but); // 向布局管理器之中增加按钮组件
		super.setContentView(layout); // 设置要使用的布局管理器
	}
}