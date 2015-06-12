package org.lxh.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.ClipboardManager;

public class MyClipboardDemo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		ClipboardManager clipboardManager = (ClipboardManager) super
				.getSystemService(Context.CLIPBOARD_SERVICE);	// 取得服务
		clipboardManager.setText("北京魔乐科技软件学院（MLDN）") ;
	}
}