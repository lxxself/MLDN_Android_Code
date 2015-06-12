package org.lxh.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MyJSONDemo extends Activity {
	private TextView msg = null ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.msg = (TextView) super.findViewById(R.id.msg) ;
		String str = "[{\"id\":1,\"name\":\"李兴华\",\"age\":30},"
				+ "{\"id\":2,\"name\":\"MLDN\",\"age\":10}]";
		StringBuffer buf = new StringBuffer() ;
		try {
			List<Map<String,Object>> all = this.parseJson(str) ;
			Iterator<Map<String,Object>> iter = all.iterator() ;
			while(iter.hasNext()){
				Map<String,Object> map = iter.next() ;
				buf.append("ID：" + map.get("id") + "，姓名：" + map.get("name")
						+ "，年龄：" + map.get("age") + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.msg.setText(buf) ;
	}

	private List<Map<String, Object>> parseJson(String data) throws Exception {
		List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
		JSONArray jsonArr = new JSONArray(data); // 是数组
		for (int x = 0; x < jsonArr.length(); x++) {
			Map<String, Object> map = new HashMap<String, Object>();
			JSONObject jsonObj = jsonArr.getJSONObject(x);
			map.put("id", jsonObj.getInt("id"));
			map.put("name", jsonObj.getString("name"));
			map.put("age", jsonObj.getInt("age"));
			all.add(map);
		}
		return all;
	}
}