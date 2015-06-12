package org.lxh.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MyWebDemo extends Activity {
	private TextView info = null;
	private static final String URL = "http://114.249.165.249/mldn/android.jsp"; // 请求地址

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.info = (TextView) super.findViewById(R.id.info);
		boolean flag = false; // 成功与否的标记
		try {
			HttpPost request = new HttpPost(URL);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id", "lixinghua"));
			params.add(new BasicNameValuePair("password", "mldn"));
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = new DefaultHttpClient().execute(request);
			if(response.getStatusLine().getStatusCode() != 404) {	// 现在已经发现了数据了
				flag = Boolean.parseBoolean(EntityUtils.toString(
						response.getEntity()).trim());
			}
		} catch (Exception e) {
			info.setText("WEB服务器连接失败！");
		}
		if (flag) {
			info.setText("用户登录成功！");
		} else {
			info.setText("用户登录失败！");
		}
	}
}