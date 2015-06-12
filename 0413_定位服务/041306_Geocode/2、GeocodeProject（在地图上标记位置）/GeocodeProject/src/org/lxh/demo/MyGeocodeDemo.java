package org.lxh.demo;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MyGeocodeDemo extends MapActivity {
	private EditText msg = null;
	private Button search = null;
	private MapView mapView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.search = (Button) super.findViewById(R.id.search);
		this.msg = (EditText) super.findViewById(R.id.msg);
		this.mapView = (MapView) super.findViewById(R.id.mapview);
		this.search.setOnClickListener(new SearchOnClickListenerImpl());
	}

	private class SearchOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			String msg = MyGeocodeDemo.this.msg.getText().toString() ;
			new SearchAsyncTask(msg).execute(0);
		}

	}

	// 此时要根据异步处理的工具类来向Google上进行数据的提交操作，而后通过返回的JSON数据解析出具体的数据内容
	private class SearchAsyncTask extends AsyncTask<Integer, String, Integer> {
		private String location;

		public SearchAsyncTask(String location) {
			this.location = location;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			GeoPoint point = new GeoPoint(
					(int) (Double.parseDouble(values[0]) * 1E6),
					(int) (Double.parseDouble(values[1]) * 1E6));
			OverlayItem overlayItem = new OverlayItem(point, "您的位置！", values[2]);
			Drawable drawable = MyGeocodeDemo.this.getResources().getDrawable(
					R.drawable.arrow);
			MyOverlayImpl mol = new MyOverlayImpl(drawable, MyGeocodeDemo.this);
			mol.addOverlayItem(overlayItem) ;
			MyGeocodeDemo.this.mapView.getOverlays().add(mol) ;
			MyGeocodeDemo.this.mapView.setBuiltInZoomControls(true) ;
			MyGeocodeDemo.this.mapView.getController().animateTo(point) ;
		}

		@Override
		protected Integer doInBackground(Integer... arg0) {
			Map<String, String> map = null;
			try {
				map = this.parseJson(this.location);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if ("OK".equals(map.get("status"))) { // 操作正常
				this.publishProgress(map.get("latitude"), map.get("longitude"),
						map.get("address")); // 将address数据返回
			} else {
				this.publishProgress("没有查询结果！");
			}
			return null;
		}

		private Map<String, String> parseJson(String location) throws Exception {
			Map<String, String> allMap = new HashMap<String, String>();
			StringBuffer buf = new StringBuffer();
			InputStream input = null;
			try {
				URL url = new URL(
						"http://maps.google.com/maps/api/geocode/json?address="
								+ URLEncoder.encode(location, "UTF-8")
								+ "&sensor=false");
				input = url.openStream();
				Scanner scan = new Scanner(input);
				while (scan.hasNext()) {
					buf.append(scan.next()); // 所有的数据都保存在字符串里面
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				input.close();
			}
			JSONObject allData = new JSONObject(buf.toString());
			allMap.put("status", allData.getString("status"));
			JSONArray jsonArr = allData.getJSONArray("results");
			JSONObject jsonObj = jsonArr.getJSONObject(0);
			allMap.put("address", jsonObj.getString("formatted_address"));
			JSONObject locationJsonObj = jsonObj.getJSONObject("geometry")
					.getJSONObject("location");
			allMap.put("latitude", locationJsonObj.getString("lat")) ;
			allMap.put("longitude", locationJsonObj.getString("lng")) ;
			return allMap;
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}