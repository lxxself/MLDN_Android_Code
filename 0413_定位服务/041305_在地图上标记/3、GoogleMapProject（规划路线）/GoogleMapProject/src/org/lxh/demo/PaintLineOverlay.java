package org.lxh.demo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class PaintLineOverlay extends Overlay {
	private GeoPoint beginGeoPoint = null;
	private GeoPoint endGeoPoint = null;

	public PaintLineOverlay(GeoPoint beginGeoPoint, GeoPoint endGeoPoint) {
		this.beginGeoPoint = beginGeoPoint;
		this.endGeoPoint = endGeoPoint;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		Paint paint = new Paint();
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setStrokeWidth(3);
		paint.setColor(Color.RED);
		Point beginPoint = new Point();
		Point endPoint = new Point();
		Projection projection = mapView.getProjection();
		projection.toPixels(this.beginGeoPoint, beginPoint);
		projection.toPixels(this.endGeoPoint, endPoint);
		canvas.drawLine(beginPoint.x, beginPoint.y, endPoint.x, endPoint.y,
				paint);
	}

}
