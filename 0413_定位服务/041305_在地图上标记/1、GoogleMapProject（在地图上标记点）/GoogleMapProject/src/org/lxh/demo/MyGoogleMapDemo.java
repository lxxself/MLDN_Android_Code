package org.lxh.demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MyGoogleMapDemo extends MapActivity {
	private MapView mapView = null;
	private int longitudeE6 = 0;
	private int latitudeE6 = 0;

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
		Drawable drawable = super.getResources().getDrawable(R.drawable.arrow);
		MyOverlayImpl mol = new MyOverlayImpl(drawable, this); // 准备好图层
		OverlayItem overlayItem = new OverlayItem(point, "您的位置！", "我现在在天安门");
		mol.addOverlayItem(overlayItem); // 标记点出现
		this.mapView.getOverlays().add(mol) ;
		MapController mapController = this.mapView.getController();
		mapController.animateTo(point); // 设置坐标的动画
		mapController.setZoom(16) ;	// 最大的级别是16
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}