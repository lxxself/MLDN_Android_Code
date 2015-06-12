package org.lxh.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASENAME = "mldn.db" ;
	private static final int DATABASERVERSION = 2 ;	// 设置数据库的版本
	private static final String TABLENAME = "mytab" ;
	
	public MyDatabaseHelper(Context context) {	// 用户最关心的也肯定只是Context
		super(context, DATABASENAME, null, DATABASERVERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {	// 创建数据表
		String sql = "CREATE TABLE " + TABLENAME + "("
				+ "id		INTEGER			PRIMARY KEY ," 	// 在SQLite中设置为Integer、PRIMARY KEY则ID自动增长
				+ "name 	VARCHAR(50) 	NOT NULL ,"
				+ "birthday DATE NOT 		NULL" + ")";
		db.execSQL(sql) ;	// 执行SQL
		System.out.println("****************** 创建：onCreate()。");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLENAME ;
		db.execSQL(sql) ;
		System.out.println("****************** 更新：onUpgrade()。");
		this.onCreate(db) ;
	}

}
