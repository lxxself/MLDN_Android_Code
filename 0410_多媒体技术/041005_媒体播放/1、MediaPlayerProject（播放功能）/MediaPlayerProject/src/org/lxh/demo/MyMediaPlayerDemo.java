package org.lxh.demo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MyMediaPlayerDemo extends Activity {
	private ImageButton play = null ;
	private ImageButton pause = null ;
	private ImageButton stop = null ;
	private TextView info = null ;
	private MediaPlayer myMediaPlayer = null ;
	private SeekBar seekbar = null ;
	private boolean playFlag = true ;	// 播放标记
	private boolean pauseFlag = true ;	// 暂停标记
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.info = (TextView) super.findViewById(R.id.info) ;
		this.play = (ImageButton) super.findViewById(R.id.play) ;
		this.pause = (ImageButton) super.findViewById(R.id.pause) ;
		this.stop = (ImageButton) super.findViewById(R.id.stop) ;
		this.seekbar = (SeekBar) super.findViewById(R.id.seekbar) ;
		this.play.setOnClickListener(new PlayOnClickListener()) ;
		this.pause.setOnClickListener(new PauseOnClickListener()) ;
		this.stop.setOnClickListener(new StopOnClickListener()) ;
		this.seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpl()) ;
	}
	
	private class PlayOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			MyMediaPlayerDemo.this.myMediaPlayer = MediaPlayer.create(
					MyMediaPlayerDemo.this, R.raw.mldn_ad);	// 要播放的文件
			if (MyMediaPlayerDemo.this.myMediaPlayer != null) {
				MyMediaPlayerDemo.this.myMediaPlayer.stop(); // 停止操作
			}
			MyMediaPlayerDemo.this.myMediaPlayer.setOnCompletionListener(new OnCompletionListener(){

				@Override
				public void onCompletion(MediaPlayer mp) {
					MyMediaPlayerDemo.this.playFlag = false ;	// 播放完毕
					MyMediaPlayerDemo.this.myMediaPlayer.release() ;	// 释放资源
				}}) ;
			try {
				MyMediaPlayerDemo.this.myMediaPlayer.prepare() ;
				MyMediaPlayerDemo.this.myMediaPlayer.start() ;
				MyMediaPlayerDemo.this.info.setText("正在播放音频文件...") ;
			} catch (Exception e) {
				MyMediaPlayerDemo.this.info.setText("文件播放出现异常，" + e) ;
			}
			
		}
		
	}
	private class PauseOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(MyMediaPlayerDemo.this.myMediaPlayer != null) {
				if (MyMediaPlayerDemo.this.pauseFlag) { // 现在暂停
					MyMediaPlayerDemo.this.myMediaPlayer.start();
					MyMediaPlayerDemo.this.pauseFlag = false ;
				} else {
					MyMediaPlayerDemo.this.myMediaPlayer.pause(); // 暂停
					MyMediaPlayerDemo.this.pauseFlag = true ;
				}
			}
		}
		
	}
	private class StopOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(MyMediaPlayerDemo.this.myMediaPlayer != null) {
				MyMediaPlayerDemo.this.myMediaPlayer.stop() ;	// 停止
				MyMediaPlayerDemo.this.info.setText("停止播放音频文件...") ;
			}
		}
	}
	private class OnSeekBarChangeListenerImpl implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			
		}
		
	}
}