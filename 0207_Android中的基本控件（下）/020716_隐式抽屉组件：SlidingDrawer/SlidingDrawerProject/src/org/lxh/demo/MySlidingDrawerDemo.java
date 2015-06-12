package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.Toast;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;

public class MySlidingDrawerDemo extends Activity {
	private String data[] = new String[] { "北京魔乐科技", "www.mldnjava.cn",
			"讲师：李兴华", "中国高校讲课联盟", "www.jiangker.com" };	// ListView填充数据
	private ListView listView = null ;
	private SlidingDrawer slidingDrawer = null ;
	private ImageView handle = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		LinearLayout layout = (LinearLayout) super.findViewById(R.id.content) ;	// 向里面保存ListView
		this.listView = new ListView(this) ;
		this.listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, this.data));
		layout.addView(this.listView) ;
		
		this.slidingDrawer = (SlidingDrawer) super.findViewById(R.id.slidingdrawer) ;
		this.handle = (ImageView) super.findViewById(R.id.handle) ;
		
		this.slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListenerImpl()) ;
		this.slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListenerImpl()) ;
		this.slidingDrawer.setOnDrawerScrollListener(new OnDrawerScrollListenerImpl()) ;
	}
	
	private class OnDrawerOpenListenerImpl implements OnDrawerOpenListener {

		@Override
		public void onDrawerOpened() {
			MySlidingDrawerDemo.this.handle.setImageResource(R.drawable.ico_right) ;
		}
		
	}
	
	private class OnDrawerCloseListenerImpl implements OnDrawerCloseListener {

		@Override
		public void onDrawerClosed() {
			MySlidingDrawerDemo.this.handle.setImageResource(R.drawable.ico_left) ;
		}
		
	}
	
	private class OnDrawerScrollListenerImpl implements OnDrawerScrollListener {

		@Override
		public void onScrollEnded() {
			Toast.makeText(MySlidingDrawerDemo.this, "窗口拖动结束。", Toast.LENGTH_SHORT).show() ;
		}

		@Override
		public void onScrollStarted() {
			Toast.makeText(MySlidingDrawerDemo.this, "正在拖动窗口。", Toast.LENGTH_SHORT).show() ;
		}
		
	}
}