package com.codepath.example.servicesnotificationsdemo.services.ipc;

import com.codepath.example.servicesnotificationsdemo.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.SeekBar;

public class MyIpcTest extends Activity {

	public static final int PLAY = 1;
	public static final int PAUSE = 2;
	private String Tag=MyIpcTest.class.getSimpleName();
	SeekBar musicSeekBar;

	playService iPlayer;
	boolean isPlaying = false;
	boolean isLoop = false;	
	Intent intent= null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ipc_player);

		musicSeekBar = (SeekBar) findViewById(R.id.musicSeekBar);
		Log.i(Tag+" onStop()", "now start activity,pid:"+android.os.Process.myPid());
		intent= new Intent(this, MPlayService.class);
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
		/*intent= new Intent(this, MPlayService.class);
		startService(intent);*/
//		handler.post(updateThread);
	}
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
		};
	};

	private Runnable updateThread = new Runnable() {
		@Override
		public void run() {
//			if (iPlayer != null) {
//				try {
//					musicSeekBar.setMax(iPlayer.getDuration());
//					musicSeekBar.setProgress(iPlayer.getCurrentPosition());
//				} catch (RemoteException e) {
//					e.printStackTrace();
//				}
//			}
		//	System.out.println(mservice.getCurr());
			handler.postDelayed(updateThread, 1000);
		}
	};
	private ServiceConnection conn = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {

			iPlayer= playService.Stub.asInterface(service);
			try {
				
				iPlayer.play();
			} catch (RemoteException e) {

				e.printStackTrace();
			}
//			handler.post(updateThread);
//			mservice.sendPerson(new Person(1, "1"));
//			mservice.my_start();
			
//			MyBinder b= (MyBinder) service;
//			((MPlayService) b.getService()).my_start();
			
		}
		public void onServiceDisconnected(ComponentName className) {
			
			System.out.println("onServiceDisconnected");
		};
	};
	
	protected void onStop() {
		
		super.onStop();
		Log.i(Tag+" onStop()", "now stop activity");
//		stopService(intent);
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(Tag+" onDestroy()", "now destroy activity");
		unbindService(conn);
		//stopService(intent);
	}
}