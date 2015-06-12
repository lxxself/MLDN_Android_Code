package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyMenuDemo extends Activity {
	private String data[] = new String[] { "北京魔乐科技", "www.mldnjava.cn",
			"讲师：李兴华", "中国高校联盟", "www.jiangker.com" }; // 定义ListView的显示项
	private ListView listView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.listView = new ListView(this);
		this.listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, this.data));
		super.setContentView(this.listView);
		super.registerForContextMenu(this.listView) ;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			Toast.makeText(this, "您选择的是“添加联系人”项", Toast.LENGTH_LONG).show();
			break;
		case Menu.FIRST + 2:
			Toast.makeText(this, "您选择的是“查看详情”项", Toast.LENGTH_LONG).show();
			break;
		case Menu.FIRST + 3:
			Toast.makeText(this, "您选择的是“删除信息”项", Toast.LENGTH_LONG).show();
			break;
		case Menu.FIRST + 4:
			Toast.makeText(this, "您选择的是“另存为”项", Toast.LENGTH_LONG).show();
			break;
		case Menu.FIRST + 5:
			Toast.makeText(this, "您选择的是“编辑”项", Toast.LENGTH_LONG).show();
			break;
		}
		return false;
	}

	@Override
	public void onContextMenuClosed(Menu menu) {
		Toast.makeText(this, "上下文菜单关闭了", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("信息操作");
		menu.add(Menu.NONE, Menu.FIRST + 1, 1, "添加联系人");
		menu.add(Menu.NONE, Menu.FIRST + 2, 2, "查看详情");
		menu.add(Menu.NONE, Menu.FIRST + 3, 3, "删除信息");
		menu.add(Menu.NONE, Menu.FIRST + 4, 4, "另存为");
		menu.add(Menu.NONE, Menu.FIRST + 5, 5, "编辑");
	}
}