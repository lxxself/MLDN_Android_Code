package org.lxh.demo;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

public class MyIntentCaseDemo extends Activity {
	private static final int PICK_CONTACT_SUBACTIVITY = 1 ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		Uri uri = Uri.parse("content://contacts/people") ;
		Intent intent = new Intent(Intent.ACTION_PICK, uri);
		super.startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY) ;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		case PICK_CONTACT_SUBACTIVITY:
			Uri ret = data.getData(); // 接收返回的数据
			String phoneSelection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
					+ "=?"; // 设置查询条件
			String[] phoneSelectionArgs = { String.valueOf(ContentUris
					.parseId(ret)) }; // 返回的ID
			Cursor c = super.managedQuery(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					phoneSelection, phoneSelectionArgs, null);
			StringBuffer buf = new StringBuffer();
			buf.append("电话号码是：");
			for (c.moveToFirst() ; !c.isAfterLast() ; c.moveToNext()) {
				buf.append(
						c.getString(c
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)))
						.append("、");
			}
			Toast.makeText(this, buf, Toast.LENGTH_LONG).show() ;
		}
	}
	
}