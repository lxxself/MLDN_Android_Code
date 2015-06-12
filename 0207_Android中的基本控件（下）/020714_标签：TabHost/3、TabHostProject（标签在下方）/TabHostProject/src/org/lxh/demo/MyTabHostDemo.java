package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MyTabHostDemo extends Activity { // 继承了TabActivity
	private TabHost myTabHost;
	private int[] layRes = new int[] { R.id.tab_edit, R.id.tab_clock,
			R.id.tab_sex }; // 这些是内嵌布局文件的ID
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.tab) ; 
		this.myTabHost = (TabHost) super.findViewById(R.id.tabhost) ;
		this.myTabHost.setup() ;	// 建立TabHost对象 
		for (int x = 0; x < this.layRes.length; x++) {
			TabSpec myTab = this.myTabHost.newTabSpec("tab" + x) ;
			myTab.setIndicator("标签  - " + x) ;
			myTab.setContent(this.layRes[x]) ;
			this.myTabHost.addTab(myTab) ;
		}
		this.myTabHost.setCurrentTab(0) ;	// 默认显示的标签索引
	}
}