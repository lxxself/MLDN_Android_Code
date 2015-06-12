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
		this.matrix.preScale(0.5f, 0.5f, 50, 100);
		this.matrix.preRotate(-60, 50, 100);
		this.matrix.preTranslate(50, 100) ;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(this.bitmap, this.matrix, null) ;
	}
	
}
