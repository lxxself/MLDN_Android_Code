package org.lxh.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context context = null;
	private int picIds[] = null;
	public ImageAdapter(Context context, int picIds[]) {
		this.context = context;
		this.picIds = picIds;
	}

	@Override
	public int getCount() {
		return this.picIds.length;
	}

	@Override
	public Object getItem(int position) {
		return this.picIds[position];
	}

	@Override
	public long getItemId(int position) {
		return this.picIds[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView img = new ImageView(this.context);
		img.setImageResource(this.picIds[position]);
		img.setScaleType(ImageView.ScaleType.CENTER);
		return img;
	}

}
