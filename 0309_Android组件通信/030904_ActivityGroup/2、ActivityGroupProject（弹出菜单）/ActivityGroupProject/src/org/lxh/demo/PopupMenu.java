package org.lxh.demo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class PopupMenu extends PopupWindow {
	private GridView popTitle ;	// 表示标题
	private GridView popBody ;	// 标题主体
	private PopupMenuTitleAdapter titleAdapter = null ;
	private LinearLayout layout = null ;

	public PopupMenu(Context context, int titleIds[],int backgroundColor,
			OnItemClickListener titleCallback, OnItemClickListener bodyCallback) {
		super(context); // 还是需要创建弹出的组件
		this.titleAdapter = new PopupMenuTitleAdapter(context, titleIds,
				0xFF222222, 16, Color.LTGRAY, Color.WHITE);
		this.layout = new LinearLayout(context) ;
		this.layout.setOrientation(LinearLayout.VERTICAL) ;
		this.popTitle = new GridView(context);
		this.popTitle.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT)); 
		this.popTitle.setNumColumns(titleIds.length) ;
		this.popTitle.setHorizontalSpacing(1) ;
		this.popTitle.setVerticalSpacing(1) ;
		this.popTitle.setStretchMode(GridView.STRETCH_COLUMN_WIDTH); // 拉申列宽
		this.popTitle.setAdapter(this.titleAdapter) ;
		this.popTitle.setOnItemClickListener(titleCallback) ;
		
		this.popBody = new GridView(context) ;
		this.popBody.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		this.popBody.setNumColumns(5) ;
		this.popBody.setHorizontalSpacing(1) ;
		this.popBody.setVerticalSpacing(1) ;
		this.popBody.setPadding(10, 10, 10, 10) ;
		this.popBody.setGravity(Gravity.CENTER) ;
		this.popBody.setStretchMode(GridView.STRETCH_COLUMN_WIDTH); // 拉申列宽
		this.popBody.setOnItemClickListener(bodyCallback) ;
		
		this.layout.addView(this.popTitle) ;
		this.layout.addView(this.popBody) ;
		super.setContentView(this.layout) ;
		super.setWidth(LayoutParams.FILL_PARENT) ;
		super.setHeight(LayoutParams.WRAP_CONTENT) ;
		super.setBackgroundDrawable(new ColorDrawable(backgroundColor)) ;
		super.setFocusable(true) ;	// 允许获得焦点
	}
	
	public void setPopupMenuBodyAdapter(PopupMenuBodyAdapter adapter) {
		this.popBody.setAdapter(adapter) ;
	}
	
	public void setPopTitleSelected(int postion) {
		this.popTitle.setSelection(postion) ;
		this.titleAdapter.setFocus(postion) ;
	}
	
	public void setPopBodySelected(int position,int selectedColor) {	// 设置选中后的颜色
		int count = this.popBody.getChildCount() ;
		for (int x = 0; x < count; x++) {
			if (x != position) {
				ImageView img = (ImageView) this.popBody.getChildAt(x) ;
				img.setBackgroundColor(Color.TRANSPARENT) ;
			}
		}
		ImageView img = (ImageView) this.popBody.getChildAt(position) ;
		img.setBackgroundColor(selectedColor) ;
	}
}
