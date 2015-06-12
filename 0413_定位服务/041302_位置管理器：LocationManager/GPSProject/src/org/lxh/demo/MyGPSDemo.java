package org.lxh.demo;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MyGPSDemo extends Activity {
	private TextView msg = null;
	private LocationManager locationManager = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.msg = (TextView) super.findViewById(R.id.msg);
		this.locationManager = (LocationManager) super
				.getSystemService(Context.LOCATION_SERVICE);
		this.locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 1000, 1,
				new LocationListenerImpl());
	}

	private class LocationListenerImpl implements LocationListener {

		@Override
		public void onLocationChanged(Location location) { // 位置改变
			MyGPSDemo.this.msg.setText("用户位置发生改变，新的数据：\n" + "经度："
					+ location.getLongitude() + "\n" + "纬度："
					+ location.getLatitude() + "\n" + "数据准确度："
					+ location.getAccuracy() + "\n" + "时间："
					+ location.getTime() + "\n" + "速度：" + location.getSpeed()
					+ "\n" + "方位：" + location.getBearing());
		}

		@Override
		public void onProviderDisabled(String provider) {

		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

	}
}