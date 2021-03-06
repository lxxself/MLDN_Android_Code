package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;

public class MyClickDemo extends Activity {
	private EditText password = null;
	private CheckBox show = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.password = (EditText) super.findViewById(R.id.password);
		this.show = (CheckBox) super.findViewById(R.id.show);
		this.show.setOnClickListener(new OnClickListenerImpl());
	}

	private class OnClickListenerImpl implements OnClickListener {

		public void onClick(View v) {
			if (MyClickDemo.this.show.isChecked()) { // 被选中，应该采用明文的方式显示
				MyClickDemo.this.password
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance()); // 将文本框的内容设置为明文显示
			} else { // 采用密文的方式显示
				MyClickDemo.this.password
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance()); // 以密文的方式显示
			}
		}

	}
}