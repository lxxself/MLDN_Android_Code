package org.lxh.demo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.media.MediaRecorder;
import android.os.Environment;

public class RecordAudioUtil {
	private MediaRecorder mediaRecorder = null;
	private String recDir = "theifaudio";
	private File recordAudioSaveFileDir = null;
	private boolean sdcardExists = false;
	private boolean isRecord = false;
	private String phoneNumber = null; // 电话号码
	private String callType = null; // 呼叫类型

	public RecordAudioUtil(String phoneNumber, String callType) {
		this.phoneNumber = phoneNumber;
		this.callType = callType;
		if ((this.sdcardExists = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))) {
			this.recordAudioSaveFileDir = new File(Environment
					.getExternalStorageDirectory().toString()
					+ File.separator
					+ this.recDir + File.separator);
			if (!this.recordAudioSaveFileDir.exists()) {
				this.recordAudioSaveFileDir.mkdirs();
			}
		}
	}

	public File record() { // 进行电话的录音，同时返回文件的路径
		File recordAudioSaveFile = null;
		String recordAudioSaveFileName = null;
		if (this.sdcardExists) { // sd卡存在
			recordAudioSaveFileName = this.recordAudioSaveFileDir.toString()
					+ File.separator
					+ "ThiefAudio_"
					+ new SimpleDateFormat("yyyyMMddhhmmssSSS")
							.format(new Date()) + "_" + this.callType + "_"
					+ this.phoneNumber + ".3gp";
			recordAudioSaveFile = new File(recordAudioSaveFileName);
			this.mediaRecorder = new MediaRecorder();
			this.mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			this.mediaRecorder
					.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			this.mediaRecorder
					.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			this.mediaRecorder.setOutputFile(recordAudioSaveFileName);
			try {
				this.mediaRecorder.prepare();
			} catch (Exception e) {
				e.printStackTrace() ;
			}
			this.mediaRecorder.start();
			this.isRecord = true;
		}
		return recordAudioSaveFile;
	}

	public void stop() {
		if (this.isRecord) {
			this.mediaRecorder.stop();
			this.mediaRecorder.reset() ;
			this.mediaRecorder.release();
		}
	}
}
