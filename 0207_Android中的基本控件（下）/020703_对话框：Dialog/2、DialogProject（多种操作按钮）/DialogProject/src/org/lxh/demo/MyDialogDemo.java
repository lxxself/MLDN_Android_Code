package org.lxh.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyDialogDemo extends Activity {
	private Button mybut = null ;	// 定义按钮
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main); // 调用布局管理器
		this.mybut = (Button) super.findViewById(R.id.mybut) ;	// 取得按钮
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;	// 设置事件类
	}
	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View view) {
			Dialog dialog = new AlertDialog.Builder(MyDialogDemo.this)
				.setTitle("确定删除？")		// 创建标题
				.setMessage("您确定要删除该条信息吗？") // 表示对话框中的内容
				.setIcon(R.drawable.pic_m) // 设置LOGO
				.setPositiveButton("删除", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).setNeutralButton("查看详情", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).create(); // 创建了一个对话框
			dialog.show() ;	// 显示对话框
		}
		
	}
}