package org.lxh.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

public class MyJSONDemo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		String data[] = new String[] { "www.mldnjava.cn", "lixinghua",
				"bbs.mldn.cn" }; // 要输出的数据
		JSONObject allData = new JSONObject(); // 建立最外面的节点对象
		JSONArray sing = new JSONArray(); // 定义数组
		for (int x = 0; x < data.length; x++) { // 将数组内容配置到相应的节点
			JSONObject temp = new JSONObject(); // 每一个包装的数据都是JSONObject
			try {
				temp.put("myurl", data[x]);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			sing.put(temp); // 保存多个JSONObject
		}
		try {
			allData.put("urldata", sing);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) { // 不存在不操作
			return; // 返回到程序的被调用处
		}
		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "mldndata" + File.separator + "json.txt"); // 要输出文件的路径
		if (!file.getParentFile().exists()) { // 文件不存在
			file.getParentFile().mkdirs(); // 创建文件夹
		}
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream(file));
			out.print(allData.toString()); // 将数据变为字符串后保存
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close(); // 关闭输出
			}
		}
	}
}