package org.lxh.demo;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
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
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			Document doc = null;
			try {
				doc = builder.parse(file); // 通过文件转化文档
			} catch (SAXException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			NodeList nl = doc.getElementsByTagName("linkman");
			for (int x = 0; x < nl.getLength(); x++) {
				Element e = (Element) nl.item(x); // 取得元素
				MyDOMDemo.this.name.setText(e.getElementsByTagName("name")
						.item(0).getFirstChild().getNodeValue());
				MyDOMDemo.this.email.setText(e.getElementsByTagName("email")
						.item(0).getFirstChild().getNodeValue());
			}
		}

	}
}