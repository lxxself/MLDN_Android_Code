package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyIntentCaseDemo extends Activity {
	private Button mybut = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.mybut = (Button) super.findViewById(R.id.mybut) ;	// 取得组件
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent emailIntent = new Intent(Intent.ACTION_SEND) ;
			emailIntent.setType("plain/text") ;	// 设置类型
			String address[] = new String[] {"mldnqa@163.com"} ;	// 邮件地址
			String subject = "北京魔乐科技软件学院（MLDN）" ;
			String content = "www.mldnjava.cn" ;
			emailIntent.putExtra(Intent.EXTRA_EMAIL, address) ;
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject) ;
			emailIntent.putExtra(Intent.EXTRA_TEXT, content) ;
			MyIntentCaseDemo.this.startActivity(emailIntent) ;	// 执行跳转
		}
		
	}
}