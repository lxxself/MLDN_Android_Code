package org.lxh.demo;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MySQLiteDemo extends Activity {
	private Button insertBut = null ;
	private Button updateBut = null ;
	private Button deleteBut = null ;
	private SQLiteOpenHelper helper = null ;
	private MytabOperate mtab = null ;
	private static int count = 0 ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.helper = new MyDatabaseHelper(this);
		this.insertBut = (Button) super.findViewById(R.id.insertBut) ;
		this.updateBut = (Button) super.findViewById(R.id.updateBut) ;
		this.deleteBut = (Button) super.findViewById(R.id.deleteBut) ;
		this.insertBut.setOnClickListener(new InsertOnClickListenerImpl()) ;
		this.updateBut.setOnClickListener(new UpdateOnClickListenerImpl()) ;
		this.deleteBut.setOnClickListener(new DeleteOnClickListenerImpl()) ;
	}
	private class InsertOnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			MySQLiteDemo.this.mtab = new MytabOperate(
					MySQLiteDemo.this.helper.getWritableDatabase());
			MySQLiteDemo.this.mtab.insert("ÀîÐË»ª" + count++, "1979-08-12") ;
		}
		
	}
	private class UpdateOnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			MySQLiteDemo.this.mtab = new MytabOperate(
					MySQLiteDemo.this.helper.getWritableDatabase());
			MySQLiteDemo.this.mtab.update(3, "MLDN", "1981-06-27");
		}
		
	}
	private class DeleteOnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			MySQLiteDemo.this.mtab = new MytabOperate(
					MySQLiteDemo.this.helper.getWritableDatabase());
			MySQLiteDemo.this.mtab.delete(3) ;
		}
		
	}
}