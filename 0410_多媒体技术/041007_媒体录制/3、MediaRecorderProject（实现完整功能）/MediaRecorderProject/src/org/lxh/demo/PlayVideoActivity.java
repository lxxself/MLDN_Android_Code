package org.lxh.demo;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class PlayVideoActivity extends Activity {
	private ImageButton play = null;
	private ImageButton stop = null;
	private ImageButton back = null;
	private MediaPlayer media = null;
	private SurfaceView sufaceView = null;
	private SurfaceHolder surfaceHolder = null;
	private String filepath = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE); // 不显示标题
		super.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 高亮的风格显示
		super.setContentView(R.layout.play);

		this.filepath = super.getIntent().getStringExtra("filepath") ;
		
		this.play = (ImageButton) super.findViewById(R.id.play);
		this.stop = (ImageButton) super.findViewById(R.id.stop);
		this.back = (ImageButton) super.findViewById(R.id.back);
		this.sufaceView = (SurfaceView) super.findViewById(R.id.surfaceView) ;
		this.surfaceHolder = this.sufaceView.getHolder() ;
		this.surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS) ;
		this.media = new MediaPlayer() ;
		this.media.reset() ;
		try {
			this.media.setDataSource(this.filepath) ;
		} catch (Exception e) {
		}
		
		this.play.setOnClickListener(new PlayOnClickListenerImpl());
		this.stop.setOnClickListener(new StopOnClickListenerImpl());
		this.back.setOnClickListener(new BackOnClickListenerImpl());
	}

	private class PlayOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			PlayVideoActivity.this.media.setAudioStreamType(AudioManager.STREAM_MUSIC) ;
			PlayVideoActivity.this.media.setDisplay(PlayVideoActivity.this.surfaceHolder) ;
			try {
				PlayVideoActivity.this.media.prepare() ;
				PlayVideoActivity.this.media.start() ;
			} catch (Exception e) {
			}
		}
	}

	private class StopOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			PlayVideoActivity.this.media.stop() ;
		}
	}

	private class BackOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent it = new Intent(PlayVideoActivity.this, BroswerActivity.class);
			PlayVideoActivity.this.startActivity(it);
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			this.media.stop() ;
			this.media.release() ;
			super.finish() ;
		}
		return false ;
	} 
}