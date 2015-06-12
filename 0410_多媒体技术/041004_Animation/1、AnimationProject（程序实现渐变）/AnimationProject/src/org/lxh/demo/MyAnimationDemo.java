package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

public class MyAnimationDemo extends Activity {
	private ImageView mldn = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.mldn = (ImageView) super.findViewById(R.id.mldn);
		this.mldn.setOnClickListener(new OnClickListenerImpl());
	}

	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			AnimationSet set = new AnimationSet(true);
			AlphaAnimation alpha = new AlphaAnimation(1, 0); // 由完全显示 --> 完全透明
			alpha.setDuration(3000); // 3秒完成动画
			set.addAnimation(alpha); // 增加动画
			MyAnimationDemo.this.mldn.startAnimation(set); // 启动动画
		}

	}
}