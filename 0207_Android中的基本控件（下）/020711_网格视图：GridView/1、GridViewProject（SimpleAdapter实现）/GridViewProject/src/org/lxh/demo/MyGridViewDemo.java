package org.lxh.demo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.SimpleAdapter;

public class MyGridViewDemo extends Activity {
	private GridView myGirdView = null; // 取得组件
	private SimpleAdapter simpleAdapter = null;
	private List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.initAdapter(); // 实例化SimpleAdapter类对象
		this.myGirdView = (GridView) super.findViewById(R.id.myGridView);
		this.myGirdView.setAdapter(this.simpleAdapter);
		this.myGirdView.setOnItemClickListener(new OnItemClickListenerImpl());
	}

	private class OnItemClickListenerImpl implements OnItemClickListener {

		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ImageView showImg = new ImageView(MyGridViewDemo.this);
			showImg.setScaleType(ImageView.ScaleType.CENTER); // 图片居中显示 
			showImg.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			Map<String, Integer> map = (Map<String, Integer>) parent
					.getAdapter().getItem(position);
			showImg.setImageResource(map.get("img")); // 设置显示图片
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

	private void initAdapter() {
		Field[] fields = R.drawable.class.getDeclaredFields();
		for (int x = 0; x < fields.length; x++) {
			if (fields[x].getName().startsWith("png_")) {
				Map<String, Integer> map = new HashMap<String, Integer>();
				try {
					map.put("img", fields[x].getInt(R.drawable.class));
				} catch (Exception e) {
				}
				this.list.add(map);
			}
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,
				R.layout.grid_layout, new String[] { "img" },
				new int[] { R.id.img });
	}
}