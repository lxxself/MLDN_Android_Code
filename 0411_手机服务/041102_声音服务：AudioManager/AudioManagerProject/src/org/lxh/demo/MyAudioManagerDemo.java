package org.lxh.demo;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MyAudioManagerDemo extends Activity {
	private ImageButton voiceOn = null ;
	private ImageButton voiceOff = null ;
	private ImageButton voiceVibrate = null ;
	private ImageButton voiceLower = null ;
	private ImageButton voiceRaise = null ;
	private AudioManager audio = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.voiceOn = (ImageButton) super.findViewById(R.id.voiceon) ;
		this.voiceOff = (ImageButton) super.findViewById(R.id.voiceoff) ;
		this.voiceVibrate = (ImageButton) super.findViewById(R.id.voicevibrate) ;
		this.voiceLower = (ImageButton) super.findViewById(R.id.voicelower) ;
		this.voiceRaise = (ImageButton) super.findViewById(R.id.voiceraise) ;
		this.audio = (AudioManager) super
				.getSystemService(Context.AUDIO_SERVICE);
		this.voiceOn.setOnClickListener(new VoiceOnOnClickListenerImpl()) ;
		this.voiceOff.setOnClickListener(new VoiceOffOnClickListenerImpl()) ;
		this.voiceVibrate.setOnClickListener(new VoiceVibrateOnClickListenerImpl()) ;
		this.voiceLower.setOnClickListener(new VoiceLowerOnClickListenerImpl()) ;
		this.voiceRaise.setOnClickListener(new VoiceRaiseOnClickListenerImpl()) ;
	}
	private void playAudio(){
		MediaPlayer media = MediaPlayer.create(this, R.raw.mldn_java);
		media.setLooping(true);// 循环播放
		try {
			media.prepare() ;
		} catch (Exception e) {
		}
		media.start() ;
	}
	private class VoiceOnOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyAudioManagerDemo.this.audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL) ;
			Toast.makeText(MyAudioManagerDemo.this, "手机音量开启！", Toast.LENGTH_SHORT).show() ;
		}
		
	}
	private class VoiceOffOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyAudioManagerDemo.this.audio.setRingerMode(AudioManager.RINGER_MODE_SILENT) ;
			Toast.makeText(MyAudioManagerDemo.this, "手机静音！", Toast.LENGTH_SHORT).show() ;
		}
		
	}
	private class VoiceVibrateOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyAudioManagerDemo.this.audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE) ;
			Toast.makeText(MyAudioManagerDemo.this, "手机为震动模式！", Toast.LENGTH_SHORT).show() ;
		}
		
	}
	private class VoiceLowerOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyAudioManagerDemo.this.audio.adjustVolume(AudioManager.ADJUST_LOWER, 0) ;
			Toast.makeText(MyAudioManagerDemo.this, "音量减小！", Toast.LENGTH_SHORT).show() ;
		}
		
	}
	private class VoiceRaiseOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyAudioManagerDemo.this.audio.adjustVolume(AudioManager.ADJUST_RAISE, 0) ;
			Toast.makeText(MyAudioManagerDemo.this, "音量增加！", Toast.LENGTH_SHORT).show() ;
		}
		
	}
}