package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
			Uri uri = Uri.parse("file:///sdcard/mypic.jpg") ;	// 设置操作的路径
			Intent it = new Intent() ; 
			it.setAction(Intent.ACTION_SEND) ;	// 设置要操作的Action
			it.putExtra("address","13683527621") ;	// 设置短信内容
			it.putExtra("sms_body","北京魔乐科技软件学院") ;	// 设置短信内容
			it.putExtra(Intent.EXTRA_STREAM, uri); // 设置短信内容
			it.setType("image/png") ;	// 短信的MIME类型
			MyIntentCaseDemo.this.startActivity(it) ;	// 执行跳转
		}
		
	}
}