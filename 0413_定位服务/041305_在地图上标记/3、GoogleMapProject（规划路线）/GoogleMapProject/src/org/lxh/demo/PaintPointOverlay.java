package org.lxh.demo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class PaintPointOverlay extends Overlay {
	private GeoPoint geoPoint = null;

	public PaintPointOverlay(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		Point point = new Point();
		Projection projection = mapView.getProjection();
		projection.toPixels(this.geoPoint, point); // 将地图上坐标的点设置为绘图屏幕的点
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		canvas.drawCircle(point.x, point.y, 6, paint);
	}

}
