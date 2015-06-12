package org.lxh.demo;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MySQLiteDemo extends Activity {
	private ListView listView ;
	private SimpleAdapter simpleAdapter = null ;
	private LinearLayout loadLayout = null ;	// 读取的脚标的视图
	private TextView loadInfo = null ;	// 进行信息提示
	private List<Map<String,Object>> all = null ;
	private LayoutParams layoutParams = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.FILL_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT);	// 表示新组件的布局参数
	private SQLiteOpenHelper helper = null ;
	private LinearLayout mylayout = null ;
	private int currentPage = 1 ;
	private int lineSize = 15 ;
	private int allRecorders = 0 ;
	private int pageSize = 1 ;	// 默认在一共只有1页
	private int lastItem = 0 ;	// 保存最后一项
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.mylayout = (LinearLayout) super.findViewById(R.id.mylayout) ;
		this.loadLayout = new LinearLayout(this) ;	// 定义脚标的线性布局管理器
		this.loadInfo = new TextView(this) ;	// 文本组件
		this.loadInfo.setText("数据加载中ing...") ;	// 定义提示文字
		this.loadInfo.setGravity(Gravity.CENTER) ;	// 文字居中显示
		this.loadInfo.setTextSize(30.0f) ;	// 文字大小
		this.loadLayout.addView(this.loadInfo,this.layoutParams) ;	// 增加组件
		this.loadLayout.setGravity(Gravity.CENTER) ;
		this.showAllData() ;	// 数据显示
		this.pageSize = (this.allRecorders + this.lineSize - 1)
				/ this.lineSize; // 计算总页数
		System.out.println("pageSize = " + this.pageSize) ;
		System.out.println("allRecorders = " + this.allRecorders); 
	}
	private void showAllData(){	// 读取全部的数据
		MySQLiteDemo.this.helper = new MyDatabaseHelper(MySQLiteDemo.this);
		MytabCursor cur = new MytabCursor(	// 实例化查询
				MySQLiteDemo.this.helper.getReadableDatabase()) ;	// 取得SQLiteDatabase对象
		this.allRecorders = cur.getCount() ;	// 取得全部记录数
		this.listView = new ListView(MySQLiteDemo.this) ;
		MySQLiteDemo.this.all = cur.find(MySQLiteDemo.this.currentPage,MySQLiteDemo.this.lineSize) ;
		this.listView.addFooterView(this.loadLayout) ;	// 增加读取数据的布局文件
		this.simpleAdapter = new SimpleAdapter(MySQLiteDemo.this, 	// 上下文对象
				MySQLiteDemo.this.all,			// 所有要操作的数据
				R.layout.tab_info,				// 布局管理器 
				new String[] { "id", "name", "birthday" },	// map中的key
				new int[] { R.id.id, R.id.name, R.id.birthday }) ;
		this.listView.setAdapter(this.simpleAdapter);	// 布局管理中的id
		this.listView.setOnScrollListener(new OnScrollListenerImpl()) ;
		this.mylayout.addView(listView) ;
	}
	private class OnScrollListenerImpl implements OnScrollListener{

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			MySQLiteDemo.this.lastItem = firstVisibleItem + visibleItemCount - 1 ;	// 统计是否到最后
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if (MySQLiteDemo.this.lastItem == MySQLiteDemo.this.simpleAdapter
					.getCount()// 已经是最底部
					&& MySQLiteDemo.this.currentPage < MySQLiteDemo.this.pageSize 	// 还有数据没读取完
					&& scrollState == OnScrollListener.SCROLL_STATE_IDLE ) {	// 不再滑动
				MySQLiteDemo.this.currentPage ++ ;
				MySQLiteDemo.this.listView.setSelection(MySQLiteDemo.this.lastItem) ;	// 设置显示位置
				MySQLiteDemo.this.appendData() ;	// 增加数据
			}
		}
		
	}
	
	private void appendData(){	// 增加数据
		MytabCursor cur = new MytabCursor(	// 实例化查询
				MySQLiteDemo.this.helper.getReadableDatabase()) ;	// 取得SQLiteDatabase对象
		List<Map<String, Object>> newData = cur.find(this.currentPage,
				this.lineSize);
		this.all.addAll(newData) ;	// 集合改变
		this.simpleAdapter.notifyDataSetChanged() ;	// 通知记录改变
	}
}