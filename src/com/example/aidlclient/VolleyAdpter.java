package com.example.aidlclient;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class VolleyAdpter extends BaseAdapter {

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*class BitmapCache implements ImageCache{

		private LruCache<String, Bitmap> cache;
		
		public BitmapCache() {
			
			int size=MemUtil.getFreeMemByte(mcontext);
			
			cache=new LruCache<String, Bitmap>(size){
				@SuppressLint("NewApi")
				@Override
				protected int sizeOf(String key, Bitmap value) {
					return value.getByteCount();
				}
			};
		}
		@Override
		public Bitmap getBitmap(String arg0) {
			
			
			return cache.get(arg0);
		}

		@Override
		public void putBitmap(String arg0, Bitmap arg1) {
			cache.put(arg0, arg1);
		}
		
	}
	
	private List<String> urls;
	private Context mcontext;
	private ImageLoader il;
	public VolleyAdpter(List<String> urls,Context context) {
		this.urls=urls;
		mcontext=context;
		RequestQueue rq=Volley.newRequestQueue(mcontext);
		BitmapCache bc=new BitmapCache();
		il=new ImageLoader(rq, bc);
		
		
	}
	
	@Override
	public int getCount() {
		return urls.size();
	}
	
	@Override
	public Object getItem(int arg0) {
		return urls.get(arg0);
	}
	
	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
	
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		String url=urls.get(arg0);
		if(arg1==null)
		{
			arg1=LayoutInflater.from(mcontext.getApplicationContext()).inflate(R.layout.picitem_volley, null);
		}
		NetworkImageView niv=(NetworkImageView) arg1.findViewById(R.id.volleyimg);
		niv.setDefaultImageResId(R.drawable.default_img);
		niv.setErrorImageResId(R.drawable.default_img);
		niv.setImageUrl(url, il);
		return arg1;
	}
	*/
}
