package org.lxh.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class PopupMenuBodyAdapter extends BaseAdapter {
	private ImageView[] menuImg = null ;

	public PopupMenuBodyAdapter(Context context, int [] picIds) {
		this.menuImg = new ImageView[picIds.length];
		for (int x = 0; x < this.menuImg.length; x++) {
			this.menuImg[x] = new ImageView(context) ;
			this.menuImg[x].setImageResource(picIds[x]) ;
		}
	}
	@Override
	public int getCount() {
		return this.menuImg.length;
	}

	@Override
	public Object getItem(int position) {
		return this.menuImg[position];
	}

	@Override
	public long getItemId(int position) {
		return this.menuImg[position].getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null ;
		if (convertView == null) {
			view = this.menuImg[position];
		} else {
			view = convertView;
		}
		return view;
	}

}
