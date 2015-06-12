package org.lxh.demo;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MyGoogleMapDemo extends MapActivity {
	private MapView mapView = null;
	private int longitudeE6 = 0;
	private int latitudeE6 = 0;
	private LocationManager locationManager = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.mapView = (MapView) super.findViewById(R.id.mapview); // 取得地图视图
		this.mapView.setBuiltInZoomControls(true);
		// 给定一个坐标：天安门的坐标：116.3975060，39.9087110
		this.longitudeE6 = (int) (116.3975060 * 1E6);
		this.latitudeE6 = (int) (39.9087110 * 1E6);
		GeoPoint point = new GeoPoint(this.latitudeE6, this.longitudeE6); // 要标记的坐标
		MyLocationOverlay myloc = new MyLocationOverlay(this, this.mapView);
		myloc.enableMyLocation(); // 注册GPS更新我的位置
		myloc.enableCompass(); // 开启磁场感应
		this.mapView.getOverlays().add(myloc);
		MapController mapController = this.mapView.getController();
		mapController.animateTo(point); // 设置坐标的动画
		mapController.setCenter(point);
		mapController.setZoom(16); // 最大的级别是16

		this.locationManager = (LocationManager) super
				.getSystemService(Context.LOCATION_SERVICE);
		this.locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 0, 0, new LocationListenerImpl());
	}

	private class LocationListenerImpl implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			MyGoogleMapDemo.this.longitudeE6 = (int) (location.getLongitude() * 1E6);
			MyGoogleMapDemo.this.latitudeE6 = (int) (location.getLatitude() * 1E6);
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}