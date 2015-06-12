package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class MyDateTimeDemo extends Activity {
	private EditText input = null;
	private DatePicker date = null;
	private TimePicker time = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.input = (EditText) super.findViewById(R.id.input);
		this.date = (DatePicker) super.findViewById(R.id.date);
		this.time = (TimePicker) super.findViewById(R.id.time);
		this.time.setIs24HourView(true); // 采用24小时制显示时间
		this.time.setOnTimeChangedListener(new OnTimeChangedListenerImpl());
		this.date.init(this.date.getYear(), this.date.getMonth(),
				this.date.getDayOfMonth(), new OnDateChangedListenerImpl());
		this.setDateTime(); // 希望一开始可以设置一些文本的内容
	}

	private class OnTimeChangedListenerImpl implements OnTimeChangedListener {

		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			MyDateTimeDemo.this.setDateTime();
		}
	}

	private class OnDateChangedListenerImpl implements OnDateChangedListener {
		public void onDateChanged(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			MyDateTimeDemo.this.setDateTime();
		}
	}

	public void setDateTime() { // 由于日期时间更改之后文本输入组件的内容也要修改
		this.input.setText(this.date.getYear() + "-"
				+ (this.date.getMonth() + 1) + "-" + this.date.getDayOfMonth()
				+ " " + this.time.getCurrentHour() + ":"
				+ this.time.getCurrentMinute()); // 修改文本的内容
	}
}