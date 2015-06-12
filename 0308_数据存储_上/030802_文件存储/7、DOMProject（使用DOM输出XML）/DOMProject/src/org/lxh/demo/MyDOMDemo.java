package org.lxh.demo;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MyDOMDemo extends Activity {
	private EditText name = null ;
	private EditText email = null ;
	private Button but = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.name = (EditText) super.findViewById(R.id.name) ;
		this.email = (EditText) super.findViewById(R.id.email) ;
		this.but = (Button) super.findViewById(R.id.but) ;
		this.but.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			if (!Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {	// 不存在不操作
				return; // 返回到程序的被调用处
			}
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + "mldndata" + File.separator
					+ "member.xml");	// 要输出文件的路径
			if (!file.getParentFile().exists()) { // 父路径不存在
				file.getParentFile().mkdirs() ;	// 创建父文件夹
			}
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance() ;
			DocumentBuilder builder = null ;
			try {
				builder = factory.newDocumentBuilder() ;
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			Document doc = null ;
			doc = builder.newDocument() ;	// 创建一个新的文档
			Element addresslist = doc.createElement("addresslist") ;
			Element linkman = doc.createElement("linkman") ;
			Element name = doc.createElement("name") ;
			Element email = doc.createElement("email") ;
			name.appendChild(doc.createTextNode(MyDOMDemo.this.name.getText()
					.toString()));
			email.appendChild(doc.createTextNode(MyDOMDemo.this.email.getText()
					.toString()));
			linkman.appendChild(name) ;
			linkman.appendChild(email) ;
			addresslist.appendChild(linkman) ;
			doc.appendChild(addresslist) ;
			TransformerFactory tf = TransformerFactory.newInstance() ;
			Transformer t = null ;
			try {
				t = tf.newTransformer() ;
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
			t.setOutputProperty(OutputKeys.ENCODING, "GBK") ;
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file) ;
			try {
				t.transform(source, result) ;
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		
	}
}