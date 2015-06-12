package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MyIntentCaseDemo extends Activity {
	private Button mybut = null ;
	private EditText tel = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.mybut = (Button) super.findViewById(R.id.mybut) ;	// 取得组件
		this.tel = (EditText) super.findViewById(R.id.tel) ;	// 取得组件
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			String telStr = MyIntentCaseDemo.this.tel.getText().toString() ;	// 取得输入信息
			Uri uri = Uri.parse("tel:" + telStr) ;	// 设置操作的路径
			Intent it = new Intent() ;
			it.setAction(Intent.ACTION_DIAL) ;	// 设置要操作的Action
			it.setData(uri) ;	// 要设置的数据
			MyIntentCaseDemo.this.startActivity(it) ;	// 执行跳转
		}
		
	}
}