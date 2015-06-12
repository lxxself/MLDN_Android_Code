package org.lxh.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	private Bitmap bitmap = null ;
	private Matrix matrix = new Matrix() ;
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.bitmap = BitmapFactory.decodeResource(super.getResources(),
				R.drawable.android_mldn);
		float cosValue = (float) Math.cos(-Math.PI / 3);
		float sinValue = (float) Math.sin(-Math.PI / 3);
		this.matrix.setValues(new float[] { cosValue, -sinValue, 100, sinValue,
				cosValue, 200, 0, 0, 2 });
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(this.bitmap, this.matrix, null) ;
	}
	
}
