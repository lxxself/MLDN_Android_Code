package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;

public class MyGalleryDemo extends Activity {
	private Gallery gallery = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.gallery = (Gallery) super.findViewById(R.id.myGallery);
		this.gallery.setAdapter(new ImageGalleryAdapter(this));
		this.gallery.setOnItemClickListener(new OnItemClickListenerImpl()) ;
	}

	private class OnItemClickListenerImpl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(MyGalleryDemo.this, String.valueOf(position),
					Toast.LENGTH_SHORT).show();
		}
	}
}