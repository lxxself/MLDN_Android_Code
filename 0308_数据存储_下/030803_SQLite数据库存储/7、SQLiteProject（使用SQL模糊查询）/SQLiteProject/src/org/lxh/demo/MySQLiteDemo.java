package org.lxh.demo;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MySQLiteDemo extends Activity {
	private Button findBut = null ;
	private SQLiteOpenHelper helper = null ;
	private LinearLayout mylayout = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.findBut = (Button) super.findViewById(R.id.findBut) ;
		this.mylayout = (LinearLayout) super.findViewById(R.id.mylayout) ;
		this.findBut.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			MySQLiteDemo.this.helper = new MyDatabaseHelper(MySQLiteDemo.this);
			ListView listView = new ListView(MySQLiteDemo.this) ;
			listView.setAdapter(	// 要设置数据
					new ArrayAdapter<String>(	// 所有的数据是字符串
							MySQLiteDemo.this,	// 上下文对象
					android.R.layout.simple_list_item_1,	// 列表显示的布局 
					new MytabCursor(	// 实例化查询
							MySQLiteDemo.this.helper.getReadableDatabase())	// 取得SQLiteDatabase对象
							.find()));	// 调用find()方法，返回List<String> ;
			MySQLiteDemo.this.mylayout.addView(listView) ;
		}
		
	}
}