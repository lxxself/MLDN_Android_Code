package org.lxh.demo;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

public class MyCameraDemo extends Activity {
	private SurfaceView surface = null ;
	private Button but = null ;
	private SurfaceHolder holder = null ;
	private Camera cam = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.but = (Button) super.findViewById(R.id.but) ;
		this.surface = (SurfaceView) super.findViewById(R.id.surface) ;
		
		this.holder = this.surface.getHolder() ;
		this.holder.addCallback(new MySurfaceViewCallback()) ;
		this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS) ;
		this.holder.setFixedSize(800, 480);
		
		this.but.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			
		}
		
	}
	
	private class MySurfaceViewCallback implements SurfaceHolder.Callback {

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			MyCameraDemo.this.cam = Camera.open(0) ;	// 取得第一个摄像头
			WindowManager manager = (WindowManager) MyCameraDemo.this
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = manager.getDefaultDisplay() ;
			Parameters param = MyCameraDemo.this.cam.getParameters() ;
			param.setPreviewSize(display.getWidth(), display.getHeight()) ;
			param.setPreviewFrameRate(5) ;	// 一秒5帧
			param.setPictureFormat(PixelFormat.JPEG) ;	// 图片形式
			param.set("jpen-quality", 85) ;
			MyCameraDemo.this.cam.setParameters(param) ;
			try {
				MyCameraDemo.this.cam.setPreviewDisplay(MyCameraDemo.this.holder) ;
			} catch (IOException e) {
			}
			MyCameraDemo.this.cam.startPreview() ;	// 进行预览
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			
		}
		
	}
}