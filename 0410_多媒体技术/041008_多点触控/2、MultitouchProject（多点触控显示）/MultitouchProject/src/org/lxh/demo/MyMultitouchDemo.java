package org.lxh.demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.widget.ImageView;

public class MyMultitouchDemo extends Activity {
	private static final int SCALEBASIC = 3 ;// 调整的比率
	private int imageX = 0 ;	// 计算图片的X轴
	private int imageY = 0 ;	// 计算图片的Y轴
	private SurfaceHolder holder = null ;
	private int screenWidth = 0 ;
	private int screenHeight = 0 ;
	private int imageWidth = 0 ;
	private int imageHeight = 0 ;
	private Bitmap bitmap = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
		this.screenWidth = super.getWindowManager().getDefaultDisplay().getWidth() ;
		this.screenHeight = super.getWindowManager().getDefaultDisplay().getHeight() ;
		this.bitmap = BitmapFactory.decodeResource(super.getResources(),
				R.drawable.android_book);
		this.imageWidth = this.bitmap.getWidth() ;
		this.imageHeight = this.bitmap.getHeight() ;
		this.imageX = (this.screenWidth - this.imageWidth) / 2 ;
		this.imageY = (this.screenHeight - this.imageHeight) / 2 ;
		super.setContentView(new MySurfaceView(this));
	}

	private class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
		
		public MySurfaceView(Context context) {
			super(context);
			MyMultitouchDemo.this.holder = super.getHolder() ;
			MyMultitouchDemo.this.holder.addCallback(this) ;
			super.setFocusable(true) ;	// 获得焦点，进行触摸事件
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			MyMultitouchDemo.this.setImage(1.0f, 350, 500);
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			
		}
	}
	
	private void setImage(float scale, int width, int height) {	// 改变之后修改图片
		Canvas canvas = MyMultitouchDemo.this.holder.lockCanvas() ;	// 获取画布
		Paint paint = new Paint() ;
		canvas.drawRect(0, 0, MyMultitouchDemo.this.screenWidth,
				MyMultitouchDemo.this.screenHeight, paint);
		Matrix matrix = new Matrix() ;
		matrix.postScale(scale, scale) ;	// 等量缩放
		Bitmap target = Bitmap.createBitmap(MyMultitouchDemo.this.bitmap, 0, 0,
				width, height, matrix, true);
		this.imageWidth = target.getWidth() ; 
		this.imageHeight = target.getHeight() ;
		this.imageX = (this.screenWidth - this.imageWidth) / 2 ;
		this.imageY = (this.screenHeight - this.imageHeight) / 2 ;
		canvas.translate(this.imageX, this.imageY) ;	// 平移到指定的位置
		canvas.drawBitmap(this.bitmap, matrix, paint) ;
		MyMultitouchDemo.this.holder.unlockCanvasAndPost(canvas) ;// 解锁，并提交图象
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int pointCount = event.getPointerCount() ;
		if (pointCount == 2) {
			float pointA = event.getY(0) ;
			float pointB = event.getY(1) ;
			if(pointA < pointB) {
				float temp = pointA ;
				pointA = pointB ;
				pointB = temp ;
			}
			if(!(event.getAction() == MotionEvent.ACTION_UP)) {
				float scale = this.getScale(pointA, pointB) / SCALEBASIC;
				MyMultitouchDemo.this.setImage(scale, 350, 500) ;
			}
		}
		return super.onTouchEvent(event);
	}
	private float getScale(float pointA,float pointB) {
		float scale = pointA / pointB ;
		return scale ;
	}
}