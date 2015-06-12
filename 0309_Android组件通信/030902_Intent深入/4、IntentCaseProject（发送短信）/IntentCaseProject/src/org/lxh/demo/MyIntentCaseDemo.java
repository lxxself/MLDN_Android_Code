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
	private EditText content = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.mybut = (Button) super.findViewById(R.id.mybut) ;	// 取得组件
		this.tel = (EditText) super.findViewById(R.id.tel) ;	// 取得组件
		this.content = (EditText) super.findViewById(R.id.content) ;	// 取得组件
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			String telStr = MyIntentCaseDemo.this.tel.getText().toString() ;	// 取得输入信息
			String note = MyIntentCaseDemo.this.content.getText().toString() ;	// 取得内容
			Uri uri = Uri.parse("smsto:" + telStr) ;	// 设置操作的路径
			Intent it = new Intent() ; 
			it.setAction(Intent.ACTION_SENDTO) ;	// 设置要操作的Action
			it.putExtra("sms_body",note) ;	// 设置短信内容
			it.setType("vnd.android-dir/mms-sms") ;	// 短信的MIME类型
			it.setData(uri) ;	// 要设置的数据
			MyIntentCaseDemo.this.startActivity(it) ;	// 执行跳转
		}
		
	}
}