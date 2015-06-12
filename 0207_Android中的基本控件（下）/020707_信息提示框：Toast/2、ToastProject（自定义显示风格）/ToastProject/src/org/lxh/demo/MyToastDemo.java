package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
public class MyToastDemo extends Activity {
	private Button but = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.but = (Button) super.findViewById(R.id.but);
		this.but.setOnClickListener(new OnClickListenerImpl()) ;
	}

	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			Toast myToast = Toast.makeText(MyToastDemo.this, "魔乐科技软件学院",
					Toast.LENGTH_LONG); 
			myToast.setGravity(Gravity.CENTER, 60, 30) ;
			LinearLayout myToastView = (LinearLayout)myToast.getView() ;	// 线性布局
			ImageView img = new ImageView(MyToastDemo.this) ;
			img.setImageResource(R.drawable.pic_mldn) ;
			myToastView.addView(img, 0);	// 放在最前面
			myToast.show() ;
		}

	}

}