package com.example.aidlclient;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;

public class MemUtil {

	public static int getFreeMemByte(Context context)
	{
		ActivityManager am=(ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo mi=new MemoryInfo();
		am.getMemoryInfo(mi);
		int size=(int) (mi.availMem/8);
		return size;
	}
	
}
