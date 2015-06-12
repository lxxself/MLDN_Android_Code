package org.lxh.demo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MemberContentProvider extends ContentProvider {
	private static UriMatcher uriMatcher = null ;	// 地址转换
	private static final int GET_MEMBER_LIST = 1 ;	// 得到全部的数据
	private static final int GET_MEMBER_ITEM = 2 ;	// 取得一个数据
	private MyDatabaseHelper helper = null ;
	static {	// 静态代码块
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH) ;	// 实例化对象
		uriMatcher.addURI(MLDNDatabaseMetaData.AUTHORITY,
				MLDNDatabaseMetaData.MemberTableMetaData.TABLE_NAME,
				GET_MEMBER_LIST);	// 取得全部数据的匹配地址
		uriMatcher.addURI(MLDNDatabaseMetaData.AUTHORITY,
				MLDNDatabaseMetaData.MemberTableMetaData.TABLE_NAME + "/#",
				GET_MEMBER_ITEM);	// 取得一个数据的匹配地址
	}

	@Override
	public String getType(Uri uri) { // 取得数据的类型的
		switch (uriMatcher.match(uri)) { // 匹配传入进来的Uri的类型
		case GET_MEMBER_LIST:
			return MLDNDatabaseMetaData.MemberTableMetaData.CONTACT_LIST;
		case GET_MEMBER_ITEM:
			return MLDNDatabaseMetaData.MemberTableMetaData.CONTACT_ITEM;
		default:
			throw new UnsupportedOperationException("Not Support Operation :"
					+ uri);
		}
	}

	@Override
	public boolean onCreate() {
		this.helper = new MyDatabaseHelper(super.getContext()) ;
		return true;	// 操作成功了
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs)  {
		SQLiteDatabase db = this.helper.getWritableDatabase() ;// 以写方式打开数据库
		int result = 0	;	// 操作结果
		switch (uriMatcher.match(uri)) {
		case GET_MEMBER_LIST:
			result = db.delete(
					MLDNDatabaseMetaData.MemberTableMetaData.TABLE_NAME,
					selection, selectionArgs);	// 删除全部
			break ;
		case GET_MEMBER_ITEM:
			long id = ContentUris.parseId(uri) ;	// 找到一个数据的ID
			String where = "_id=" + id ;
			result = db.delete(
					MLDNDatabaseMetaData.MemberTableMetaData.TABLE_NAME, where,
					selectionArgs);
			break ;
		default:
			throw new UnsupportedOperationException("Not Support Operation :"
					+ uri);
		}
		return result;
	}



	@Override
	public Uri insert(Uri uri, ContentValues values) {	// content://org.lxh.demo.membercontentprovider/member
		SQLiteDatabase db = this.helper.getWritableDatabase() ;// 以写方式打开数据库
		long id = 0 ;	// 取得增长后的数据ID
		switch(uriMatcher.match(uri)) {
		case GET_MEMBER_LIST :
			id = db.insert(MLDNDatabaseMetaData.MemberTableMetaData.TABLE_NAME,
					MLDNDatabaseMetaData.MemberTableMetaData._ID, values);
			String uriPath = uri.toString() ;
			String path = uriPath + "/" + id ;
			return Uri.parse(path) ;
		case GET_MEMBER_ITEM :
			return null ; 
		default:
			throw new UnsupportedOperationException("Not Support Operation :"
					+ uri);
		}
	}



	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = this.helper.getWritableDatabase() ;// 以写方式打开数据库
		switch (uriMatcher.match(uri)) {
		case GET_MEMBER_LIST:
			return db
					.query(MLDNDatabaseMetaData.MemberTableMetaData.TABLE_NAME,
							projection, selection, selectionArgs, null, null,
							sortOrder);
		case GET_MEMBER_ITEM:
			long id = ContentUris.parseId(uri) ;	// 找到ID
			String where = "_id=" + id ;
			return db
					.query(MLDNDatabaseMetaData.MemberTableMetaData.TABLE_NAME,
							projection, where, selectionArgs, null, null,
							sortOrder);
		default:
			throw new UnsupportedOperationException("Not Support Operation :"
					+ uri);
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = this.helper.getWritableDatabase() ;// 以写方式打开数据库
		int result = 0 ;	// 表示结果
		switch (uriMatcher.match(uri)) {
		case GET_MEMBER_LIST:
			result = db.update(
					MLDNDatabaseMetaData.MemberTableMetaData.TABLE_NAME,
					values, null, null);
			break ;
		case GET_MEMBER_ITEM:
			long id = ContentUris.parseId(uri) ;	// 找到里面的id
			String where = "_id=" + id ;
			result = db.update(
					MLDNDatabaseMetaData.MemberTableMetaData.TABLE_NAME,
					values, where, selectionArgs);
			break ;
		default:
			throw new UnsupportedOperationException("Not Support Operation :"
					+ uri);
		}
		return result ; 
	}

}
