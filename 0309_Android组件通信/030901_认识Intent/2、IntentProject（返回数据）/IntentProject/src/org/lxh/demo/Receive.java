package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Receive extends Activity {
	private TextView show = null ;
	private Button retbut = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.receive_main);
		this.show = (TextView) super.findViewById(R.id.show) ;
		this.retbut = (Button) super.findViewById(R.id.retbut) ;
		Intent it = super.getIntent() ;	// 取得当前的Intent
		String info = it.getStringExtra("myinfo") ;
		this.show.setText(info) ; 
		this.retbut.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			Receive.this.getIntent().putExtra("retmsg", "老师：李兴华") ;
			Receive.this.setResult(RESULT_OK, Receive.this.getIntent()) ;
			Receive.this.finish() ;
		}
		
	}
}