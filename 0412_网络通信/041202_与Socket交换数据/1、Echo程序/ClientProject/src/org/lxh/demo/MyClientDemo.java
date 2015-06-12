package org.lxh.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyClientDemo extends Activity {
	private Button send = null;
	private TextView info = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.send = (Button) super.findViewById(R.id.send);
		this.info = (TextView) super.findViewById(R.id.info);
		this.send.setOnClickListener(new SendOnClickListener());
	}

	private class SendOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			try {
				Socket client = new Socket("192.168.1.114", 8888);
				PrintStream out = new PrintStream(client.getOutputStream());
				BufferedReader buf = new BufferedReader(new InputStreamReader(
						client.getInputStream()));
				out.println("北京魔乐科技软件学院") ;	// 向服务器端发送数据
				MyClientDemo.this.info.setText(buf.readLine()) ;
				out.close() ;
				buf.close() ;
				client.close() ;
			} catch (Exception e) {
				e.printStackTrace() ;
			}
		}

	}
}