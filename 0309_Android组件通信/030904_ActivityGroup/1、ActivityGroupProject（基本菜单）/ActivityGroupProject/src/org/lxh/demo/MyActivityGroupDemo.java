package org.lxh.demo;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

public class MyActivityGroupDemo extends ActivityGroup {
	private GridView gridviewToolbar; // 工具菜单栏
	private MenuImageAdapter menu = null; // 图片适配器
	private LinearLayout content = null; // 填充内容
	private int menu_img[] = new int[] { R.drawable.menu_main,
			R.drawable.menu_news, R.drawable.menu_sms, R.drawable.menu_more,
			R.drawable.menu_exit }; // 填充的图片的资源
	private int width = 0; // 求出平均的宽度
	private int height = 0; // 求出平均的高度，定位显示
	private Intent intent = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
		super.setContentView(R.layout.main);
		this.gridviewToolbar = (GridView) super.findViewById(R.id.gridviewbar);
		this.content = (LinearLayout) super.findViewById(R.id.content);

		// 定义工具栏的一些信息显示
		this.gridviewToolbar.setNumColumns(this.menu_img.length); // 求出可以保存的个数
		this.gridviewToolbar.setSelector(new ColorDrawable(Color.TRANSPARENT));
		this.gridviewToolbar.setGravity(Gravity.CENTER);
		this.gridviewToolbar.setVerticalSpacing(0);

		this.width = super.getWindowManager().getDefaultDisplay().getWidth()
				/ this.menu_img.length;
		this.height = super.getWindowManager().getDefaultDisplay().getHeight() / 8;

		this.menu = new MenuImageAdapter(this, this.menu_img, this.width,
				this.height, R.drawable.menu_selected);
		this.gridviewToolbar.setAdapter(this.menu);
		this.switchActivity(0); // 第一个被选中
		this.gridviewToolbar
				.setOnItemClickListener(new OnItemClickListenerImpl());
	}

	private class OnItemClickListenerImpl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			MyActivityGroupDemo.this.switchActivity(position);
		}
	}

	private void switchActivity(int id) { // 切换选中的操作
		this.menu.setFocus(id); // 设置选中图片的背景
		this.content.removeAllViews(); // 删除所有的内容
		switch (id) {
		case 0:
			this.intent = new Intent(MyActivityGroupDemo.this, MyActivity.class);
			break;
		case 1:
			this.intent = new Intent(MyActivityGroupDemo.this, MyActivity.class);
			break;
		case 2:
			this.intent = new Intent(MyActivityGroupDemo.this, MyActivity.class);
			break;
		case 3:
			this.intent = new Intent(MyActivityGroupDemo.this, MyActivity.class);
			break;
		case 4:
			this.exitDialog() ;
			return;
		}
		this.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		Window subActivity = super.getLocalActivityManager().startActivity(
				"subActivity", this.intent);
		this.content.addView(subActivity.getDecorView(),
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
	}

	private void exitDialog() {
		Dialog dialog = new AlertDialog.Builder(this).setIcon(R.drawable.pic_m)
				.setTitle("程序退出？ ").setMessage("您确定要退出本程序吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						MyActivityGroupDemo.this.finish() ;
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						MyActivityGroupDemo.this.switchActivity(0);
					}
				}).create();

		dialog.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			this.exitDialog() ;
		}
		return false ;
	}
	
}