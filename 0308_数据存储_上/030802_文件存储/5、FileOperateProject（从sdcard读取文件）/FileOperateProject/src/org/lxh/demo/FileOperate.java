package org.lxh.demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

public class FileOperate extends Activity {
	private static final String FILENAME = "mymldn.txt" ;	// 设置文件名称
	private static final String DIR = "mldndata" ;	// 操作文件夹的名称
	private TextView msg = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.msg = (TextView) super.findViewById(R.id.msg) ;
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + DIR + File.separator + FILENAME); // 定义要操作的文件
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs(); // 创建父文件夹路径
			}
			Scanner scan = null ;
			try {
				scan = new Scanner(new FileInputStream(file)) ;
				while(scan.hasNext()) {
					this.msg.append(scan.next() + "\n") ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally { // 一定要关闭流
				if (scan != null) {
					scan.close();
				}
			}
		} else {
			Toast.makeText(this, "保存失败，SD卡不存在！", Toast.LENGTH_LONG).show() ;
		}
	}
}