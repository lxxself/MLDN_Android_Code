package org.lxh.demo;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyWebServiceDemo extends Activity {
	private Button save = null;
	private Button load = null;
	private TextView show = null;
	private static final String NAMESPACE = "http://114.249.165.249/";
	private static String URL = "http://114.249.165.249/FileProject/services/mldn";
	private static final String SAVE_METHOD_NAME = "save";
	private static final String LOAD_METHOD_NAME = "load";
	private static String SOAP_ACTION = "http://114.249.165.249/FileProject/services/";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.save = (Button) super.findViewById(R.id.save);
		this.load = (Button) super.findViewById(R.id.load);
		this.show = (TextView) super.findViewById(R.id.show);
		this.save.setOnClickListener(new SaveOnClickListenerImpl());
		this.load.setOnClickListener(new LoadOnClickListenerImpl());
	}

	private class SaveOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			SoapObject soapObject = new SoapObject(NAMESPACE, SAVE_METHOD_NAME);
			soapObject.addProperty("fileName", "mldn.txt");
			soapObject.addProperty("content", "北京魔乐科技软件学院（www.MLDNJAVA.cn）");
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);// 版本
			envelope.bodyOut = soapObject ;
			envelope.dotNet = false ;
			envelope.setOutputSoapObject(soapObject) ;
			HttpTransportSE trans = new HttpTransportSE(URL) ;
			trans.debug = true ;	// 使用调试功能
			try {
				trans.call(SOAP_ACTION, envelope) ;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			Toast.makeText(MyWebServiceDemo.this, "数据保存成功", Toast.LENGTH_SHORT)
					.show();
		}

	}

	private class LoadOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			SoapObject soapObject = new SoapObject(NAMESPACE, LOAD_METHOD_NAME);
			soapObject.addProperty("fileName", "mldn.txt");
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);// 版本
			envelope.bodyOut = soapObject ;
			envelope.dotNet = false ;
			envelope.setOutputSoapObject(soapObject) ;
			HttpTransportSE trans = new HttpTransportSE(URL) ;
			trans.debug = true ;	// 使用调试功能
			try {
				trans.call(SOAP_ACTION, envelope) ;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			SoapObject result = (SoapObject) envelope.bodyIn;
			MyWebServiceDemo.this.show.setText("Web Service返回的数据是："
					+ result.getProperty(0));
		}

	}
}