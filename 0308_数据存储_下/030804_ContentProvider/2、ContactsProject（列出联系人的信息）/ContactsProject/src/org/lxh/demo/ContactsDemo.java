package org.lxh.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ContactsDemo extends Activity {
	private Cursor result = null ;	// 既然要查询，查询返回的就是结果
	private ListView contactsList = null ;	// 定义ListView组件
	private List<Map<String,Object>> allContacts = null ;
	private SimpleAdapter simple = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.contactsList = (ListView) super.findViewById(R.id.contactsList) ;	// 取得组件
		this.result = super.getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);	// 查询
		super.startManagingCursor(this.result) ;	// 将结果集交给容器管理
		this.allContacts = new ArrayList<Map<String,Object>>() ;	// 实例化List集合
		for (this.result.moveToFirst(); !this.result.isAfterLast(); this.result
				.moveToNext()) {	// 取出结果集中的每一个内容
			Map<String,Object> contact = new HashMap<String,Object>() ;
			contact.put("_id", this.result.getInt(this.result
					.getColumnIndex(ContactsContract.Contacts._ID)));
			contact.put("name", this.result.getString(this.result
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
			this.allContacts.add(contact) ;
		}
		this.simple = new SimpleAdapter(this, this.allContacts,
				R.layout.contacts, new String[] { "_id", "name" }, new int[] {
						R.id._id, R.id.name });
		this.contactsList.setAdapter(this.simple) ;
	}
}