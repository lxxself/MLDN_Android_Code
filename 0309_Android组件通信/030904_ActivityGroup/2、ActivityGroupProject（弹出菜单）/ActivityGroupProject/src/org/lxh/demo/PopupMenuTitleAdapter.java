package org.lxh.demo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopupMenuTitleAdapter extends BaseAdapter {
	private TextView menuTitle[] = null; // 文字显示组件
	private int fontColor;
	private int selectedColor;
	private int unSelectedColor;

	public PopupMenuTitleAdapter(Context context, int[] titleIds,
			int fontColor, int fontSize, int selectedColor, int unSelectedColor) {
		this.fontColor = fontColor;
		this.selectedColor = selectedColor;
		this.unSelectedColor = unSelectedColor;
		this.menuTitle = new TextView[titleIds.length];
		for (int x = 0; x < titleIds.length; x++) {
			this.menuTitle[x] = new TextView(context);
			this.menuTitle[x].setText(titleIds[x]);
			this.menuTitle[x].setTextSize(fontSize);
			this.menuTitle[x].setGravity(Gravity.CENTER);
			this.menuTitle[x].setPadding(10, 10, 10, 10);
		}
	}

	@Override
	public int getCount() {
		return this.menuTitle.length;
	}

	@Override
	public Object getItem(int position) {
		return this.menuTitle[position];
	}

	@Override
	public long getItemId(int position) {
		return this.menuTitle[position].getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			view = this.menuTitle[position];
		} else {
			view = convertView;
		}
		return view;
	}

	public void setFocus(int index) {
		for (int x = 0; x < this.menuTitle.length; x++) {
			if (x != index) {
				this.menuTitle[x].setBackgroundDrawable(new ColorDrawable(
						this.unSelectedColor));
				this.menuTitle[x].setTextColor(fontColor);
			}
		}
		this.menuTitle[index].setBackgroundColor(0x00);
		this.menuTitle[index].setTextColor(this.selectedColor);
	}
}
