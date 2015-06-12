package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class MyFocusDemo extends Activity {
	private EditText edit = null; // 在此组件上设置焦点事件
	private TextView txt = null; // 用于信息提示

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.edit = (EditText) super.findViewById(R.id.edit); // 取得组件
		this.txt = (TextView) super.findViewById(R.id.txt); // 取得组件
		this.edit.setOnClickListener(new OnClickListenerImpl()); // 设置单击事件
		this.edit.setOnFocusChangeListener(new OnFocusChangeListenerImpl());
	}

	private class OnClickListenerImpl implements OnClickListener {

		public void onClick(View v) {
			MyFocusDemo.this.edit.setText(""); // 清空文本
		}

	}

	private class OnFocusChangeListenerImpl implements OnFocusChangeListener {

		public void onFocusChange(View v, boolean hasFocus) { // 表示操作的组件，而hasFocus表示是否获得焦点
			if (hasFocus) { // 已经获得了焦点
				MyFocusDemo.this.txt.setText("文本输入组件获得焦点。");
			} else {
				if (MyFocusDemo.this.edit.getText().length() > 0) { // 现在有数据
					MyFocusDemo.this.txt.setText("文本输入组件失去焦点，输入内容合法。");
				} else {
					MyFocusDemo.this.txt.setText("文本输入组件失去焦点，输入内容不能为空。");
				}
			}
		}

	}
}