package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class MyAnimationDemo extends Activity {
	private GridView myGridView = null ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.myGridView = (GridView) super.findViewById(R.id.myGridView) ;
		this.myGridView.setAdapter(new ImageAdapter(this)) ;
	}
}