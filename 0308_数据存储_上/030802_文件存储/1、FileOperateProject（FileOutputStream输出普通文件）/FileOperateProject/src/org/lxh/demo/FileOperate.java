package org.lxh.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import android.app.Activity;
import android.os.Bundle;

public class FileOperate extends Activity {
	private static final String FILENAME = "mldn.txt" ;	// 设置文件名称
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		FileOutputStream output = null ;	// 接收文件输出对象
		try {
			output = super.openFileOutput(FILENAME, Activity.MODE_PRIVATE) ;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintStream out = new PrintStream(output) ; 	// 输出方便
		out.println("姓名：李兴华；") ;
		out.println("年龄：30；") ;
		out.println("地址：北京魔乐科技软件学院。") ; 
		out.close() ;	// 资源一定要关闭
	}
}