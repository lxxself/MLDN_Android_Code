package org.lxh.demo;

import android.database.sqlite.SQLiteDatabase;

public class MytabOperate {
	private static final String TABLENAME = "mytab"; // 表示要操作的数据表名称
	private SQLiteDatabase db = null; // 数据库操作

	public MytabOperate(SQLiteDatabase db) {
		this.db = db;
	}
	public void insert(String name,String birthday) {
		String sql = "INSERT INTO " + TABLENAME + "(name,birthday) VALUES (?,?)";
		Object args[] = new Object[] { name, birthday };
		this.db.execSQL(sql, args);
		this.db.close() ;
	}

	public void update(int id, String name, String birthday) {
		String sql = "UPDATE " + TABLENAME + " SET name=?,birthday=? WHERE id=?";
		Object args[] = new Object[] { name, birthday, id };
		this.db.execSQL(sql, args); 
		this.db.close() ;
	}
	
	public void delete(int id) {
		String sql = "DELETE FROM " + TABLENAME + " WHERE id=?";
		Object args[] = new Object[] { id };
		this.db.execSQL(sql,args) ;
		this.db.close() ;
	}
}
