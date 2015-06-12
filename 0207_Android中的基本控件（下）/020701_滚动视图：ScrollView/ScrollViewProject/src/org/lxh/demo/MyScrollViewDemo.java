package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyScrollViewDemo extends Activity {
	private String data[] = { "北京魔乐科技", "www.mldnjava.cn", "讲师：李兴华",
			"中国高校讲师联盟", "www.jiangker.com", "咨询邮箱：mldnqa@163.com",
			"客户服务：mldnkf@163.com", "客户电话：(010) 51283346", "魔乐社区：bbs.mldn.cn",
			"程序员招聘网：http://www.javajob.cn/" }; // 准备出若干个信息而这些信息以后将通过程序加入到内嵌的线性布局文件之中

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main); // 调用默认的布局管理器
		LinearLayout layout = (LinearLayout) super.findViewById(R.id.mylinear); // 取得组件
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT); // 定义布局参数
		for (int x = 0; x < this.data.length; x++) { // 通过循环方式将以上的信息通过Button组件进行封装
			Button but = new Button(this);
			but.setText(this.data[x]); // 设置显示文字
			layout.addView(but, param); // 增加组件
		}
	}
}