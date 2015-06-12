package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Send extends Activity {
	private Button mybut = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.send_main);
		this.mybut = (Button) super.findViewById(R.id.mybut) ;
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent it = new Intent(Send.this, Receive.class);
			it.putExtra("myinfo", "北京魔乐科技软件学院（www.mldnjava.cn）") ;
			Send.this.startActivity(it) ;	// 跳转
		}
		
	}
}