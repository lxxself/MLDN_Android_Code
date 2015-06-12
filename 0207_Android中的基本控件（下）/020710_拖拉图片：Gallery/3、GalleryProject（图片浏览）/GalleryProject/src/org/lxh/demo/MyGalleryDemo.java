package org.lxh.demo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher.ViewFactory;

public class MyGalleryDemo extends Activity {
	private Gallery gallery = null;
	private SimpleAdapter simpleAdapter = null ;
	private List<Map<String,Integer>> list = new ArrayList<Map<String,Integer>>() ;
	private ImageSwitcher myImageSwitcher = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.initAdapter() ;
		this.gallery = (Gallery) super.findViewById(R.id.myGallery);
		this.myImageSwitcher = (ImageSwitcher) super.findViewById(R.id.myImageSwitcher) ;
		this.gallery.setAdapter(this.simpleAdapter) ;
		this.myImageSwitcher.setFactory(new ViewFactoryImpl()) ;
		this.gallery.setOnItemClickListener(new OnItemClickListenerImpl()) ;
	}
	private void initAdapter(){
		Field [] fields = R.drawable.class.getDeclaredFields() ;	// 取得全部的属性
		for (int x = 0; x < fields.length; x++) {
			if(fields[x].getName().startsWith("ispic_")){	// 我们所需要的图片
				Map<String,Integer> map = new HashMap<String,Integer>() ;
				try{
					map.put("img", fields[x].getInt(R.drawable.class)) ;	// 必须定义好名称是img
				} catch(Exception e){
				}
				this.list.add(map) ;
			}
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,
				R.layout.grid_layout, new String[] { "img" },
				new int[] { R.id.img });
	}
	private class OnItemClickListenerImpl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Map<String, Integer> map = (Map<String, Integer>) parent
					.getAdapter().getItem(position); 
			MyGalleryDemo.this.myImageSwitcher.setImageResource(map.get("img")) ;
		}
	}
	private class ViewFactoryImpl implements ViewFactory{

		@Override
		public View makeView() {
			ImageView img = new ImageView(MyGalleryDemo.this) ; 
			img.setBackgroundColor(0xFFFFFFFF) ;
			img.setScaleType(ImageView.ScaleType.CENTER) ;
			img.setLayoutParams(new ImageSwitcher.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			return img;
		}
		
	}
}