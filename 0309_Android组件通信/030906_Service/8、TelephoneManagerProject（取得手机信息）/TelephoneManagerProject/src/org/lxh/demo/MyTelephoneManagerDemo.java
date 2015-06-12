package org.lxh.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MyTelephoneManagerDemo extends Activity {
	private ListView infolist = null ;
	private ListAdapter adapter = null ;
	private List<String> all = new ArrayList<String>() ;
	private TelephonyManager manager = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.infolist = (ListView) super.findViewById(R.id.infolist) ;
		this.manager = (TelephonyManager) super
				.getSystemService(Context.TELEPHONY_SERVICE); // 电话服务
		this.list() ;
	}
	private void list(){	// 列出一些信息
		this.all.add(this.manager.getLine1Number() == null ? "没有手机号码" : "手机号码："
				+ this.manager.getLine1Number());
		this.all.add(this.manager.getNetworkOperatorName() == null ? "没有移动服务商"
				: "移动服务商：" + this.manager.getNetworkOperatorName());
		if (this.manager.getPhoneType() == TelephonyManager.NETWORK_TYPE_CDMA) {
			this.all.add("移动网络：CDMA");
		} else if (this.manager.getPhoneType() == TelephonyManager.NETWORK_TYPE_GPRS) {
			this.all.add("移动网络：GPRS");
		} else {
			this.all.add("移动网络：未知");
		}
		if (this.manager.getNetworkType() == TelephonyManager.PHONE_TYPE_GSM) {
			this.all.add("网络类型：GSM");
		} else if (this.manager.getNetworkType() == TelephonyManager.PHONE_TYPE_CDMA) {
			this.all.add("网络类型：CDMA");
		} else {
			this.all.add("网络类型：未知");
		}
		this.all.add("是否漫游：" + (this.manager.isNetworkRoaming() ? "漫游" : "非漫游"));
		this.adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, this.all);
		this.infolist.setAdapter(this.adapter) ;
	}
}