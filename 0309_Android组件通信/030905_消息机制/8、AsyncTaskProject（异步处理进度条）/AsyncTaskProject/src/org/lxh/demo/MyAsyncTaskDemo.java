package org.lxh.demo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyAsyncTaskDemo extends Activity {
	private ProgressBar bar = null;
	private TextView info = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.bar = (ProgressBar) super.findViewById(R.id.bar);
		this.info = (TextView) super.findViewById(R.id.info);
		ChildUpdate child = new ChildUpdate() ;
		child.execute(100) ;
	}

	// 每次处理后台进度的类型是Integer、更新之后的数值Integer，最后的结果返回的是字符串
	private class ChildUpdate extends AsyncTask<Integer, Integer, String> {

		@Override
		protected void onPostExecute(String result) {
			MyAsyncTaskDemo.this.info.setText(result) ;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {	// 每次更新之后的内容
			MyAsyncTaskDemo.this.info.setText("当前的进度值是：" + String.valueOf(values[0])) ;
		}

		@Override
		protected String doInBackground(Integer... params) { // 每次的进度处理，可以更新UI组件
			for (int x = 0; x < 100; x++) {
				MyAsyncTaskDemo.this.bar.setProgress(x); // 设置进度
				this.publishProgress(x) ;	// 更新，调用更新操作
				try {// 延迟的操作由外部决定
					Thread.sleep(params[0]);
				} catch (InterruptedException e) {
				}
			}
			return "执行完毕";
		}

	}
}