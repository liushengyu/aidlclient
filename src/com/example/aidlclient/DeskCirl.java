package com.example.aidlclient;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViews.RemoteView;

public class DeskCirl extends AppWidgetProvider {

	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		RemoteViews rv=new RemoteViews(context.getPackageName(),R.layout.cirview);
		ComponentName cn=new ComponentName(context,DeskCirl.class);
		appWidgetManager.updateAppWidget(cn, rv);
	}
}
