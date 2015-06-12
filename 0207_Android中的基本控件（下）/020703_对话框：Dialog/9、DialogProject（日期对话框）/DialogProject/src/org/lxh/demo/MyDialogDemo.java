package org.lxh.demo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

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
			Dialog dialog = new DatePickerDialog(MyDialogDemo.this,new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					TextView text = (TextView) MyDialogDemo.this.findViewById(R.id.txt) ;
					text.setText("更新的日期为：" + year + "-" + monthOfYear + "-" + dayOfMonth) ;
				}
			},1981,8,19) ; // 默认的年、月、日
			dialog.show() ;	// 显示对话框
		}
		
	}

}