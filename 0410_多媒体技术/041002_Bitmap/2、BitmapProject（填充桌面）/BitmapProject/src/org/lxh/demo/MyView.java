package org.lxh.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class MyView extends View {

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		DisplayMetrics dm = super.getResources().getDisplayMetrics() ;
		int screenWidth = dm.widthPixels ;
		int screenHeight = dm.heightPixels ;
		Bitmap bitmap = BitmapFactory.decodeResource(super.getResources(),
				R.drawable.android_mldn);	// 找到图片的Bitmap对象
		bitmap = Bitmap.createScaledBitmap(bitmap, screenWidth, screenHeight, true);
		Paint paint = new Paint() ;
		paint.setAntiAlias(true); // 消除锯齿
		canvas.drawBitmap(bitmap, 0, 0, paint) ; 
	}
}
