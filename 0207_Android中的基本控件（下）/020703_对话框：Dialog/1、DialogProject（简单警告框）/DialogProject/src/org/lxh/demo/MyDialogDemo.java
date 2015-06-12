package org.lxh.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

public class MyDialogDemo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main); // 调用布局管理器
		Dialog dialog = new AlertDialog.Builder(this)
			.setTitle("对话框")		// 创建标题
			.setMessage("显示提示信息") // 表示对话框中的内容
			.setIcon(R.drawable.pic_m) // 设置LOGO
			.create(); // 创建了一个对话框
		dialog.show() ;	// 显示对话框
	}
}