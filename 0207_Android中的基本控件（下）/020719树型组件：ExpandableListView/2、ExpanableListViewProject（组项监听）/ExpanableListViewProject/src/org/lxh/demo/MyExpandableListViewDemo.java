package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class MyExpandableListViewDemo extends Activity {
	private ExpandableListView elistview = null;
	private ExpandableListAdapter adapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.elistview = (ExpandableListView) super
				.findViewById(R.id.elistview);
		this.adapter = new MyExpandableListAdapter(this);
		this.elistview.setAdapter(this.adapter);

		this.elistview.setOnChildClickListener(new OnChildClickListenerImpl());
		this.elistview.setOnGroupClickListener(new OnGroupClickListenerImpl());
		this.elistview
				.setOnGroupExpandListener(new OnGroupExpandListenerImpl());
		this.elistview
				.setOnGroupCollapseListener(new OnGroupCollapseListenerImpl());
	}

	private class OnChildClickListenerImpl implements OnChildClickListener {

		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			Toast.makeText(
					MyExpandableListViewDemo.this,
					"子选项被选中，groupPosition = " + groupPosition
							+ "，childPosition = " + childPosition,
					Toast.LENGTH_SHORT).show();
			return false;
		}
	}

	private class OnGroupClickListenerImpl implements OnGroupClickListener {

		@Override
		public boolean onGroupClick(ExpandableListView parent, View v,
				int groupPosition, long id) {
			Toast.makeText(MyExpandableListViewDemo.this,
					"分组被选中，groupPosition = " + groupPosition,
					Toast.LENGTH_SHORT).show();
			return false;
		}

	}

	private class OnGroupExpandListenerImpl implements OnGroupExpandListener {

		@Override
		public void onGroupExpand(int groupPosition) {
			Toast.makeText(MyExpandableListViewDemo.this,
					"打开分组，groupPosition = " + groupPosition, Toast.LENGTH_SHORT)
					.show();

		}

	}

	private class OnGroupCollapseListenerImpl implements
			OnGroupCollapseListener {

		@Override
		public void onGroupCollapse(int groupPosition) {
			Toast.makeText(MyExpandableListViewDemo.this,
					"关闭分组，groupPosition = " + groupPosition, Toast.LENGTH_SHORT)
					.show();

		}

	}
}