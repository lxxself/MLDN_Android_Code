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

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.msg = (TextView) super.findViewById(R.id.msg) ;
		String str = "{\"memberdata\":[{\"id\":1,\"name\":\"李兴华\",\"age\":30},"
				+ "{\"id\":2,\"name\":\"MLDN\",\"age\":10}],\"company\":\"北京魔乐科技软件学院\"}";
		StringBuffer buf = new StringBuffer() ;
		try {
			Map<String, Object> result = this.parseJson(str) ;	// 解析文本
			buf.append("公司名称：" + result.get("company") + "\n");
			List<Map<String,Object>> all = (List<Map<String,Object>>) result.get("memberdata") ; 
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

	private Map<String, Object> parseJson(String data) throws Exception {
		Map<String, Object> allMap = new HashMap<String, Object>();
		JSONObject allData = new JSONObject(data) ;	// 全部的内容变为一个项
		allMap.put("company", allData.getString("company")); // 取出项
		JSONArray jsonArr = allData.getJSONArray("memberdata"); // 取出数组
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>() ;
		for (int x = 0; x < jsonArr.length(); x++) {
			Map<String, Object> map = new HashMap<String, Object>();
			JSONObject jsonObj = jsonArr.getJSONObject(x);
			map.put("id", jsonObj.getInt("id"));
			map.put("name", jsonObj.getString("name"));
			map.put("age", jsonObj.getInt("age"));
			all.add(map);
		}
		allMap.put("memberdata", all) ;
		return allMap;
	}
}