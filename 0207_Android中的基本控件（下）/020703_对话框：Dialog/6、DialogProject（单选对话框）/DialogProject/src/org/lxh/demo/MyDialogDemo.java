package org.lxh.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyDialogDemo extends Activity {
	private Button mybut = null ;	// 定义按钮
	private TextView mych = null ;	// 定义文本
	private TextView mytext = null ;	// 定义文本
	private String fruitData [] = new String[] { "苹果", "西瓜", "水蜜桃" };
	private String fruitDesc [] = new String[] {
			"苹果，植物类水果，多次花果，具有丰富的营养成分，有食疗、辅助治疗等功能。",
			"西瓜（学名：Citrullus Lanatus，英文：Watermelon），属葫芦科，原产于非洲。",
			"水蜜桃，在植物分类学上属于蔷薇科，梅属，桃亚属，为落叶小乔木。"} ;
	private int chNum = 0 ;	// 保存选项
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main); // 调用布局管理器
		this.mybut = (Button) super.findViewById(R.id.mybut) ;	// 取得按钮
		this.mych = (TextView) super.findViewById(R.id.mych) ;	// 取得文本
		this.mytext = (TextView) super.findViewById(R.id.mytext) ;	// 取得文本
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;	// 设置事件类
	}
	private class OnClickListenerImpl implements OnClickListener {
 
		@Override
		public void onClick(View view) {
			Dialog dialog = new AlertDialog.Builder(MyDialogDemo.this)
				.setIcon(R.drawable.pic_m) 
				.setTitle("请选择你喜欢吃的水果？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
									MyDialogDemo.this.mych
											.setText(MyDialogDemo.this.fruitData[MyDialogDemo.this.chNum]);	// 设置选项的名称
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).setSingleChoiceItems(MyDialogDemo.this.fruitData, 0, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
									MyDialogDemo.this.mytext
											.setText(MyDialogDemo.this.fruitDesc[which]);
									MyDialogDemo.this.chNum = which ;	// 保存选项的索引
					}
				}).create() ;
			dialog.show() ;
		}
		
	}

}