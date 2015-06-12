package org.lxh.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
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
			LayoutInflater factory = LayoutInflater.from(MyDialogDemo.this) ;
			View myView = factory.inflate(R.layout.login, null) ;
			Dialog dialog = new AlertDialog.Builder(MyDialogDemo.this)
				.setIcon(R.drawable.pic_m) 
				.setTitle("用户登录")
				.setPositiveButton("登录", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).setView(myView).create();
			dialog.show() ;
		}
		
	}

}