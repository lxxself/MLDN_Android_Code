package org.lxh.demo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyDOMDemo extends Activity {
	private TextView name = null;
	private TextView email = null;
	private Button but = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.name = (TextView) super.findViewById(R.id.name);
		this.email = (TextView) super.findViewById(R.id.email);
		this.but = (Button) super.findViewById(R.id.but);
		this.but.setOnClickListener(new OnClickListenerImpl());
	}

	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (!Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) { // 不存在不操作
				return; // 返回到程序的被调用处
			}
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + "mldndata" + File.separator
					+ "member.xml"); // 要输出文件的路径
			if (!file.exists()) { // 文件不存在
				return;
			}
			SAXParserFactory factory = SAXParserFactory.newInstance() ;
			SAXParser parser = null ;
			MySAX sax = new MySAX() ;
			try {
				parser = factory.newSAXParser() ;
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				parser.parse(file, sax) ;
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
			List<LinkMan> all = sax.getAll() ;
			System.out.println(all.size());
			MyDOMDemo.this.name.setText(all.get(0).getName()) ;
			MyDOMDemo.this.email.setText(all.get(0).getEmail()) ;
		}

	}
}