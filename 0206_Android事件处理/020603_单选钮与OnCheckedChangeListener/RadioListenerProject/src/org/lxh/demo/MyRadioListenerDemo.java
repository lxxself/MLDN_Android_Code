package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MyRadioListenerDemo extends Activity {
	private TextView show = null;
	private RadioGroup sex = null;
	private RadioButton male = null;
	private RadioButton female = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.show = (TextView) super.findViewById(R.id.show);
		this.sex = (RadioGroup) super.findViewById(R.id.sex);
		this.male = (RadioButton) super.findViewById(R.id.male);
		this.female = (RadioButton) super.findViewById(R.id.female);
		this.sex.setOnCheckedChangeListener(new OnCheckedChangeListenerImpl());
	}

	private class OnCheckedChangeListenerImpl implements
			OnCheckedChangeListener {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			String temp = null; // 保存以后show组件要显示的文本信息
			if (MyRadioListenerDemo.this.male.getId() == checkedId) { // 现在选中的ID和组件的ID一致
				temp = MyRadioListenerDemo.this.male.getText().toString(); // 取得信息
			}
			if (MyRadioListenerDemo.this.female.getId() == checkedId) { // 现在选中的ID和组件的ID一致
				temp = MyRadioListenerDemo.this.female.getText().toString(); // 取得信息
			}
			MyRadioListenerDemo.this.show.setText("您的性别是：" + temp);// 设置文本组件的内容
		}
	}
}