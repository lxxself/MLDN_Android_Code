package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MyLongClickDemo extends Activity {
	private TextView info = null;
	private ImageView img = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.img = (ImageView) super.findViewById(R.id.img);
		this.info = (TextView) super.findViewById(R.id.info);
		this.img.setOnLongClickListener(new OnLongClickListenerImpl());
	}

	private class OnLongClickListenerImpl implements OnLongClickListener {

		public boolean onLongClick(View v) {
			try {
				MyLongClickDemo.this.clearWallpaper(); // 清除已有的桌面
				MyLongClickDemo.this.setWallpaper(MyLongClickDemo.this.img
						.getResources().openRawResource(R.drawable.mldn_bg)); // 设置桌面
				MyLongClickDemo.this.info.setText("手机桌面背景已修改。");
			} catch (Exception e) {
				e.printStackTrace() ;
				MyLongClickDemo.this.info.setText("手机桌面背景设置失败。");
			}
			return false;
		}
	}
}