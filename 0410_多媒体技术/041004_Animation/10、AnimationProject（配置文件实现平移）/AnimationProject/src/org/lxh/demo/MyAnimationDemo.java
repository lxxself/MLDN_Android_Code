package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
			Animation anim = AnimationUtils.loadAnimation(MyAnimationDemo.this,
					R.anim.translate);
			MyAnimationDemo.this.mldn.startAnimation(anim); // Æô¶¯¶¯»­
		}
	} 
}