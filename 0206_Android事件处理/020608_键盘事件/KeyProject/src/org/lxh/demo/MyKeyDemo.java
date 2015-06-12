package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ImageView;

public class MyKeyDemo extends Activity {
	private EditText input = null;
	private ImageView img = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.input = (EditText) super.findViewById(R.id.input); // 取得组件
		this.img = (ImageView) super.findViewById(R.id.img); // 取得组件
		this.input.setOnKeyListener(new OnKeyListenerImpl());
	}

	private class OnKeyListenerImpl implements OnKeyListener {

		public boolean onKey(View v, int keyCode, KeyEvent event) {
			switch (event.getAction()) {
			case KeyEvent.ACTION_UP:
				String msg = MyKeyDemo.this.input.getText().toString(); // 取得输入的文字信息
				if (msg.matches("\\w+@\\w+\\.\\w+")) { // 验证通过
					MyKeyDemo.this.img.setImageResource(R.drawable.right); // 设置正确图片
				} else {
					MyKeyDemo.this.img.setImageResource(R.drawable.wrong); // 设置错误图片
				}
			case KeyEvent.ACTION_DOWN: // 键盘按下
				break;
			}
			return false;
		}
	}
}