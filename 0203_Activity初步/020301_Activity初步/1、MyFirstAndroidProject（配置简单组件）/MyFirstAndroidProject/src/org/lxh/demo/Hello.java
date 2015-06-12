package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Hello extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // 生命周期方法
		super.setContentView(R.layout.main); // 设置要使用的布局管理器
		TextView text = (TextView) super.findViewById(R.id.mytext); // 取得TextView组件
		text.setText("北京魔乐科技软件学院（MLDN）") ;	// 设置显示文字
		Button but = (Button) super.findViewById(R.id.mybut) ;	// 取得按钮组件
		but.setText("按我，不过没用。") ;
	}
}