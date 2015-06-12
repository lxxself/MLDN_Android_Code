package org.lxh.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class MyWebViewDemo extends Activity {
	private Button open = null;
	private Button back = null;
	private Button forward = null;
	private Button zoomin = null;
	private Button zoomout = null;
	private Button clear = null;
	private WebView webview = null;
	private String urlData[] = new String[] { "http://www.mldn.cn",
			"http://www.mldnjava.cn", "http://bbs.mldn.cn",
			"http://www.jiangke.com", "http://www.javajob.cn" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.webview = (WebView) super.findViewById(R.id.webview);
		this.open = (Button) super.findViewById(R.id.open);
		this.back = (Button) super.findViewById(R.id.back);
		this.forward = (Button) super.findViewById(R.id.forward);
		this.zoomin = (Button) super.findViewById(R.id.zoomin);
		this.zoomout = (Button) super.findViewById(R.id.zoomout);
		this.clear = (Button) super.findViewById(R.id.clear);
		this.open.setOnClickListener(new OpenOnClickListenerImpl());
		this.back.setOnClickListener(new BackOnClickListenerImpl());
		this.forward.setOnClickListener(new ForwardOnClickListenerImpl());
		this.zoomin.setOnClickListener(new ZoomInOnClickListenerImpl());
		this.zoomout.setOnClickListener(new ZoomOutClickListenerImpl());
		this.clear.setOnClickListener(new ClearOnClickListenerImpl());
	}

	private class OpenOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			MyWebViewDemo.this.showUrlDialog() ;
		}
	}

	private class BackOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			if(MyWebViewDemo.this.webview.canGoBack()) {
				MyWebViewDemo.this.webview.goBack() ;
			}
		}
	}

	private class ForwardOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			if(MyWebViewDemo.this.webview.canGoForward()){
				MyWebViewDemo.this.webview.goForward() ;
			}
		}
	}

	private class ZoomInOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			MyWebViewDemo.this.webview.zoomIn() ;
		}
	}

	private class ZoomOutClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			MyWebViewDemo.this.webview.zoomOut() ;
		}
	}

	private class ClearOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			MyWebViewDemo.this.webview.clearHistory() ;
		}
	}

	private void showUrlDialog() {
		Dialog dialog = new AlertDialog.Builder(this).setIcon(R.drawable.pic_m)
				.setTitle("请选择要浏览的网站")
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				})
				.setItems(this.urlData, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						MyWebViewDemo.this.webview
								.loadUrl(MyWebViewDemo.this.urlData[which]);
					}
				}).create();
		dialog.show() ;
	}
}