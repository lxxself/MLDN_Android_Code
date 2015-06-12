package org.lxh.demo;

import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MyWebDemo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		TextView info = (TextView) super.findViewById(R.id.info);
		boolean flag = false ;	// 成功与否的标记
		try {
			URL url = new URL("http", "114.249.165.249", 80,
					"/mldn/android.jsp?id=lixinghua&password=mldn");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			byte data[] = new byte[512];
			int len = conn.getInputStream().read(data); // 输入流读取
			if (len > 0) {	// 已经读取到内容
				String temp = new String(data, 0, len).trim();
				flag = Boolean.parseBoolean(temp) ;	// 取出里面的boolean型数据
			}
			conn.getInputStream().close() ;
		} catch (Exception e) {
			info.setText("WEB服务器连接失败！") ;
		}
		if(flag){
			info.setText("用户登录成功！") ;
		} else {
			info.setText("用户登录失败！") ;
		}
	}
}