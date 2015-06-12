package org.lxh.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ContactsDemo extends Activity {
	private Cursor result = null; // 既然要查询，查询返回的就是结果
	private ListView contactsList = null; // 定义ListView组件
	private List<Map<String, Object>> allContacts = null;
	private SimpleAdapter simple = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.contactsList = (ListView) super.findViewById(R.id.contactsList); // 取得组件
		this.result = super.getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null); // 查询
		super.startManagingCursor(this.result); // 将结果集交给容器管理
		this.allContacts = new ArrayList<Map<String, Object>>(); // 实例化List集合
		for (this.result.moveToFirst(); !this.result.isAfterLast(); this.result
				.moveToNext()) { // 取出结果集中的每一个内容
			Map<String, Object> contact = new HashMap<String, Object>();
			contact.put("_id", this.result.getInt(this.result
					.getColumnIndex(ContactsContract.Contacts._ID)));
			contact.put("name", this.result.getString(this.result
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
			this.allContacts.add(contact);
		}
		this.simple = new SimpleAdapter(this, this.allContacts,
				R.layout.contacts, new String[] { "_id", "name" }, new int[] {
						R.id._id, R.id.name });
		this.contactsList.setAdapter(this.simple);
		super.registerForContextMenu(this.contactsList); // 注册菜单
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int position = info.position; // 取得操作位置
		String contactsId = this.allContacts.get(position).get("_id").toString() ;
		switch(item.getItemId()){	// 进行菜单的操作
		case Menu.FIRST + 1:	// 查看
			String phoneSelection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
					+ "=?";
			String[] phoneSelectionArgs = new String[] { contactsId};
			Cursor c = super.getContentResolver().query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					phoneSelection, phoneSelectionArgs, null);
			StringBuffer buf = new StringBuffer() ;
			buf.append("电话号码是：") ;
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				buf.append(
						c.getString(c
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)))
						.append("、");
			}
			Toast.makeText(this, buf, Toast.LENGTH_SHORT)
					.show();
			break ;
		case Menu.FIRST + 2:	// 删除
			super.getContentResolver().delete(
					Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI,
							contactsId), null, null);
			this.allContacts.remove(position) ;	// 删除集合数据项
			this.simple.notifyDataSetChanged() ;	// 通知改变
			Toast.makeText(this, "数据已删除！", Toast.LENGTH_SHORT).show();
			break ;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) { // 创建菜单
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("联系人操作");
		menu.add(Menu.NONE, Menu.FIRST + 1, 1, "查看详情");
		menu.add(Menu.NONE, Menu.FIRST + 2, 1, "删除信息");
	}

}