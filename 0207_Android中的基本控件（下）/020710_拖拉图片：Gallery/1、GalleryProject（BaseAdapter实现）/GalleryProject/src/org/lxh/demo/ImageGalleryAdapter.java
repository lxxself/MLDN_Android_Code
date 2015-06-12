package org.lxh.demo;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageGalleryAdapter extends BaseAdapter {
	private Context context = null ;
	// 里面的所有方法表示的是可以根据指定的显示图片的数量，进行每个图片的处理
	private int[] imgRes = new int[] { R.drawable.ispic_a,
			R.drawable.ispic_b, R.drawable.ispic_c, R.drawable.ispic_d,
			R.drawable.ispic_e };	// 这些是所要显示的图片的资源
	public ImageGalleryAdapter(Context context) {
		this.context = context ;
	}
	@Override
	public int getCount() {	// 取得要显示的内容的数量
		return this.imgRes.length ;	// 资源的数量
	}

	@Override
	public Object getItem(int position) {
		return this.imgRes[position];
	}

	@Override
	public long getItemId(int position) {	// 取得项的ID
		return this.imgRes[position]; 
	}
	// 将资源设置到一个组件之中，很明显这个组件就是ImageView组件
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView img = new ImageView(this.context) ;
		img.setBackgroundColor(0xFFFFFFFF) ;
		img.setImageResource(this.imgRes[position]) ;	// 将指定的资源设置到ImageView中
		img.setScaleType(ImageView.ScaleType.CENTER) ;	// 居中显示
		img.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		return img;
	}

}
