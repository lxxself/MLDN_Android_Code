package org.lxh.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MyCameraDemo extends Activity {
	private SurfaceView surface = null ;
	private Button but = null ;
	private SurfaceHolder holder = null ;
	private Camera cam = null ;
	private boolean previewRunning =  true ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.but = (Button) super.findViewById(R.id.but) ;
		this.surface = (SurfaceView) super.findViewById(R.id.surface) ;
		
		this.holder = this.surface.getHolder() ;
		this.holder.addCallback(new MySurfaceViewCallback()) ;
		this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS) ;
		this.holder.setFixedSize(500, 350);
		
		this.but.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			if(MyCameraDemo.this.cam != null) {
				MyCameraDemo.this.cam.autoFocus(new AutoFocusCallbackImpl()) ;
			}
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
			param.set("jpen-quality", 80) ;
			MyCameraDemo.this.cam.setParameters(param) ;
			try {
				MyCameraDemo.this.cam.setPreviewDisplay(MyCameraDemo.this.holder) ;
			} catch (IOException e) {
			}
			MyCameraDemo.this.cam.startPreview() ;	// 进行预览
			MyCameraDemo.this.previewRunning = true ;	// 已经开始预览
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			if(MyCameraDemo.this.cam != null) {
				if(MyCameraDemo.this.previewRunning) {
					MyCameraDemo.this.cam.stopPreview() ;	// 停止预览
					MyCameraDemo.this.previewRunning = false ;
				}
				MyCameraDemo.this.cam.release() ;
			}
		}
		
	}
	
	private class AutoFocusCallbackImpl implements AutoFocusCallback {

		@Override
		public void onAutoFocus(boolean success, Camera camera) {
			if(success) {	// 成功
				MyCameraDemo.this.cam.takePicture(sc, pc, jpgcall) ;
			}
		}
		
	}
	
	private PictureCallback jpgcall = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {	// 保存图片的操作
			Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
			String fileName = Environment.getExternalStorageDirectory()
					.toString()
					+ File.separator
					+ "mldnphoto"
					+ File.separator
					+ "MLDN_" + System.currentTimeMillis() + ".jpg";
			File file = new File(fileName) ;
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs() ;	// 创建文件夹
			}
			try {
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file)) ;
				bmp.compress(Bitmap.CompressFormat.JPEG, 80, bos) ; // 向缓冲区之中压缩图片
				bos.flush() ;
				bos.close() ;
				Toast.makeText(MyCameraDemo.this,
						"拍照成功，照片已保存在" + fileName + "文件之中！", Toast.LENGTH_SHORT)
						.show();
			} catch (Exception e) {
				Toast.makeText(MyCameraDemo.this,
						"拍照失败！", Toast.LENGTH_SHORT)
						.show();
			}
			MyCameraDemo.this.cam.stopPreview() ;
			MyCameraDemo.this.cam.startPreview() ;
		}
		
	} ;
	
	private ShutterCallback sc = new ShutterCallback(){
		@Override
		public void onShutter() {
			// 按下快门之后进行的操作
		}
	} ;
	private PictureCallback pc = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			
		}
		
	} ;
}