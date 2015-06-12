package org.lxh.demo;

import android.app.Activity;
import android.app.ProgressDialog;
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
			final ProgressDialog proDia = ProgressDialog.show(MyDialogDemo.this,
					"搜索网络", "请耐心等待...");
			new Thread(){
				public void run(){	// 线程的主体类
					try {
						Thread.sleep(3000) ;	// 运行三秒
					} catch (Exception e) {
					} finally {
						proDia.dismiss() ;	// 关闭对话框
					}
				}
			}.start() ;
			proDia.show() ;	// 显示对话框
		}
		
	}

}