package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
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
			TranslateAnimation tran = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF,0.0f ,	// X轴开始位置
					Animation.RELATIVE_TO_SELF,0.5f ,	// X轴移动的结束位置
					Animation.RELATIVE_TO_SELF,0.0f ,	// Y轴开始位置
					Animation.RELATIVE_TO_SELF,1.5f );	// Y轴移动位置
			tran.setDuration(3000); // 3秒完成动画
			set.addAnimation(tran); // 增加动画
			MyAnimationDemo.this.mldn.startAnimation(set); // 启动动画
		}
	}
}