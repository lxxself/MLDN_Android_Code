package org.lxh.demo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context context = null;
	private List<Integer> picRes = new ArrayList<Integer>() ;
	public ImageAdapter(Context context) {
		this.context = context;
		this.initPic();
	}

	@Override
	public int getCount() {
		return this.picRes.size();
	}

	@Override
	public Object getItem(int position) {
		return this.picRes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return this.picRes.get(position).intValue();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView img = new ImageView(this.context);
		img.setBackgroundColor(0xFF000000) ;
		img.setImageResource(this.picRes.get(position));
		img.setScaleType(ImageView.ScaleType.CENTER);
		img.setLayoutParams(new GridView.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		img.setPadding(3, 3, 3, 3);
		return img;
	}
	private void initPic(){	// 利用反射加载所有的图片
		Field [] fields = R.drawable.class.getDeclaredFields() ;
		for (int x = 0; x < fields.length; x++) {
			if(fields[x].getName().startsWith("png_")){
				try {
					this.picRes.add(fields[x].getInt(R.drawable.class)) ;
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
