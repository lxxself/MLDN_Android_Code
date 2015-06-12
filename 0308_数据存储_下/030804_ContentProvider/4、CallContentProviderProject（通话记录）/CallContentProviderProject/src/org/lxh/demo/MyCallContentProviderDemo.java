package org.lxh.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyCallContentProviderDemo extends Activity {
	private ListView callList = null ;
	private Cursor result = null ;
	private List<Map<String,Object>> allCalls = null ;
	private SimpleAdapter simple = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.callList = (ListView) super.findViewById(R.id.callList) ;
		this.result = super.getContentResolver().query(
				CallLog.Calls.CONTENT_URI, null, null, null, null);	// 查询通话记录
		System.out.println("************ " + this.result.getCount()) ;
		this.startManagingCursor(this.result) ;	// 交给程序
		this.allCalls = new ArrayList<Map<String,Object>>() ;
		for (this.result.moveToFirst(); !result.isAfterLast(); result
				.moveToNext()) {	// 取出数据
			Map<String,Object> contact = new HashMap<String,Object>() ;
			contact.put("_id",
					result.getInt(result.getColumnIndex(CallLog.Calls._ID)));
			String nameTemp = this.result.getString(this.result.getColumnIndex(CallLog.Calls.CACHED_NAME)) ;
			if (nameTemp == null || "".equals(nameTemp)) {
				nameTemp = "未知" ;
			}
			contact.put("name",nameTemp);
			contact.put("number",
					result.getString(result.getColumnIndex(CallLog.Calls.NUMBER)));
			this.allCalls.add(contact) ;
		}
		this.simple = new SimpleAdapter(this, this.allCalls, R.layout.calls,
				new String[] { "_id", "name", "number" }, new int[] { R.id._id,
						R.id.name, R.id.number });
		this.callList.setAdapter(this.simple) ;
	}
}