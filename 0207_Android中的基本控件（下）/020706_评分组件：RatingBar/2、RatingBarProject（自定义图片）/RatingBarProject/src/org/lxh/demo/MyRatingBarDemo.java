package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class MyRatingBarDemo extends Activity {
	private RatingBar ratingBar = null;
	private TextView text = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.ratingBar = (RatingBar) super.findViewById(R.id.ratingbar);
		this.text = (TextView) super.findViewById(R.id.text);
		this.ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListenerImpl()) ;
	}
	private class OnRatingBarChangeListenerImpl implements OnRatingBarChangeListener {
		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			int num = (int) rating;
			String result = null ;	// 保存文字信息
			switch(num) {
			case 5:
				result = "非常满意" ;
				break ;
			case 4:
				result = "满意" ;
				break ;
			case 3:
				result = "还可以" ;
				break ;
			case 2:
				result = "不满意" ;
				break ;
			case 1:
				result = "非常不满意" ;
				break ;
			}
			MyRatingBarDemo.this.text.setText(result) ;
		}
	}
	
}