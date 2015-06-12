package org.lxh.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Bitmap bitmap = BitmapFactory.decodeResource(super.getResources(),
				R.drawable.android_mldn);	// 找到图片的Bitmap对象
		Paint paint = new Paint() ;
		paint.setAntiAlias(true); // 消除锯齿
		canvas.drawBitmap(bitmap, new Rect(100,100,200,200), new Rect(100, 100, 200, 200), paint); 
	}
}
