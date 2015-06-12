package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MyMenuDemo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, Menu.FIRST + 1, 5, "删除").setIcon(
				android.R.drawable.ic_menu_delete);
		menu.add(Menu.NONE, Menu.FIRST + 2, 2, "保存").setIcon(
				android.R.drawable.ic_menu_save);
		menu.add(Menu.NONE, Menu.FIRST + 3, 6, "帮助").setIcon(
				android.R.drawable.ic_menu_help);
		menu.add(Menu.NONE, Menu.FIRST + 4, 1, "添加").setIcon(
				android.R.drawable.ic_menu_add);
		menu.add(Menu.NONE, Menu.FIRST + 5, 4, "详情").setIcon(
				android.R.drawable.ic_menu_info_details);
		menu.add(Menu.NONE, Menu.FIRST + 6, 7, "发送").setIcon(
				android.R.drawable.ic_menu_send);
		menu.add(Menu.NONE, Menu.FIRST + 7, 3, "编辑").setIcon(
				android.R.drawable.ic_menu_edit);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {	// 判断操作的菜单ID
		case Menu.FIRST + 1: 
			Toast.makeText(this, "您选择的是“删除菜单”项", Toast.LENGTH_LONG).show() ;
			break ;
		case Menu.FIRST + 2: 
			Toast.makeText(this, "您选择的是“保存菜单”项", Toast.LENGTH_LONG).show() ;
			break ;
		case Menu.FIRST + 3: 
			Toast.makeText(this, "您选择的是“帮助菜单”项", Toast.LENGTH_LONG).show() ;
			break ;
		case Menu.FIRST + 4: 
			Toast.makeText(this, "您选择的是“添加菜单”项", Toast.LENGTH_LONG).show() ;
			break ;
		case Menu.FIRST + 5: 
			Toast.makeText(this, "您选择的是“详情菜单”项", Toast.LENGTH_LONG).show() ;
			break ;
		case Menu.FIRST + 6: 
			Toast.makeText(this, "您选择的是“发送菜单”项", Toast.LENGTH_LONG).show() ;
			break ;
		case Menu.FIRST + 7: 
			Toast.makeText(this, "您选择的是“编辑菜单”项", Toast.LENGTH_LONG).show() ;
			break ;
		}
		return false;
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		Toast.makeText(this,
				"选项菜单关闭了",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		Toast.makeText(this,
				"在菜单显示（onCreateOptionsMenu()方法之前会调用此操作，可以在此操作之中完成一些预处理功能。）",
				Toast.LENGTH_LONG).show();
		return true;
	}
	
}