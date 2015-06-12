package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MyIntentCaseDemo extends Activity {
	private ImageButton mybut = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.mybut = (ImageButton) super.findViewById(R.id.mybut);
		this.mybut.setOnClickListener(new OnClickListenerImpl());
	}

	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
			MyIntentCaseDemo.this.startActivity(Intent.createChooser(intent,
					"—°‘ÒÕº∆¨‰Ø¿¿π§æﬂ"));
		}

	}
}