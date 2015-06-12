package org.lxh.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyOverlayImpl extends ItemizedOverlay<OverlayItem> {
	private List<OverlayItem> allOverlayItems = new ArrayList<OverlayItem>();
	private Context context = null;

	public MyOverlayImpl(Drawable defaultMarker, Context context) {
		super(boundCenter(defaultMarker));
		this.context = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return this.allOverlayItems.get(i);
	}

	@Override
	public int size() {
		return this.allOverlayItems.size();
	}

	@Override
	protected boolean onTap(int index) { // 单击标记图片之后的操作
		OverlayItem item = this.allOverlayItems.get(index); // 取得指定的点
		Dialog dialog = new AlertDialog.Builder(this.context)
				.setIcon(R.drawable.pic_m).setTitle(item.getTitle())
				.setMessage(item.getSnippet())
				.setPositiveButton("关闭", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).create();
		dialog.show();
		return true;
	}

	public void addOverlayItem(OverlayItem item) {
		this.allOverlayItems.add(item);
		super.populate();
	}
}
