package org.lxh.demo;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MytabCursor {
	private static final String TABLENAME = "mytab" ;
	private SQLiteDatabase db = null ;
	public MytabCursor(SQLiteDatabase db) {
		this.db = db ;
	}
	public List<String> find(){
		List<String> all = new ArrayList<String>() ;	// 此时只是String
		String columns[] = new String[] { "id", "name", "birthday" };
		Cursor result = this.db.query(TABLENAME, columns, null, null, null,
				null, null);	// 这些条件根据自己的情况增加
		for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {	// 采用循环的方式检索数据
			all.add("【" + result.getInt(0) + "】" + " " + result.getString(1)
					+ "，" + result.getString(2));
		}
		this.db.close() ;
		return all ;
	}
}
