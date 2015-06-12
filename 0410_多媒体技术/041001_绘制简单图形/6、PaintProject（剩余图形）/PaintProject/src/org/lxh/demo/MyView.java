package org.lxh.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
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
		paint.setColor(Color.BLACK) ;
		canvas.drawRect(80,20,160,80, paint) ;
		
		Rect rect = new Rect() ;	// 定义矩形
		rect.set(180, 20, 300, 80) ;
		paint.setStyle(Style.STROKE) ;
		canvas.drawRect(rect, paint) ;
		
		paint.setColor(Color.RED) ;
		paint.setTextSize(20) ;
		canvas.drawText("北京魔乐科技软件学院（MLDN）", 10, 110, paint) ;
	
		paint.setColor(Color.BLACK) ;
		canvas.drawLine(10, 120, 300, 120, paint);
		
		RectF oval = new RectF() ;
		oval.set(10.0f, 140.0f, 110.0f, 200.0f) ;
		canvas.drawOval(oval, paint) ;
		
		oval = new RectF() ;
		oval.set(150.0f, 140.0f, 210.0f, 200.0f);
		canvas.drawArc(oval, 150.0f, 140.0f, true, paint) ;
	}
	
}
