package org.lxh.demo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyDialogDemo extends Activity {
	private Button mybut = null ;	// 定义按钮
	private static final int MAX_PROGRESS = 100 ;	// 最大值
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
			final ProgressDialog proDia = new ProgressDialog(MyDialogDemo.this) ;
			proDia.setTitle("搜索网络") ;
			proDia.setMessage("请耐心等待") ;
			proDia.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL) ;	// 水平进度条
			proDia.setMax(MAX_PROGRESS) ;	// 设置进度的最大值
			proDia.setProgress(30) ;	// 从进度30开始
			proDia.setButton("后台处理", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					proDia.dismiss() ;	// 关闭对话框
				}
			}) ;
			proDia.setButton2("详细信息", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			}) ;
			proDia.onStart() ;	// 启动进度
			new Thread(){
				public void run(){	// 线程的主体类
					for (int x = 0; x < MAX_PROGRESS; x++) {
						try {
							Thread.sleep(500); // 运行三秒
						} catch (Exception e) {
						} 
						proDia.incrementProgressBy(1) ;
					}
					proDia.dismiss() ;
				}
			}.start() ;
			proDia.show() ;	// 显示对话框
		}
		
	}

}