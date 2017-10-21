package com.example.aidlclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class VedioActivity extends Activity {

	private VideoView videoview;
	private MediaController mc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.vedioactivity);
		videoview=(VideoView)findViewById(R.id.video);
		mc=new MediaController(this);
		AssetManager am=getAssets();
		try {
			InputStream in=am.open("deskball.mp4");
			String path=Environment.getExternalStorageDirectory().getCanonicalPath()+"/deskball.mp4";
			System.out.println("path:"+path);
			File file=new File(path);
			if(!file.exists())
			{
				file.createNewFile();
				byte[] buffer=new byte[1024];
				OutputStream os=new FileOutputStream(file);
				int len=0;
				while((len=in.read(buffer))!=-1)
				{
					os.write(buffer,0,len);
				}
				in.close();
				os.close();
			}
			videoview.setVideoPath(path);
			videoview.setMediaController(mc);
			mc.setMediaPlayer(videoview);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
