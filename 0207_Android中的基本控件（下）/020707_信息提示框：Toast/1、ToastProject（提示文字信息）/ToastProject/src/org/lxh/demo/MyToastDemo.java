package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
public class MyToastDemo extends Activity {
	private Button butA = null;
	private Button butB = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.butA = (Button) super.findViewById(R.id.butA);
		this.butB = (Button) super.findViewById(R.id.butB);
		this.butA.setOnClickListener(new OnClickListenerImplLong()) ;
		this.butB.setOnClickListener(new OnClickListenerImplShort()) ;
	}

	private class OnClickListenerImplLong implements OnClickListener {

		@Override
		public void onClick(View v) {
			Toast.makeText(MyToastDemo.this, "长时间显示的Toast信息提示框",
					Toast.LENGTH_LONG).show();
		}

	}

	private class OnClickListenerImplShort implements OnClickListener {

		@Override
		public void onClick(View v) {
			Toast.makeText(MyToastDemo.this, "短时间显示的Toast信息提示框",
					Toast.LENGTH_SHORT).show();
		}

	}
}