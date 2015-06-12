package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.SeekBar;
import android.widget.TextView;

public class MySeekBarDemo extends Activity {
	private SeekBar seekbar = null;
	private TextView text = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.seekbar = (SeekBar) super.findViewById(R.id.seekbar); // 取得组件
		this.text = (TextView) super.findViewById(R.id.text); // 取得组件
		this.text.setMovementMethod(ScrollingMovementMethod.getInstance()); // 文本组件的内容可以滚动
		this.seekbar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpl());
	}

	private class OnSeekBarChangeListenerImpl implements
			SeekBar.OnSeekBarChangeListener {
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			MySeekBarDemo.this.text.append("*** 开始拖动，当前值："
					+ seekBar.getProgress() + "\n");
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			MySeekBarDemo.this.text.append("*** 正在拖动，当前值："
					+ seekBar.getProgress() + "\n");
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			MySeekBarDemo.this.text.append("*** 停止拖动，当前值："
					+ seekBar.getProgress() + "\n");
		}

	}
}