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
import android.widget.Toast;

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
	private boolean isShow = false ;
	
	private int commonItemIds[] = new int[] { R.drawable.common_account,
			R.drawable.common_addmark, R.drawable.common_download,
			R.drawable.common_exit, R.drawable.common_fullscreen,
			R.drawable.common_history, R.drawable.common_night,
			R.drawable.common_refresh };
	private int setItemIds[] = new int[] { R.drawable.set_button,
			R.drawable.set_mode, R.drawable.set_nophoto,
			R.drawable.set_rotation, R.drawable.set_scroll,
			R.drawable.set_skin, R.drawable.set_system, R.drawable.set_time };
	private int totleItemids[] = new int[] { R.drawable.tool_back,
			R.drawable.tool_copy, R.drawable.tool_file, R.drawable.tool_help,
			R.drawable.tool_report, R.drawable.tool_report,
			R.drawable.tool_save, R.drawable.tool_share };
	
	private int titleIds[] = new int[] { R.string.popmenu_common,
			R.string.popmenu_set, R.string.popmenu_tool };
	private PopupMenu popMenu = null ;
	private PopupMenuBodyAdapter commonAdapter = null ;
	private PopupMenuBodyAdapter setAdapter = null ;
	private PopupMenuBodyAdapter toolAdapter = null ;

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
		
		this.popMenu = new PopupMenu(this, this.titleIds, 0x55123456, new PopupTitleOnItemClickListenerCallback(),
				new PopupBodyOnItemClickListenerCallback());
		this.commonAdapter = new PopupMenuBodyAdapter(this, this.commonItemIds);
		this.setAdapter = new PopupMenuBodyAdapter(this, this.setItemIds);
		this.toolAdapter = new PopupMenuBodyAdapter(this, this.totleItemids);
		
		this.popMenu.setPopupMenuBodyAdapter(this.commonAdapter) ;
		this.popMenu.setPopTitleSelected(0) ;
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
			this.showPopupMenu() ;
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

	private void showPopupMenu() {
		if (this.isShow) { // 已经显示了
			this.popMenu.dismiss();
			this.isShow = false;
		} else {
			this.popMenu.showAtLocation(
					MyActivityGroupDemo.this.gridviewToolbar, Gravity.BOTTOM,
					0, this.height);
			this.isShow = true;
		}
	}

	private class PopupBodyOnItemClickListenerCallback implements
			OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			MyActivityGroupDemo.this.popMenu.setPopBodySelected(position, Color.GRAY) ;
			Toast.makeText(MyActivityGroupDemo.this, "执行选项 - " + position, Toast.LENGTH_SHORT).show() ;
		}
	}
	private class PopupTitleOnItemClickListenerCallback implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			MyActivityGroupDemo.this.popMenu.setPopTitleSelected(position) ;
			switch(position) {
			case 0:
				MyActivityGroupDemo.this.popMenu
						.setPopupMenuBodyAdapter(MyActivityGroupDemo.this.commonAdapter);
				break;
			case 1:
				MyActivityGroupDemo.this.popMenu
						.setPopupMenuBodyAdapter(MyActivityGroupDemo.this.setAdapter);
				break;
			case 2:
				MyActivityGroupDemo.this.popMenu
						.setPopupMenuBodyAdapter(MyActivityGroupDemo.this.toolAdapter);
				break;
			}
		}}
}