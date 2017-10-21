package com.example.aidlclient;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class ImageAdpter extends BaseAdapter {

	private List<String> items; 
	private LruCache<String, Bitmap> imgcache;
	private Context mcontext;
	private ListView mlist;
	public ImageAdpter(List<String> items,Context context) {
		this.items=items;
		this.mcontext=context;
		int size=MemUtil.getFreeMemByte(mcontext);
		System.out.println("getMem "+size);
		
		imgcache=new LruCache<String, Bitmap>(size){
			@SuppressLint("NewApi")
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getByteCount();
			}
		};
		
	}
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if(mlist==null)
		mlist=(ListView) arg2;
		String url=items.get(arg0);
		viewHolder holder=null;
		if(arg1==null)
		{
			
			arg1=LayoutInflater.from(mcontext).inflate(R.layout.picitem, null);
			holder=new viewHolder();
			holder.img=(ImageView)arg1.findViewById(R.id.img);
			arg1.setTag(holder);
		}
		if(holder==null)
		{
			holder=(viewHolder) arg1.getTag();
		}
		holder.img.setTag(url);
		holder.img.setImageResource(R.drawable.default_img);
		Bitmap bitmap=imgcache.get(url);
		if(bitmap==null)
		{
			Drawable d=holder.img.getDrawable();
			if(d instanceof AsynDrawable)
			{
			AsynDrawable ad=(AsynDrawable) holder.img.getDrawable();
			if(!ad.getTask().url.equals(url))
			{
				ad.getTask().cancel(true);
			}
			
			ImageAsyTask imt=new ImageAsyTask(holder.img);
			d=new AsynDrawable(imt);
			holder.img.setImageDrawable(d);
			imt.execute(url);
			System.out.println("url:"+url);
			}else
			{
				ImageAsyTask imt=new ImageAsyTask(holder.img);
				d=new AsynDrawable(imt);
				holder.img.setImageDrawable(d);
				imt.execute(url);
			}
		}else
		{
			holder.img.setImageBitmap(bitmap);
		}
		return arg1;
	}
	
	class viewHolder
	{
		public ImageView img;
	}

	class AsynDrawable extends BitmapDrawable
	{
		private WeakReference<ImageAsyTask> taskref;
		
		public AsynDrawable(ImageAsyTask task) {
			taskref=new WeakReference<ImageAdpter.ImageAsyTask>(task);
		}
		
		public ImageAsyTask getTask()
		{
			return taskref.get();
		}
		
	}
	
	class ImageAsyTask extends AsyncTask<String, Void, Bitmap>{

		
		private ImageView img;
		private WeakReference<ImageView> viewsref;
		public String  url;
		
		public ImageAsyTask(ImageView img) {
			this.img=img;
			viewsref=new WeakReference<ImageView>(img);
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			
			if(result!=null/*&&img!=null*/)
			{
//				img=(ImageView) mlist.findViewWithTag(url);
//				if(img!=null)
//				if(((String)img.getTag()).equals(url))
//					img.setImageBitmap(result);
				//img.setImageBitmap(result);
				ImageView im=viewsref.get();
				Drawable d=im.getDrawable();
				if(d instanceof AsynDrawable)
				{
					AsynDrawable ad=(AsynDrawable)d;
					ImageAsyTask task=ad.getTask();
					if(task==this)
					{ 	
						im.setImageBitmap(result);
					}
				}
				
				
			} 
		}
		
		@Override
		protected Bitmap doInBackground(String... arg0) {
			
			String urlstr=arg0[0];
			url=urlstr;
			Bitmap bitmap=null;
			HttpURLConnection con=null;
			try {
				URL u=new URL(urlstr);
				con=(HttpURLConnection) u.openConnection();
				con.setConnectTimeout(3000);
				con.setReadTimeout(10000);
				bitmap=BitmapFactory.decodeStream(con.getInputStream());
				System.out.println("map:"+bitmap);
				imgcache.put(urlstr, bitmap);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				if(con!=null)
					con.disconnect();
			}
			
			return bitmap;
		}
		
	}
}

