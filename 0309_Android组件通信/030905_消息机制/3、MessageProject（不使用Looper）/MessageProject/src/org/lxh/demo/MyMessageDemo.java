package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyMessageDemo extends Activity {
	private TextView info = null;
	private static final int SET = 1; // 操作的what状态
	private Button but = null; // 操作按钮

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.info = (TextView) super.findViewById(R.id.info); // 取得组件
		this.but = (Button) super.findViewById(R.id.but); // 取得组件
		this.but.setOnClickListener(new OnClickListenerImpl()); // 单击事件
	}

	private class OnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			MyHandler myHandler = new MyHandler();
			myHandler.removeMessages(0); // 表示清空所有的消息
			String data = "魔乐科技软件学院（MLDN）"; // 要传递的消息
			Message msg = myHandler.obtainMessage(SET, 1, 1, data); // 创建消息
			myHandler.sendMessage(msg); // 发送消息
		}

	}

	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) { // 判断操作的消息类型
			case SET: // 更新组件
				MyMessageDemo.this.info.setText(msg.obj.toString()); // 设置文本组件
			}
		}
	}
}