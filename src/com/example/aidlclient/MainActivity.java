 package com.example.aidlclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newleaning.ICat;
import com.example.newleaning.Student;
import com.example.newleaning.StudentService;

public class MainActivity extends Activity {

	private StudentService ss;
	private ICat cb;
	private TextView txt;
	private Button bind;
	private Button unbind;
	private Button get;
	private Button getrecord;
	private Button up;
	private Button start;
	private Button next;
	private Button record;
	private Button stoprecord;
	private Button alert;
	private ServiceConnection sc=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			
			
		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			System.out.println("connected");
//			cb=ICat.Stub.asInterface(arg1);			
			ss=StudentService.Stub.asInterface(arg1);
		}
	};
	String toHex(byte[] bytes)
	{
		StringBuilder result=new StringBuilder();
		for(int i=0;i<bytes.length;i++)
		{
			String hes=Integer.toHexString(bytes[i]&0xff);
			result.append(hes);
		}
		return result.toString();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("mainactivity oncreate");
		next=(Button)findViewById(R.id.next);
		bind=(Button)findViewById(R.id.bind);
		unbind=(Button)findViewById(R.id.unbind);
		get=(Button)findViewById(R.id.get);
		txt=(TextView)findViewById(R.id.txt);
		getrecord=(Button)findViewById(R.id.getrecord);
		up=(Button )findViewById(R.id.up);
		start=(Button )findViewById(R.id.start);
		record=(Button )findViewById(R.id.recorder);
		stoprecord=(Button)findViewById(R.id.stoprecord);
		
		alert=(Button)findViewById(R.id.alert);
		
		alert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				/*new AlertDialog.Builder(MainActivity.this).setTitle("title")
				.setMessage("message").setPositiveButton("pos text", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
					}
				}).setNegativeButton("neg text", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
					}
				}).show(); */
				Intent in=new Intent(MainActivity.this,PicActivity.class);
				startActivity(in);
			}
			
		});
		
		
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update("lsadkf/asldfj".getBytes());
			Toast.makeText(this, toHex(md.digest()), Toast.LENGTH_SHORT).show()
			;
		} catch (NoSuchAlgorithmException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		
		
		
		try {
			final MediaRecorder mr=new MediaRecorder();
			mr.setAudioSource(MediaRecorder.AudioSource.MIC);
			mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			String path1;
			path1 = Environment.getExternalStorageDirectory().getCanonicalPath()+"/testrecord.amr";
			mr.setOutputFile(path1);
			
			record.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					try {
						mr.prepare();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					mr.start();
				}
			});
			stoprecord.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					mr.stop();
					mr.release();
				}
			});
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent in=new Intent(MainActivity.this,NetActivity.class);
				startActivity(in);
			}
		});
		
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Toast.makeText(getApplicationContext(), "sda",500).show();
				MediaPlayer mp=new MediaPlayer();
				try {
					mp.setDataSource(Environment.getExternalStorageDirectory().getCanonicalPath()+"/testrecord.amr");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					mp.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mp.start();
//				MediaPlayer mp=MediaPlayer.create(MainActivity.this, R.raw.qiyou);
//				mp.start();
//				Intent in=new Intent();
//				in.putExtra("sms", "sdfasdfasd");
//				in.setClass(MainActivity.this, SmsActivity.class);
//				startActivity(in);
			}
		});
		up.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AudioManager am=(AudioManager) getSystemService(AUDIO_SERVICE);
				am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
			}
		});
				
		String path="";
		try {
			path = getFilesDir().getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		TelephonyManager tm=(TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		
		PhoneStateListener lis=new PhoneStateListener(){
			@Override
			public void onCallStateChanged(int state, String incomingNumber) {
				
				switch(state)
				{
				case TelephonyManager.CALL_STATE_IDLE:
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					break;
				case TelephonyManager.CALL_STATE_RINGING:
					
					try {
						OutputStream os=openFileOutput("phonerecord.txt", MODE_APPEND);
						PrintWriter pw=new PrintWriter(os);
						pw.println(""+new Date()+" À´µç£º"+incomingNumber);
						pw.close();
//						os.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;
				}
				super.onCallStateChanged(state, incomingNumber);
			}
		};
		tm.listen(lis, PhoneStateListener.LISTEN_CALL_STATE);
		
		File f=new File(path+"/phonerecord.txt");
		getrecord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				try {
					InputStream is=openFileInput("phonerecord.txt");
					StringBuilder sb=new StringBuilder();
					int len=0;
					byte buffer[]=new byte[512];
					while((len=is.read(buffer))!=-1)
					{
						sb.append(new String(buffer,0,len));
					}
					txt.setText(sb.toString());
					is.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				String path1=getFilesDir().getAbsolutePath();
//				System.out.println("canno path:"+path+"  ab path:"+path1);
			}
		});
		Intent sin=new Intent();
		sin.setAction("com.example.newleaning.aidltest");
		bindService(sin, sc, Service.BIND_AUTO_CREATE);
		bind.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			}
		});
		unbind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				unbindService(sc);
			}
		});
		get.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				System.out.println("in click");
				try {
					if(cb==null)
					{
						System.out.println("cb is null");
					}
					Student s=ss.getStudentById("³¤Èø");
//					Toast.makeText(getApplicationContext(), "color:"+cb.getColor()+"  weight:"+cb.getWeight(), Toast.LENGTH_SHORT).show();
					Toast.makeText(getApplicationContext(), "name:"+s.getName()+"  age:"+s.getAge(), Toast.LENGTH_SHORT).show();
				} catch (RemoteException e) {
					System.out.println("remote error");
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("mainactivity ondestroy");		
		unbindService(sc);
	}
	
	
	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("mainactivity onstart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("mainactivity onstop");
	}
	@Override
	protected void onResume() {
		super.onResume();
		
		System.out.println("mainactivity onresume");
		
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("mainactivity onrestart");
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		System.out.println("mainactivity onRestoreInstanceState");
	}
@Override
protected void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	System.out.println("mainactivity onSaveInstanceState");
}
}
