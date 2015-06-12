package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MyPopupWindowDemo extends Activity {
	private Button popbut = null;
	private TextView statusinfo = null;
	private View popView = null ;
	private PopupWindow popWin = null ;
	private RadioGroup changestatus = null ;
	private Button cancel = null ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.popbut = (Button) super.findViewById(R.id.popbut);
		this.statusinfo = (TextView) super.findViewById(R.id.statusinfo);
		
		this.popbut.setOnClickListener(new OnClickListenerImpl()) ;
	}
	
	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			LayoutInflater inflater = LayoutInflater.from(MyPopupWindowDemo.this) ;
			MyPopupWindowDemo.this.popView = inflater.inflate(
					R.layout.popwindow, null);	// 找到了布局文件中的View
			MyPopupWindowDemo.this.popWin = new PopupWindow(
					MyPopupWindowDemo.this.popView, 300, 220, true);
			MyPopupWindowDemo.this.changestatus = (RadioGroup) MyPopupWindowDemo.this.popView
					.findViewById(R.id.changestatus);	// 取得弹出界面中的组件
			MyPopupWindowDemo.this.cancel = (Button) MyPopupWindowDemo.this.popView
					.findViewById(R.id.cancel);
			MyPopupWindowDemo.this.changestatus
					.setOnCheckedChangeListener(new OnCheckedChangeListenerImpl());
			MyPopupWindowDemo.this.cancel.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					MyPopupWindowDemo.this.popWin.dismiss() ;	// 不显示
				}}) ;
			MyPopupWindowDemo.this.popWin.showAtLocation(
					MyPopupWindowDemo.this.popbut, Gravity.CENTER, 0, 0);
		}
	}
	private class OnCheckedChangeListenerImpl implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			RadioButton but = (RadioButton) MyPopupWindowDemo.this.popView.findViewById(group
					.getCheckedRadioButtonId());	// 取得指定的单选钮被选中
			MyPopupWindowDemo.this.statusinfo.setText("当前用户状态：" + but.getText().toString()) ;
			MyPopupWindowDemo.this.popWin.dismiss() ;
		}
		
	}
}