package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

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
			
		}
	}
	
}