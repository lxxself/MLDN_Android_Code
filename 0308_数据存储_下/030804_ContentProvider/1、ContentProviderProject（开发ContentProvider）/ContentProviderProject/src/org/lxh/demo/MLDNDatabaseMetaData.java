package org.lxh.demo;

import android.net.Uri;
import android.provider.BaseColumns;

public interface MLDNDatabaseMetaData {
	// 这个是外部访问的Authority，Content地址为：content//org.lxh.demo.membercontentprovider
	public static final String AUTHORITY = "org.lxh.demo.membercontentprovider" ;
	// 数据库的名字
	public static final String DATABASE_NAME = "mldn" ;	// 操作的数据库的名字
	public static final int VERSION = 1 ;	// 数据库的版本
	
	public static interface MemberTableMetaData extends BaseColumns{
		public static final String TABLE_NAME = "member" ;	// 表名称
		// 外部程序访问本表的Uri地址，而且名称都统一设置为CONTENT_URI
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + TABLE_NAME);
		// 得到member表中的全部记录
		public static final String CONTACT_LIST = "vnd.android.cursor.dir/vnd.mldncontentprovider.member" ;
		// 取得一个member的信息，相当于就是按照ID查询
		public static final String CONTACT_ITEM = "vnd.android.cursor.item/vnd.mldncontentprovider.member" ;
		
		public static final String MEMBER_NAME = "name" ;
		public static final String MEMBER_AGE = "age" ;
		public static final String MEMBER_BIRTHDAY = "birthday" ;
		
		public static final String SORT_ORDER = "_id DESC" ;// 排序操作
	}
}
