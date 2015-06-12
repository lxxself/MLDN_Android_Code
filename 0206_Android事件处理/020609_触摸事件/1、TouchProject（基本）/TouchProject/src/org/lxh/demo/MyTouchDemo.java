package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MyTouchDemo extends Activity {
	private TextView info = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.info = (TextView) super.findViewById(R.id.info);
		this.info.setOnTouchListener(new OnTouchListenerImpl());
	}

	private class OnTouchListenerImpl implements OnTouchListener {

		public boolean onTouch(View v, MotionEvent event) {
			MyTouchDemo.this.info.setText("X = " + event.getX() + "£¨Y = "
					+ event.getY()); // …Ë÷√Œƒ±æ
			return false;
		}
	}
}