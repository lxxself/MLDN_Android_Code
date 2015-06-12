package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MySeekBarDemo extends Activity {
	private SeekBar seekbar = null;
	private ImageView pic = null;
	private int picData[] = new int[] { R.drawable.pic_0, R.drawable.pic_1,
			R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_4,
			R.drawable.pic_5, R.drawable.pic_6, R.drawable.pic_7,
			R.drawable.pic_8, R.drawable.pic_9 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.seekbar = (SeekBar) super.findViewById(R.id.seekbar); // 取得组件
		this.pic = (ImageView) super.findViewById(R.id.pic); // 取得组件
		this.seekbar.setMax(9); // 0 ~ 9的范围
		this.seekbar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpl());
	}

	private class OnSeekBarChangeListenerImpl implements
			SeekBar.OnSeekBarChangeListener {
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			MySeekBarDemo.this.pic
					.setImageResource(MySeekBarDemo.this.picData[seekBar
							.getProgress()]);// 设置显示图片
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

	}
}