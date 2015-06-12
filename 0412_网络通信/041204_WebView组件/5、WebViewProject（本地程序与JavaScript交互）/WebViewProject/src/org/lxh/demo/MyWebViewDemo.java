package org.lxh.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class MyWebViewDemo extends Activity {
	private WebView webview = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.webview = (WebView) super.findViewById(R.id.webview);
		this.webview.getSettings().setJavaScriptEnabled(true); // 启用JavaScript
		this.webview.getSettings().setBuiltInZoomControls(true); // 控制页面缩放
		this.webview.setWebChromeClient(new WebChromeClientImpl());
		this.webview.loadUrl("file:/android_asset/html/show_js.html");
	}

	private class WebChromeClientImpl extends WebChromeClient {

		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				final JsResult result) {
			Dialog dialog = new AlertDialog.Builder(MyWebViewDemo.this)
					.setIcon(R.drawable.pic_m)
					.setTitle("MLDN警告")
					.setMessage(message)
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Toast.makeText(MyWebViewDemo.this, "关闭警告框",
											Toast.LENGTH_SHORT).show();
									result.cancel();
								}
							}).create();
			dialog.show() ;
			return true;
		}

		@Override
		public boolean onJsConfirm(WebView view, String url, String message,
				final JsResult result) {
			Dialog dialog = new AlertDialog.Builder(MyWebViewDemo.this)
					.setIcon(R.drawable.pic_m)
					.setTitle("确定删除？")
					.setMessage(message)
					.setPositiveButton("删除",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Toast.makeText(MyWebViewDemo.this, "确定删除",
											Toast.LENGTH_SHORT).show();
									result.confirm();
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Toast.makeText(MyWebViewDemo.this, "取消删除",
											Toast.LENGTH_SHORT).show();
									result.cancel();
								}
							}).create();
			dialog.show() ;
			return true;
		}

		@Override
		public void onReceivedTitle(WebView view, String title) {
			MyWebViewDemo.this.setTitle(title) ;
			super.onReceivedTitle(view, title) ;
		}

	}
}