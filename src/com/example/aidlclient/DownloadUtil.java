package com.example.aidlclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadUtil {

	private DownloadThread threads[];
	private int threadcount;
	private int partsize;
	private String url;
	private String filepath;
	
	public DownloadUtil(int threadcount,String url,String filepath) {
		this.threads = new DownloadThread[threadcount];
		this.threadcount = threadcount;
		this.url = url;
		this.filepath=filepath;
	}

	public void download() throws IOException
	{
		URL u=new URL(url);
		HttpURLConnection con=(HttpURLConnection) u.openConnection();
		con.setConnectTimeout(5*1000);
		con.setRequestMethod("GET");
		int filesize=con.getContentLength();
		System.out.println("filesize:"+filesize);
		System.out.println("filepath:"+filepath);
		
		con.connect();
		partsize=filesize/threadcount+1;
		
		RandomAccessFile file=new RandomAccessFile(filepath, "rw");
		file.setLength(filesize);
		file.close();
		for(int i=0;i<threadcount;i++)
		{
			int currentpos=i*partsize;
			System.out.println("thread"+i+" :"+currentpos);
			RandomAccessFile curf=new RandomAccessFile(filepath, "wr");
			curf.seek(currentpos);
			threads[i]=new DownloadThread(file,currentpos);
			threads[i].start();
		}
	}
	
	public double getRate()
	{
		double rate=0.0;
		int sum=0;
		for(int i=0;i<threadcount;i++)
		{
			sum+=threads[i].length;
		}
		rate=sum/(partsize*threadcount);
		return rate;
	}	
	class DownloadThread extends Thread
	{
		private RandomAccessFile file;
		private int pos;
		public int length=0;
		public DownloadThread(RandomAccessFile file, int pos) {
			this.file = file;
			this.pos = pos;
		}
		@Override
		public void run() {
			
			try {
				URL u=new URL(url);
				InputStream is=u.openStream();
				is.skip(this.pos);
				int len=0;
				byte[] buffer=new byte[1024];
				while(length<partsize&&(len=is.read(buffer))!=-1)
				{
					file.write(buffer, 0, len);
					length+=len;
				}
				file.close();
				is.close();
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
