package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class MyExpandableListViewDemo extends Activity {
	private ExpandableListView elistview = null ;
	private ExpandableListAdapter adapter = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.elistview = (ExpandableListView) super.findViewById(R.id.elistview) ;
		this.adapter = new MyExpandableListAdapter(this) ;
		this.elistview.setAdapter(this.adapter) ;
	}
}