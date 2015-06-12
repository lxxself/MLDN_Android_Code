package org.lxh.demo;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MyTabHostDemo extends TabActivity { // 继承了TabActivity
	private TabHost myTabHost;
	private int[] layRes = new int[] { R.id.tab_edit, R.id.tab_clock,
			R.id.tab_sex }; // 这些是内嵌布局文件的ID

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.myTabHost = super.getTabHost(); // 取得TabHost对象
		LayoutInflater.from(this).inflate(R.layout.tab,
				this.myTabHost.getTabContentView(), true); // true表示实例化布局文件中的组件
		for (int x = 0; x < this.layRes.length; x++) {
			TabSpec myTab = this.myTabHost.newTabSpec("tab" + x) ;
			myTab.setIndicator("标签  - " + x) ;
			myTab.setContent(this.layRes[x]) ;
			this.myTabHost.addTab(myTab) ;
		}
	}
}