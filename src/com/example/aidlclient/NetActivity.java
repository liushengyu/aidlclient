package com.example.aidlclient;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class NetActivity extends Activity {

	private ImageView img;
	private Button download;
	private ProgressBar gress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.netactivity);
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				
				switch(msg.what)
				{
				case 1:
					gress.setProgress(msg.arg1);
					break;
				case 2:
//					Bitmap b=(Bitmap) msg.obj;
					Bitmap b=BitmapFactory.decodeFile(getFilesDir()+"/pic.png");
					img.setImageBitmap(b);
					break;
				}
				
			}
		};
		img=(ImageView)findViewById(R.id.netimg);
		download=(Button)findViewById(R.id.download);
		gress=(ProgressBar)findViewById(R.id.progressbar);
		download.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				new Thread(){
					public void run() {
						
						final DownloadUtil du=new DownloadUtil(4, "http://10.0.2.2:8800/pics/pic1.png", getFilesDir()+"/pic.png");
						try {
							du.download();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						new Timer().schedule(new TimerTask() {
							
							@Override
							public void run() {
								double rate=du.getRate();
								if(rate<1)
								{
									Message msg=Message.obtain();
									msg.arg1=(int) (rate*100);
									handler.sendMessage(msg);
								}
								else
								{
									handler.sendEmptyMessage(2);
									cancel();
								}
									
							}
						}, 0, 100);
					};
				}.start();
			}
		});
		/*	new Thread(){
				
				public void run() {
					try {
					
					URL url=new URL("http://10.0.2.2:8800/pics/pic1.png");
					InputStream is=url.openStream();
					Bitmap b=BitmapFactory.decodeStream(is);
					Message msg =Message.obtain();
					msg.what=1;
					msg.obj=b;
					handler.sendMessage(msg);
					is.close();
					
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				};
			}.start();*/
	}
	
}
