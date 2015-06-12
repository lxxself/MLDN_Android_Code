package org.lxh.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyContentProviderDemo extends Activity {
	private Button insertBut = null ;	// 操作按钮
	private Button updateBut = null ;
	private Button deleteBut = null ;
	private Button queryBut = null ;
	private TextView mainInfo = null ;
	private ListView memberList = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.mainInfo = (TextView) super.findViewById(R.id.mainInfo) ;	// 取得组件
		this.insertBut = (Button) super.findViewById(R.id.insertBut) ;	// 取得组件
		this.updateBut = (Button) super.findViewById(R.id.updateBut) ;	// 取得组件
		this.deleteBut = (Button) super.findViewById(R.id.deleteBut) ;	// 取得组件
		this.queryBut = (Button) super.findViewById(R.id.queryBut) ;	// 取得组件
		this.memberList = (ListView) super.findViewById(R.id.memberList) ;	// 取得组件
		this.insertBut.setOnClickListener(new InsertOnClickListener()) ;
		this.updateBut.setOnClickListener(new UpdateOnClickListener()) ;
		this.deleteBut.setOnClickListener(new DeleteOnClickListener()) ;
		this.queryBut.setOnClickListener(new QueryOnClickListener()) ;
	}
	private class InsertOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyContentProviderDemo.this.mainInfo.setText("执行的是增加操作...") ;
			long id = 0 ;	// 接收返回的id数据
			id = MyContentProviderDemo.this.testInsert("李兴华", 30,
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()));	// 增加数据
			Toast.makeText(MyContentProviderDemo.this, "数据增加成功，ID为：" + id,
					Toast.LENGTH_LONG).show();
		}
		
	}
	private class DeleteOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyContentProviderDemo.this.mainInfo.setText("执行的是删除操作...") ;
			long result = 0 ;	// 接收返回的id数据
			result = MyContentProviderDemo.this.testDelete(String.valueOf(4)); // 更新数据
			Toast.makeText(MyContentProviderDemo.this, "删除了" + result + "条记录",
					Toast.LENGTH_LONG).show();
		}
		
	}
	private class QueryOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyContentProviderDemo.this.mainInfo.setText("执行的是查询操作...") ;
			Cursor result = MyContentProviderDemo.this.testQuery(null) ;	// 查询全部
			MyContentProviderDemo.this.startManagingCursor(result) ;	// 结果集交给系统管理
			List<Map<String,Object>> members = new ArrayList<Map<String,Object>>() ;
			for (result.moveToFirst(); !result.isAfterLast(); result
					.moveToNext()) {
				Map<String,Object> member = new HashMap<String,Object>() ;
				member.put("_id", result.getInt(0)) ;
				member.put("name", result.getString(1)) ;
				member.put("age", result.getInt(2)) ;
				member.put("birthday", result.getString(3)) ;
				members.add(member) ;
			}
			MyContentProviderDemo.this.memberList
					.setAdapter(new SimpleAdapter(MyContentProviderDemo.this,
							members, R.layout.member, new String[] { "_id",
									"name", "age", "birthday" }, new int[] {
									R.id._id, R.id.name, R.id.age,
									R.id.birthday }));
			Toast.makeText(MyContentProviderDemo.this, "数据查询成功！",
					Toast.LENGTH_LONG).show();
		}
		
	}
	private class UpdateOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			MyContentProviderDemo.this.mainInfo.setText("执行的是更新操作...") ;
			long result = 0 ;	// 接收返回的id数据
			result = MyContentProviderDemo.this.testUpdate("", "MLDN", 18,
					"1989-09-19"); // 更新数据
			Toast.makeText(MyContentProviderDemo.this, "更新了" + result + "条记录",
					Toast.LENGTH_LONG).show();
		}
		
	}
	private long testInsert(String name,int age,String birthday) {	// 执行增加操作
		ContentResolver contentResolver = super.getContentResolver() ;	// 取得ContentResolver对象
		ContentValues values = new ContentValues() ;
		values.put(MLDNDatabaseMetaData.MemberTableMetaData.MEMBER_NAME, name) ;
		values.put(MLDNDatabaseMetaData.MemberTableMetaData.MEMBER_AGE, age) ;
		values.put(MLDNDatabaseMetaData.MemberTableMetaData.MEMBER_BIRTHDAY, birthday) ;
		Uri resultUri = contentResolver.insert(
				MLDNDatabaseMetaData.MemberTableMetaData.CONTENT_URI, values);
		return ContentUris.parseId(resultUri) ;	// 解析出返回的id数据
	}

	private long testUpdate(String _id, String name, int age, String birthday) { // 执行增加操作
		long result = 0 ;	// 返回结果
		ContentResolver contentResolver = super.getContentResolver() ;	// 取得ContentResolver对象
		ContentValues values = new ContentValues() ;
		values.put(MLDNDatabaseMetaData.MemberTableMetaData.MEMBER_NAME, name) ;
		values.put(MLDNDatabaseMetaData.MemberTableMetaData.MEMBER_AGE, age) ;
		values.put(MLDNDatabaseMetaData.MemberTableMetaData.MEMBER_BIRTHDAY, birthday) ;
		
		if(_id == null || "".equals(_id)) {	// 更新全部
			result = contentResolver.update(MLDNDatabaseMetaData.MemberTableMetaData.CONTENT_URI, values, null, null) ;
		} else {	// 按照id更新
			result = contentResolver.update(
					Uri.withAppendedPath(MLDNDatabaseMetaData.MemberTableMetaData.CONTENT_URI, _id),
					values, null, null);
		}
		return result ;	// 解析出返回的id数据
	}
	
	private long testDelete(String _id) {
		ContentResolver contentResolver = super.getContentResolver() ;	// 取得ContentResolver对象
		long result = 0 ;
		if (_id == null || "".equals(_id)) {
			result = contentResolver.delete(
					MLDNDatabaseMetaData.MemberTableMetaData.CONTENT_URI, null,
					null);
		} else {
			result = contentResolver.delete(Uri.withAppendedPath(
					MLDNDatabaseMetaData.MemberTableMetaData.CONTENT_URI, _id),
					null, null);
		}
		return result ;
	}
	
	private Cursor testQuery(String id) {
		if(id == null || "".equals(id)) {	// 查询全部
			return super.getContentResolver().query(
					MLDNDatabaseMetaData.MemberTableMetaData.CONTENT_URI, null,
					null, null,
					MLDNDatabaseMetaData.MemberTableMetaData.SORT_ORDER);
		} else {
			return super
					.getContentResolver()
					.query(Uri.withAppendedPath(
							MLDNDatabaseMetaData.MemberTableMetaData.CONTENT_URI,
							id), null, null, null,
							MLDNDatabaseMetaData.MemberTableMetaData.SORT_ORDER);
		}
	}
}