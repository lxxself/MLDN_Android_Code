package org.lxh.demo;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MySMSDemo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		String content = "北京魔乐科技软件学院（www.mldnjava.cn），"
				+ "是一家专门从事于Java高端培训的职业培训机构，"
				+ "采用了行业汉字中最先进的教学方法，让学生可以在四个月之内挑战自身的学习极限，"
				+ "提供了同行业内最多最好的职业就业信息，为学生就业插上成功的翅膀。";// 超过了70个字
		SmsManager smsManager = SmsManager.getDefault();
		PendingIntent sentIntent = PendingIntent.getActivity(this, 0,
				super.getIntent(), PendingIntent.FLAG_UPDATE_CURRENT);
		if (content.length() > 70) { // 大于70个字，拆分
			List<String> msgs = smsManager.divideMessage(content); // 拆分
			Iterator<String> iter = msgs.iterator();
			while (iter.hasNext()) {
				String msg = iter.next();
				smsManager.sendTextMessage("13683527621", null, msg, sentIntent, null);
			}
		} else {
			smsManager.sendTextMessage("13683527621", null, content, sentIntent, null);
		}
		Toast.makeText(this, "短信发送完成", Toast.LENGTH_SHORT).show();
	}
}