package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
public class MySpinnerListenerDemo extends Activity {
	private Spinner city = null; // 下拉列表框内容
	private Spinner area = null; // 二级列表
	private String[][] areaData = new String[][] {
			{ "东城", "西城", "朝阳", "大兴", "平谷" }, // 针对于一级的子信息
			{ "黄浦", "杨浦", "闵行" }, // 针对于二级的子信息
			{ "广州" } // 针对于三级的子信息
	}; // 子菜单项
	private ArrayAdapter<CharSequence> adapterArea = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.city = (Spinner) super.findViewById(R.id.city); // 取得下拉列表框
		this.area = (Spinner) super.findViewById(R.id.area); // 取得下拉列表框
		this.city.setOnItemSelectedListener(new OnItemSelectedListenerImpl());
	}

	private class OnItemSelectedListenerImpl implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) { // 表示选项改变的时候触发
			MySpinnerListenerDemo.this.adapterArea = new ArrayAdapter<CharSequence>(
					MySpinnerListenerDemo.this,
					android.R.layout.simple_spinner_item,
					MySpinnerListenerDemo.this.areaData[position]); // 定义所有的列表项
			MySpinnerListenerDemo.this.area
					.setAdapter(MySpinnerListenerDemo.this.adapterArea);// 设置二级下拉列表的选项内容
		}

		public void onNothingSelected(AdapterView<?> arg0) { // 表示没有选项的时候触发
			// 一般此方法现在不关心
		}
	}
}