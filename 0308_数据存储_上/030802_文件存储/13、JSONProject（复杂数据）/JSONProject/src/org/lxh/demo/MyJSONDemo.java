package org.lxh.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

public class MyJSONDemo extends Activity {
	private String nameData[] = new String[] { "李兴华", "魔乐科技", "MLDN" };
	private int ageData[] = new int[] { 30, 5, 7 };
	private boolean isMarraiedData[] = new boolean[] { false, true, false };
	private double salaryData[] = new double[] { 3000.0, 5000.0, 9000.0 };
	private Date birthdayData[] = new Date[] { new Date(), new Date(),
			new Date() };
	private String companyName = "北京魔乐科技软件学院（MLDN软件实训中心）" ;
	private String companyAddr = "北京市西城区美江大厦6层" ;
	private String companyTel = "010-51283346" ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		JSONObject allData = new JSONObject(); // 建立最外面的节点对象
		JSONArray sing = new JSONArray(); // 定义数组
		for (int x = 0; x < this.nameData.length; x++) { // 将数组内容配置到相应的节点
			JSONObject temp = new JSONObject(); // 每一个包装的数据都是JSONObject
			try {
				temp.put("name", this.nameData[x]);
				temp.put("age", this.ageData[x]);
				temp.put("married", this.isMarraiedData[x]);
				temp.put("salary", this.salaryData[x]);
				temp.put("birthday", this.birthdayData[x]);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			sing.put(temp); // 保存多个JSONObject
		}
		try {
			allData.put("persondata", sing);
			allData.put("company", this.companyName) ; 
			allData.put("address", this.companyAddr) ;
			allData.put("telephone", this.companyTel) ;
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