package org.lxh.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import android.app.Activity;
import android.os.Bundle;

public class FileOperate extends Activity {
	private static final String FILENAME = "/mnt/sdcard/mldndata/mymldn.txt" ;	// 设置文件名称
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		File file = new File(FILENAME) ; 	// 定义要操作的文件
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs() ;	// 创建父文件夹路径
		}
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream(file));
			out.println("北京魔乐科技软件学院（MLDN，www.MLDNJAVA.cn），讲师：李兴华");
		} catch (Exception e) {
			e.printStackTrace() ;
		} finally {	// 一定要关闭流
			if(out != null) {
				out.close() ;
			}
		}
	}
}