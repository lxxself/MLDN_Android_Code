package org.lxh.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;

public class ArrowView extends View implements SensorEventListener {
	private Bitmap comp = null;
	private float[] allValue;

	public ArrowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setBackgroundColor(Color.WHITE); // 底色为白色
		this.comp = BitmapFactory.decodeResource(super.getResources(),
				R.drawable.arrow);
		SensorManager manager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE); // 现在只是找到了一个传感器，但是没有定义类型
		manager.registerListener(this,
				manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
				SensorManager.SENSOR_DELAY_GAME); // 创建了一个适合于游戏操作的磁场传感器
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) { // 传感器方位改变
		if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) { // 现在是方位传感器
			float value[] = event.values; // 取得所有的偏离数据
			ArrowView.this.allValue = value; // 取得三个轴的值
			super.postInvalidate(); // 主线程的现实需要重绘
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint p = new Paint(); // 根据传感器的数值来改变球的速度
		if (this.allValue != null) { // 已经取得了数据
			float x = this.allValue[0] ;
			float y = this.allValue[1] ;
			canvas.restore(); // 重置绘图对象
			// 设置以屏幕中心点作为旋转中心
			canvas.translate(super.getWidth() / 2, super.getHeight() / 2) ;
			// 判断y轴是否为0的旋转角度
			if (y == 0 && x > 0) {
				canvas.rotate(90) ;	// 旋转角度为90度
			} else if (y == 0 && x < 0) {
				canvas.rotate(270) ;	// 旋转角度为270度
			} else {	// 根据x和y的值计算旋转角度，而这个角度就是依靠tan()值来计算
				if(y >= 0) {
					canvas.rotate((float) Math.tanh(x / y) * 90);
				} else {
					canvas.rotate(180 + (float) Math.tanh(x / y) * 90);
				}
			}
		}
		canvas.drawBitmap(this.comp, -this.comp.getWidth() / 2,
				-this.comp.getHeight() / 2, p);
	}

}
