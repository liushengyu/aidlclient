package com.example.aidlclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class SmsActivity extends Activity {

	private TextView sms;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.smsactivoty);
		
		sms=(TextView)findViewById(R.id.sms);
		String smsstr=getIntent().getStringExtra("sms");
		sms.setText(smsstr.toString());
		
	}
	
}
