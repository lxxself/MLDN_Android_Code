package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyBroadcastDemo extends Activity {
	private Button mybut = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		this.mybut = (Button) super.findViewById(R.id.mybut);
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;
	}
	
	private class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent it = new Intent(Intent.ACTION_EDIT); // ²Ù×÷µÄ¹ýÂË
			MyBroadcastDemo.this.sendBroadcast(it) ;
		}
		
	}
}