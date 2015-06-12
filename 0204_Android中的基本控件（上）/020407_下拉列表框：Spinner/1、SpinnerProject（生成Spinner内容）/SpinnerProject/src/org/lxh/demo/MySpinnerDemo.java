package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MySpinnerDemo extends Activity {
	private Spinner spiColor = null; // 表示要读取的颜色列表框
	private ArrayAdapter<CharSequence> adapterColor = null; // 所有的数据都是String

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.spiColor = (Spinner) super.findViewById(R.id.mycolor); // 取得颜色的下拉框
		this.spiColor.setPrompt("请选择您喜欢的颜色：");
		this.adapterColor = ArrayAdapter.createFromResource(this,
				R.array.color_labels, android.R.layout.simple_spinner_item); // 实例化了ArrayAdapter
		this.adapterColor 
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // 换个风格
		this.spiColor.setAdapter(this.adapterColor); // 设置显示信息
	}
}