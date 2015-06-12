package org.lxh.demo;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MyCallContentProviderDemo extends Activity {
	private ListView callList = null;
	private Cursor result = null;
	private ListAdapter listAdapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.callList = (ListView) super.findViewById(R.id.callList);
		this.result = super.getContentResolver().query(
				CallLog.Calls.CONTENT_URI, null, null, null, null); // 查询通话记录
		this.startManagingCursor(this.result); // 交给程序
		String[] columns = new String[] { CallLog.Calls._ID,
				CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER };
		int[] entries = new int[] { R.id._id, R.id.name, R.id.number };
		this.listAdapter = new SimpleCursorAdapter(this, R.layout.calls,
				this.result, columns, entries);	// 将Curosr直接变为可以显示的数据
		this.callList.setAdapter(this.listAdapter);
	}
}