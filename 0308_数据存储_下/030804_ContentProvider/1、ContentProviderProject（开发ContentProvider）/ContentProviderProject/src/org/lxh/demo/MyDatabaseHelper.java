package org.lxh.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	public MyDatabaseHelper(Context context) {
		super(context, MLDNDatabaseMetaData.DATABASE_NAME, null,
				MLDNDatabaseMetaData.VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE "
				+ MLDNDatabaseMetaData.MemberTableMetaData.TABLE_NAME + " ( "
				+ MLDNDatabaseMetaData.MemberTableMetaData._ID
				+ "	INTEGER 	PRIMARY KEY ,"
				+ MLDNDatabaseMetaData.MemberTableMetaData.MEMBER_NAME
				+ "	VARCHAR(50)	NOT NULL ,"
				+ MLDNDatabaseMetaData.MemberTableMetaData.MEMBER_AGE
				+ "	INTEGER	NOT NULL ,"
				+ MLDNDatabaseMetaData.MemberTableMetaData.MEMBER_BIRTHDAY
				+ "	DATE	NOT NULL" + " )";
		db.execSQL(sql) ;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + MLDNDatabaseMetaData.MemberTableMetaData.TABLE_NAME ;
		db.execSQL(sql) ;
		this.onCreate(db) ;
	}

}
