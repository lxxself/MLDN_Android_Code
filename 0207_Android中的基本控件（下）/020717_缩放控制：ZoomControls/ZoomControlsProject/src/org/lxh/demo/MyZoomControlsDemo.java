package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ZoomControls;

public class MyZoomControlsDemo extends Activity {
	private ZoomControls zoomcontrols = null ;
	private TextView text = null ;
	private int size = 10 ;	// 是保存文字大小
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.zoomcontrols = (ZoomControls) super.findViewById(R.id.zommcontrols) ;
		this.text = (TextView) super.findViewById(R.id.text) ;
		
		this.zoomcontrols.setOnZoomInClickListener(new OnZoomInClickListenerImpl()) ;
		this.zoomcontrols.setOnZoomOutClickListener(new OnZoomOutClickListenerImpl()) ;
	}
	private class OnZoomInClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyZoomControlsDemo.this.size = size + 2 ;
			MyZoomControlsDemo.this.text.setTextSize(size) ;
		}
		
	}
	private class OnZoomOutClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyZoomControlsDemo.this.size = size - 2 ;
			MyZoomControlsDemo.this.text.setTextSize(size) ;
		}
		
	}
}