package org.lxh.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

public class FileOperate extends Activity {
	private TextView msg = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.msg = (TextView) super.findViewById(R.id.msg) ;
		Resources res = super.getResources() ;	// 资源操作类
		InputStream input = res.openRawResource(R.raw.mybook) ;	// 为要读取的内容设置输入流
		Scanner scan = new Scanner(input) ;
		StringBuffer buf = new StringBuffer() ;
		while (scan.hasNext()) {
			buf.append(scan.next()).append("\n") ;
		}
		scan.close() ;
		try {
			input.close() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.msg.setText(buf) ;
	}
}