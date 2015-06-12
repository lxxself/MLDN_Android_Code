package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MyWebViewDemo extends Activity {
	private Button open = null ;
	private EditText inputurl = null ;
	private WebView webview = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.open = (Button) super.findViewById(R.id.open) ;
		this.inputurl = (EditText) super.findViewById(R.id.inputurl) ;
		this.webview = (WebView) super.findViewById(R.id.webview) ;
		this.open.setOnClickListener(new OpenOnClickListenerImpl()) ;
	}
	private class OpenOnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			String url = MyWebViewDemo.this.inputurl.getText().toString() ;
			MyWebViewDemo.this.webview.loadUrl(url) ;
		}
		
	}
}