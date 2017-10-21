package com.example.aidlclient;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class PicActivity extends Activity{

	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.picactivity);
		
		System.out.println("picactivity oncreate");
		
		lv=(ListView)findViewById(R.id.piclist);
		
		List<String> imgurls=java.util.Arrays.asList(Images.img1);
		for(String s:imgurls)
		{
			System.out.println(s);
		}
		ImageAdpter ia=new ImageAdpter(imgurls, getApplicationContext());
		//VolleyAdpter va=new VolleyAdpter(imgurls,this);
		lv.setAdapter(ia);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("picactivity onstart");
	}
	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("picactivity onstop");
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("picactivity onrestart");
	}
@Override
protected void onResume() {
	super.onResume();
	System.out.println("picactivity onresume");
}

@Override
protected void onDestroy() {
	super.onDestroy();
	System.out.println("picactivity ondestroy");
}
}


