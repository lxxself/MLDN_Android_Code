package org.lxh.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MyActivityRunDemo extends Activity {
	private ListView tasklist = null ;
	private ListAdapter adapter = null ;
	private List<String> all = new ArrayList<String>() ;
	private ActivityManager activityManager = null ;
	private List<ActivityManager.RunningServiceInfo> allTaskInfo ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.tasklist = (ListView) super.findViewById(R.id.tasklist) ;
		this.activityManager = (ActivityManager) super
				.getSystemService(Context.ACTIVITY_SERVICE);
		this.listActivity() ;
	}

	private void listActivity() {
		this.allTaskInfo = this.activityManager.getRunningServices(30) ;
		Iterator<ActivityManager.RunningServiceInfo> iterInfo = this.allTaskInfo.iterator() ;
		while(iterInfo.hasNext()) {
			ActivityManager.RunningServiceInfo service = iterInfo.next() ;
			this.all.add("¡¾ID = " + service.pid + " ¡¿ "
					+ service.process);
		}
		this.adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, this.all);
		this.tasklist.setAdapter(this.adapter) ;
	}
}