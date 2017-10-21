package com.example.aidlclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReciver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {

		if(arg1.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
		{
		abortBroadcast();
		
		Bundle b=arg1.getExtras();
		if(b!=null)
		{
		Object [] pdus=(Object[]) b.get("pdus");
		SmsMessage []mes=new SmsMessage[pdus.length];
		for(int i=0;i<pdus.length;i++)
		{
			mes[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);
		}
		StringBuilder sb=new StringBuilder();
		for(SmsMessage s:mes)
		{
			sb.append("发件人：");
			sb.append(s.getDisplayOriginatingAddress());
			sb.append("内容：");
			sb.append(s.getDisplayMessageBody());
		}
		Intent in=new Intent();
		in.putExtra("sms", sb.toString());
		in.setClass(arg0, SmsActivity.class);
		arg0.startActivity(in);
		}
		}
	}

}
