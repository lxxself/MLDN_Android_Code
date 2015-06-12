package org.lxh.demo;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MyAnimationDemo extends Activity {
	private ImageView face = null;
	private Button start = null ;
	private AnimationDrawable draw = null ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.face = (ImageView) super.findViewById(R.id.face);
		this.start = (Button) super.findViewById(R.id.start);
		this.start.setOnClickListener(new OnClickListenerImpl()) ;
	}

	private class OnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			MyAnimationDemo.this.face.setBackgroundResource(R.anim.allface) ;
			MyAnimationDemo.this.draw = (AnimationDrawable) MyAnimationDemo.this.face
					.getBackground();
			MyAnimationDemo.this.draw.setOneShot(false) ;
			MyAnimationDemo.this.draw.start() ;
		}
	} 
}