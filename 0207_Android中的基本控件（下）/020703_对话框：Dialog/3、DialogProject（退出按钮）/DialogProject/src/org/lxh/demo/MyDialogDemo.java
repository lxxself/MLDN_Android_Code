package org.lxh.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MyDialogDemo extends Activity {
	private ImageButton but = null ;	// 定义按钮
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main); // 调用布局管理器
		this.but = (ImageButton) super.findViewById(R.id.but) ;	// 取得按钮
		this.but.setOnClickListener(new OnClickListenerImpl()) ;	// 设置事件类
	}
	private class OnClickListenerImpl implements OnClickListener {
 
		@Override
		public void onClick(View view) {
			MyDialogDemo.this.exitDialog() ;
		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {	// 返回键
			this.exitDialog() ;
		}
		return false ;
	}
	private void exitDialog(){
		Dialog dialog = new AlertDialog.Builder(MyDialogDemo.this)
			.setTitle("程序退出？")		// 创建标题
			.setMessage("您确定要退出本程序吗？") // 表示对话框中的内容
			.setIcon(R.drawable.pic_m) // 设置LOGO
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					MyDialogDemo.this.finish() ;	// 操作结束
				}
			}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			}).create(); // 创建了一个对话框
		dialog.show() ;	// 显示对话框
	}
}