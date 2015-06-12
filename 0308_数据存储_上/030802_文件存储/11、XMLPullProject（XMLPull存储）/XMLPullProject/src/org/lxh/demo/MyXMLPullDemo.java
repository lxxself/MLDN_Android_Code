package org.lxh.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

public class MyXMLPullDemo extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) { // 不存在不操作
			return; // 返回到程序的被调用处
		}
		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "mldndata" + File.separator
				+ "member.xml"); // 要输出文件的路径
		if (!file.getParentFile().exists()) { // 文件不存在
			file.getParentFile().mkdirs() ;	// 创建文件夹
		}
		List<LinkMan> all = new ArrayList<LinkMan>() ;
		for (int x = 0; x < 3; x++) {
			LinkMan man = new LinkMan() ;
			man.setName("李兴华 - " + x ) ;
			man.setEmail("mldnqa@163.com") ;
			all.add(man) ;
		}
		OutputStream output = null ;
		try {
			output = new FileOutputStream(file) ;
			new MyXMLPullUtil(output,all).save() ;
		} catch (Exception e) {
		}finally{
			if (output != null) {
				try {
					output.close() ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}