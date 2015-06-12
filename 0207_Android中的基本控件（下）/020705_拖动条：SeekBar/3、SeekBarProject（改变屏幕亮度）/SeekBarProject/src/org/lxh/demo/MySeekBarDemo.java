package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;

public class MySeekBarDemo extends Activity {
	private SeekBar seekbar = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.seekbar = (SeekBar) super.findViewById(R.id.seekbar); // 取得组件
		this.seekbar.setMax(100);
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
			MySeekBarDemo.this.setScreenBrightness((float) seekBar
					.getProgress() / 100);
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
	}
	private void setScreenBrightness(float num) {	// 0 ~ 1表示亮度
		WindowManager.LayoutParams layoutParams = super.getWindow().getAttributes() ;	// 取得屏幕的属性
		layoutParams.screenBrightness = num ;	// 设置屏幕亮度
		super.getWindow().setAttributes(layoutParams) ;	// 重新设置窗口的属性
	}
}