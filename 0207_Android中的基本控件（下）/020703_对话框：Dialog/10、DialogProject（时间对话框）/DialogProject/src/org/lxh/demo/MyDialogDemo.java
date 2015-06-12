package org.lxh.demo;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

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
			Dialog dialog = new TimePickerDialog(MyDialogDemo.this,new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					TextView text = (TextView) MyDialogDemo.this.findViewById(R.id.txt) ;
					text.setText("时间为：" + hourOfDay + ":" + minute) ;
				}
			},19,20,true) ;
			dialog.show() ;	// 显示对话框
		}
		
	}

}