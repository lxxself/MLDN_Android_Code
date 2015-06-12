package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setTitle("�鿴ͼƬ") ;
		ImageView img = new ImageView(this) ;
		img.setImageResource(R.drawable.mldn_ad) ;
		super.setContentView(img) ;
	}
}