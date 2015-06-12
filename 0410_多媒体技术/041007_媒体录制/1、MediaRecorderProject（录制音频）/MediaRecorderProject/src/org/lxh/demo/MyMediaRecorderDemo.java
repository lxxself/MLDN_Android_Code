package org.lxh.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MyMediaRecorderDemo extends Activity {
	private ImageButton record = null;
	private ImageButton stop = null;
	private TextView info = null;
	private ListView reclist = null;
	private SimpleAdapter recordSimpleAdapter = null;
	private MediaRecorder mediaRecorder = null;
	private boolean sdcardExists = false; // 判断sd卡是否存在
	private File recordAudioSaveFileDir = null; // 保存所有音频文件的文件夹
	private File recordAudioSaveFile = null;	// 每次保存音频文件的名称
	private String recordAudioSaveFileName = null;	// 每次保存音频文件的名称
	private String recDir = "mldnrec"; // 保存的目录名称
	private boolean isRecord = false ;	// 录音的标志
	private List<Map<String,Object>> recordFiles = null ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.record = (ImageButton) super.findViewById(R.id.record);
		this.stop = (ImageButton) super.findViewById(R.id.stop);
		this.info = (TextView) super.findViewById(R.id.info);
		this.reclist = (ListView) super.findViewById(R.id.reclist);
		// 如果存在则将状态给了sdcardExists属性
		if ((this.sdcardExists = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))) { // 判断sd卡是否存在
			this.recordAudioSaveFileDir = new File(Environment
					.getExternalStorageDirectory().toString()
					+ File.separator
					+ MyMediaRecorderDemo.this.recDir + File.separator);
			if (!this.recordAudioSaveFileDir.exists()) { // 文件夹不存在
				this.recordAudioSaveFileDir.mkdirs(); // 创建文件夹
			}
		}
		this.stop.setEnabled(false) ;	// 按钮现在不可用
		this.record.setOnClickListener(new RecordOnClickListenerImpl());
		this.stop.setOnClickListener(new StopOnClickListenerImpl());
		this.reclist.setOnItemClickListener(new OnItemClickListenerImpl()) ;
		this.getRecordFiles() ;
	}

	private class RecordOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			if(MyMediaRecorderDemo.this.sdcardExists) {	// 如果sd卡存在
				MyMediaRecorderDemo.this.recordAudioSaveFileName = MyMediaRecorderDemo.this.recordAudioSaveFileDir
						.toString()
						+ File.separator
						+ "MLDNRecord_"
						+ System.currentTimeMillis() + ".3gp";	// 每次的录音文件名称都不一样
				MyMediaRecorderDemo.this.recordAudioSaveFile = new File(
						MyMediaRecorderDemo.this.recordAudioSaveFileName);
				MyMediaRecorderDemo.this.mediaRecorder = new MediaRecorder(); // 实例化对象
				// 在进行录制之前必须配置若干个参数
				MyMediaRecorderDemo.this.mediaRecorder
						.setAudioSource(MediaRecorder.AudioSource.MIC); // 音频来源是MIC
				MyMediaRecorderDemo.this.mediaRecorder
						.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				MyMediaRecorderDemo.this.mediaRecorder
						.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
				MyMediaRecorderDemo.this.mediaRecorder
						.setOutputFile(MyMediaRecorderDemo.this.recordAudioSaveFileName);
				try {	// 进入到就绪状态
					MyMediaRecorderDemo.this.mediaRecorder.prepare() ;
				} catch (Exception e) {
					// Log.i("MyMediaRecorderDemo", e.toString()) ;
				}
				MyMediaRecorderDemo.this.mediaRecorder.start() ;	// 开始录音
				MyMediaRecorderDemo.this.info.setText("正在录音中...") ;
				MyMediaRecorderDemo.this.stop.setEnabled(true); // 停止录音按钮可以使用了
				MyMediaRecorderDemo.this.record.setEnabled(false) ;
				MyMediaRecorderDemo.this.isRecord = true ;	// 正在录音
			}
		}
	}

	private class StopOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			if(MyMediaRecorderDemo.this.isRecord) {	// 正在录音
				MyMediaRecorderDemo.this.mediaRecorder.stop() ;	// 停止
				MyMediaRecorderDemo.this.mediaRecorder.release() ;	// 释放资源
				MyMediaRecorderDemo.this.record.setEnabled(true) ;
				MyMediaRecorderDemo.this.stop.setEnabled(false) ;
				MyMediaRecorderDemo.this.info.setText("录音结束，文件路径为："
						+ MyMediaRecorderDemo.this.recordAudioSaveFileName);
				MyMediaRecorderDemo.this.getRecordFiles() ;
			}
		}
	}
	
	private void getRecordFiles(){	// 将一个文件夹之中的全部文件列出
		this.recordFiles = new ArrayList<Map<String, Object>>();
		if(this.sdcardExists) {	// 有sd卡存在
			File files [] = this.recordAudioSaveFileDir.listFiles() ;	// 列出目录中的文件
			for (int x = 0; x < files.length; x++) {
				Map<String, Object> fileInfo = new HashMap<String, Object>();
				fileInfo.put("filename", files[x].getName()) ;
				this.recordFiles.add(fileInfo) ;
			}
			this.recordSimpleAdapter = new SimpleAdapter(this,
					this.recordFiles, R.layout.recordfiles,
					new String[] { "filename" }, new int[] { R.id.filename });
			this.reclist.setAdapter(this.recordSimpleAdapter) ;
		}
	}
	private class OnItemClickListenerImpl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (MyMediaRecorderDemo.this.recordSimpleAdapter.getItem(position) instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) MyMediaRecorderDemo.this.recordSimpleAdapter
						.getItem(position);
				Uri uri = Uri
						.fromFile(new File(MyMediaRecorderDemo.this.recordAudioSaveFileDir
								.toString()
								+ File.separator
								+ map.get("filename")));
				Intent intent = new Intent(Intent.ACTION_VIEW) ; 
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
				intent.setDataAndType(uri, "audio/mp3") ;
				MyMediaRecorderDemo.this.startActivity(intent) ;
			}
		}
	}
}