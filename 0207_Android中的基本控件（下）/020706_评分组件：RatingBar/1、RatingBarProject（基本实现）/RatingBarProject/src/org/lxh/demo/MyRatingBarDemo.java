package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class MyRatingBarDemo extends Activity {
	private RatingBar ratingBarA = null;
	private TextView text = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.ratingBarA = (RatingBar) super.findViewById(R.id.ratingbarA);
		this.text = (TextView) super.findViewById(R.id.text);
		this.ratingBarA.setOnRatingBarChangeListener(new OnRatingBarChangeListenerImpl()) ;
	}
	private class OnRatingBarChangeListenerImpl implements OnRatingBarChangeListener {
		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			MyRatingBarDemo.this.text.append("*** 当前值（Rating）："
					+ ratingBar.getRating() + "，步长：" + ratingBar.getStepSize()
					+ "\n");
		}
	}
	
}