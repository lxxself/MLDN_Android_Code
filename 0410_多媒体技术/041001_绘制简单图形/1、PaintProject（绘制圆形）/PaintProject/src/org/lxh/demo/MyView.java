package org.lxh.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE) ;	// 白色
		Paint paint = new Paint() ;
		paint.setColor(Color.BLUE) ;	// 设置图形的底色
		canvas.drawCircle(30, 50, 25, paint) ;
	}
	
}
