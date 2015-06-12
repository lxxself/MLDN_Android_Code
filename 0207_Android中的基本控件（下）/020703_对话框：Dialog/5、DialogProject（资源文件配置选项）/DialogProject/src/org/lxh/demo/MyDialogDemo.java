package org.lxh.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyDialogDemo extends Activity {
	private Button mybut = null ;	// 定义按钮
	private TextView mych = null ;	// 定义文本
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main); // 调用布局管理器
		this.mybut = (Button) super.findViewById(R.id.mybut) ;	// 取得按钮
		this.mych = (TextView) super.findViewById(R.id.mych) ;	// 取得文本
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;	// 设置事件类
	}
	private class OnClickListenerImpl implements OnClickListener {
 
		@Override
		public void onClick(View view) {
			Dialog dialog = new AlertDialog.Builder(MyDialogDemo.this)
				.setIcon(R.drawable.pic_m) 
				.setTitle("请选择你喜欢吃的水果？")
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).setItems(R.array.fruit_labels, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
									MyDialogDemo.this.mych
											.setText("您选择的水果是："
													+ MyDialogDemo.this
															.getResources()
															.getStringArray(
																	R.array.fruit_labels)[which]);
					}
				}).create() ;
			dialog.show() ;
		}
		
	}

}