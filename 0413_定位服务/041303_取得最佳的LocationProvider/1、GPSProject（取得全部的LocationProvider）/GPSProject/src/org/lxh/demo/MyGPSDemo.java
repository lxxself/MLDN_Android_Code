package org.lxh.demo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MyGPSDemo extends Activity {
	private ListView allData = null;
	private LocationManager locationManager = null;
	private ListAdapter adapter = null ;
	private List<String> allProviders = null ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.allData = (ListView) super.findViewById(R.id.alldata);
		this.locationManager = (LocationManager) super
				.getSystemService(Context.LOCATION_SERVICE);
		this.listProviders() ;
	}
	private void listProviders(){	// 列出全部的数据提供者
		this.allProviders = this.locationManager.getAllProviders();
		this.adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, this.allProviders); 
		this.allData.setAdapter(this.adapter) ;
	}
}