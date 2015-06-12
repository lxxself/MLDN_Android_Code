package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MyWebViewDemo extends Activity {
	private WebView webview = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.webview = (WebView) super.findViewById(R.id.webview) ;
		String data = "<h1>MLDNJava Training.</h1>"
				+ "<h2><a href=\"http://www.mldnjava.cn\">MLDNJAVA</a><h2>";
		this.webview.loadData(data, "text/html", "UTF-8") ;
	}
}