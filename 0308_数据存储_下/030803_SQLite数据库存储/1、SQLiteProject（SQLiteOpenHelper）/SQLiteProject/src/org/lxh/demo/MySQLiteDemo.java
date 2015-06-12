package org.lxh.demo;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class MySQLiteDemo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		SQLiteOpenHelper  helper = new MyDatabaseHelper(this) ;
		helper.getWritableDatabase() ;	// 以写的方式打开数据库
	}
}