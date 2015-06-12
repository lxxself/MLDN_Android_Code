package org.lxh.demo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MyVideoPlayerDemo extends Activity {
	private ImageButton play = null ;
	private ImageButton stop = null ;
	private MediaPlayer media = null ;
	private SurfaceView surfaceView = null ;
	private SurfaceHolder surfaceHolder = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.play = (ImageButton) super.findViewById(R.id.play) ;
		this.stop = (ImageButton) super.findViewById(R.id.stop) ;
		this.surfaceView = (SurfaceView) super.findViewById(R.id.surfaceView) ;
		this.surfaceHolder = this.surfaceView.getHolder() ;
		this.surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		this.media = new MediaPlayer() ;
		try {
			this.media.setDataSource("/sdcard/mldn.3gp") ;	// 少了一些判断
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.play.setOnClickListener(new PlayOnClickListenerImpl()) ;
		this.stop.setOnClickListener(new StopOnClickListenerImpl()) ;
	}
	private class PlayOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			MyVideoPlayerDemo.this.media.setAudioStreamType(AudioManager.STREAM_MUSIC) ;
			MyVideoPlayerDemo.this.media.setDisplay(MyVideoPlayerDemo.this.surfaceHolder) ;
			try {
				MyVideoPlayerDemo.this.media.prepare() ;
			} catch (Exception e) {
			}
			MyVideoPlayerDemo.this.media.start() ;
		}
		
	}
	private class StopOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			MyVideoPlayerDemo.this.media.stop() ;
		}
		
	}
}