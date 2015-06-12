package org.lxh.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;

public class BallView extends View implements SensorEventListener {
	private Bitmap ball = null;
	private float[] allValue;
	private Point point = new Point();
	private int xSpeed = 0;
	private int ySpeed = 0;

	public BallView(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setBackgroundColor(Color.WHITE); // 底色为白色
		this.ball = BitmapFactory.decodeResource(super.getResources(),
				R.drawable.ball);
		SensorManager manager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE); // 现在只是找到了一个传感器，但是没有定义类型
		manager.registerListener(this,
				manager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME); // 创建了一个适合于游戏操作的方位传感器
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) { // 传感器方位改变
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) { // 现在是方位传感器
			float value[] = event.values; // 取得所有的偏离数据
			BallView.this.allValue = value; // 取得三个轴的值
			super.postInvalidate(); // 主线程的现实需要重绘
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint p = new Paint(); // 根据传感器的数值来改变球的速度
		if (this.allValue != null) { // 已经取得了数据
			this.xSpeed = (int) -this.allValue[2]; // 计算X轴速度
			this.ySpeed = (int) -this.allValue[1];
		}
		this.point.x += this.xSpeed;
		this.point.y += this.ySpeed;
		if (this.point.x < 0) {
			this.point.x = 0;
		}
		if (this.point.y < 0) {
			this.point.y = 0;
		}
		if (point.x > super.getWidth() - this.ball.getWidth()) { // X轴已经显示过了
			this.point.x = super.getWidth() - this.ball.getWidth();
		}
		if (point.y > super.getHeight() - this.ball.getHeight()) {
			this.point.y = super.getHeight() - this.ball.getWidth(); // 设置Y 轴的边界
		}
		canvas.drawBitmap(this.ball, this.point.x, this.point.y, p);
	}

}
