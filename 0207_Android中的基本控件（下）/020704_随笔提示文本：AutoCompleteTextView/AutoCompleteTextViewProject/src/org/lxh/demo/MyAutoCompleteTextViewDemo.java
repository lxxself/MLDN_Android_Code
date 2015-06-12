package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MyAutoCompleteTextViewDemo extends Activity {
	private static final String DATA[] = new String[] { "mldn", "mldn java",
			"mldn魔乐科技", "mldn李兴华", "mldn job" };
	private AutoCompleteTextView myauto = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, DATA); // 数据集
		this.myauto = (AutoCompleteTextView) super.findViewById(R.id.myauto); // 取得组件
		this.myauto.setAdapter(adapter); // 设置内容
	}
}