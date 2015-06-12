package org.lxh.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class MyTextSwitcherDemo extends Activity {
	private TextSwitcher myTextSwitcher = null;
	private Button but = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.myTextSwitcher = (TextSwitcher) super
				.findViewById(R.id.myTextSwitcher);
		this.but = (Button) super.findViewById(R.id.but);
		this.myTextSwitcher.setFactory(new ViewFactoryImpl());
		this.myTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		this.myTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		this.but.setOnClickListener(new OnClickListenerImpl());
	}

	private class OnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			MyTextSwitcherDemo.this.myTextSwitcher.setText("当前时间为："
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
							.format(new Date()));
		}
	}

	private class ViewFactoryImpl implements ViewFactory {
		@Override
		public View makeView() {
			TextView txt = new TextView(MyTextSwitcherDemo.this);
			txt.setBackgroundColor(0xFFFFFFFF);
			txt.setTextColor(0xFF000000);
			txt.setLayoutParams(new TextSwitcher.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			txt.setTextSize(30);
			return txt;
		}

	}
}