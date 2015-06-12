package org.lxh.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyGridViewDemo extends Activity {
	private GridView myGirdView = null; // 取得组件
	private int[] picRes = new int[] { R.drawable.png_01, R.drawable.png_02,
			R.drawable.png_03, R.drawable.png_04, R.drawable.png_05,
			R.drawable.png_06, R.drawable.png_07, R.drawable.png_08,
			R.drawable.png_09, R.drawable.png_10, R.drawable.png_11,
			R.drawable.png_12, R.drawable.png_13, R.drawable.png_14,
			R.drawable.png_15, R.drawable.png_16, R.drawable.png_17,
			R.drawable.png_18, R.drawable.png_19, R.drawable.png_20,
			R.drawable.png_21, R.drawable.png_22, R.drawable.png_23,
			R.drawable.png_24 };
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.myGirdView = (GridView) super.findViewById(R.id.myGridView);
		this.myGirdView.setAdapter(new ImageAdapter(this, this.picRes));
		this.myGirdView.setOnItemClickListener(new OnItemClickListenerImpl());
	}

	private class OnItemClickListenerImpl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ImageView showImg = new ImageView(MyGridViewDemo.this);
			showImg.setScaleType(ImageView.ScaleType.CENTER); // 图片居中显示 
			showImg.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			showImg.setImageResource(MyGridViewDemo.this.picRes[position]); // 设置显示图片
			Dialog dialog = new AlertDialog.Builder(MyGridViewDemo.this)
					.setIcon(R.drawable.pic_m).setTitle("查看图片")
					.setView(showImg).setNegativeButton("关闭", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					}).create();
			dialog.show() ;
		}

	}
}