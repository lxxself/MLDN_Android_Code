package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class MyEditTextDemo extends Activity {
	private EditText edit = null; // 作为属性出现

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.edit = (EditText) super.findViewById(R.id.myet2); // 取得组件
		this.edit.setEnabled(false) ;	// 现在不可编辑
	}
}