package org.lxh.demo;

import android.database.sqlite.SQLiteDatabase;

public class MytabTransaction {
	private static final String TABLENAME = "mytab"; // 表示要操作的数据表名称
	private SQLiteDatabase db = null; // 数据库操作

	public MytabTransaction(SQLiteDatabase db) {
		this.db = db;
	}
	public void insertBatch() {
		this.db.beginTransaction() ;	// 开始事务
		try {
			this.db.execSQL("INSERT INTO " + TABLENAME + " (name,birthday) VALUES (?,?) ",new Object[]{"李兴华","1989-09-12"} ) ;
			this.db.execSQL("INSERT INTO " + TABLENAME + " (id,name,birthday) VALUES (?,?) ",new Object[]{"魔乐科技","1000-09-12"} ) ;
			this.db.execSQL("INSERT INTO " + TABLENAME + " (name,birthday) VALUES (?,?) ",new Object[]{"董鸣楠","1982-08-03"} ) ;
			this.db.setTransactionSuccessful() ;	// 正常提交
		} catch (Exception e) {
			e.printStackTrace() ;
		} finally {
			this.db.endTransaction() ;	// 结束事务
		}
		this.db.close() ;
	}
}
