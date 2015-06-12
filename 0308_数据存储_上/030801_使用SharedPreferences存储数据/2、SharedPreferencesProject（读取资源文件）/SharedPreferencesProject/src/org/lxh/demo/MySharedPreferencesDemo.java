package org.lxh.demo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MySharedPreferencesDemo extends Activity {
	private static final String FILENAME = "mldn"; // 保存的文件名称
	
	private TextView authorinfo = null ;
	private TextView ageinfo = null ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		this.authorinfo = (TextView) super.findViewById(R.id.authorinfo) ;
		this.ageinfo = (TextView) super.findViewById(R.id.ageinfo) ;

		SharedPreferences share = super.getSharedPreferences(FILENAME,
				Activity.MODE_PRIVATE);

		this.authorinfo.setText("作者：" + share.getString("author", "没有作者信息。")) ;
		this.ageinfo.setText("年龄：" + share.getInt("age", 0)) ;
	}
}