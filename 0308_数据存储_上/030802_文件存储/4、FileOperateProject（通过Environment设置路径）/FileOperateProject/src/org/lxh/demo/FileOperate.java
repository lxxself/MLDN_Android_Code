package org.lxh.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

public class FileOperate extends Activity {
	private static final String FILENAME = "mymldn.txt" ;	// 设置文件名称
	private static final String DIR = "mldndata" ;	// 操作文件夹的名称
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + DIR + File.separator + FILENAME); // 定义要操作的文件
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs(); // 创建父文件夹路径
			}
			PrintStream out = null;
			try {
				out = new PrintStream(new FileOutputStream(file));
				out.println("北京魔乐科技软件学院（MLDN，www.MLDNJAVA.cn），讲师：李兴华");
			} catch (Exception e) {
				e.printStackTrace();
			} finally { // 一定要关闭流
				if (out != null) {
					out.close();
				}
			}
		} else {
			Toast.makeText(this, "保存失败，SD卡不存在！", Toast.LENGTH_LONG).show() ;
		}
	}
}