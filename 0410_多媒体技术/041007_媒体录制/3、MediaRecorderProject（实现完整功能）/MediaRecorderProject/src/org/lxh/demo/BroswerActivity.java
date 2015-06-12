package org.lxh.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Browser;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class BroswerActivity extends Activity {
	private ImageButton back = null ;
	private ListView videolist = null ;
	private SimpleAdapter recordSimpleAdapter = null ;
	private List<Map<String,Object>> recordFiles = null ;
	private String recDir = "mldnvideo";
	private File recordVideoSaveFileDir = null;
	private boolean sdcardExists = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE); // 不显示标题
		super.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 高亮的风格显示
		super.setContentView(R.layout.broswer);
		if ((this.sdcardExists = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))) {
			this.recordVideoSaveFileDir = new File(Environment
					.getExternalStorageDirectory().toString()
					+ File.separator
					+ BroswerActivity.this.recDir + File.separator); // 保存文件夹
			if (!this.recordVideoSaveFileDir.exists()) {
				this.recordVideoSaveFileDir.mkdirs(); // 创建文件夹
			}
		}
		this.back = (ImageButton) super.findViewById(R.id.back) ;
		this.videolist = (ListView) super.findViewById(R.id.videolist) ;
		this.back.setOnClickListener(new BackOnClickListenerImpl()) ;
		this.getRecordFiles() ;
		this.videolist.setOnItemClickListener(new OnItemClickListenerImpl()) ;
	}

	private void getRecordFiles() {
		this.recordFiles = new ArrayList<Map<String, Object>>();
		if(this.sdcardExists){
			File files [] = this.recordVideoSaveFileDir.listFiles() ;
			for (int x = 0; x < files.length; x++) {
				Map<String, Object> fileInfo = new HashMap<String, Object>();
				fileInfo.put("filename", files[x].getName()) ;
				this.recordFiles.add(fileInfo) ;
			}
			this.recordSimpleAdapter = new SimpleAdapter(this,
					this.recordFiles, R.layout.recordfiles,
					new String[] { "filename" }, new int[] { R.id.filename });
			this.videolist.setAdapter(this.recordSimpleAdapter) ;
		}
	}
	
	private class BackOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent it = new Intent(BroswerActivity.this,MyMediaRecorderDemo.class) ;
			BroswerActivity.this.startActivity(it) ;
		}
	} 
	private class OnItemClickListenerImpl implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position,
				long id) {
			if (BroswerActivity.this.recordSimpleAdapter.getItem(position) instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) BroswerActivity.this.recordSimpleAdapter
						.getItem(position);
				Intent intent = new Intent(BroswerActivity.this,PlayVideoActivity.class) ;
				intent.putExtra("filepath",
						BroswerActivity.this.recordVideoSaveFileDir.toString()
								+ File.separator
								+ map.get("filename").toString());
				BroswerActivity.this.startActivity(intent) ;
			}
		}
		
	}
}