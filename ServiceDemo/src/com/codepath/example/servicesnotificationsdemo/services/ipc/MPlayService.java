package com.codepath.example.servicesnotificationsdemo.services.ipc;

import com.codepath.example.servicesnotificationsdemo.R;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

public class MPlayService extends Service {

	public MediaPlayer mPlayer;
	
	private String Tag = MPlayService.class.getSimpleName();
	playService.Stub stub= new playService.Stub() {
		
		@Override
		public void play() throws RemoteException {
			
//			try {
//				TimeUnit.SECONDS.sleep(300);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			Log.i(Tag+" stub play()","curr process pid:"+android.os.Process.myPid()
					+"mPlayer:"+mPlayer.toString());
			new Thread() {
				
				public void run() {
					
					mPlayer.start();
				}
			}.start();
		}

		@Override
		public void pause() throws RemoteException {
			mPlayer.pause();
		}

		@Override
		public void stop() throws RemoteException {
			mPlayer.stop();
		}

		@Override
		public int getDuration() throws RemoteException {
			return mPlayer.getDuration();
		}

		@Override
		public int getCurrentPosition() throws RemoteException {
			return mPlayer.getCurrentPosition();
		}

		@Override
		public void seekTo(int current) throws RemoteException {
			mPlayer.seekTo(current);
		}

		@Override
		public boolean setLoop(boolean loop) throws RemoteException {
			return false;
		}

	};
	

	MyBinder b;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(Tag+" onStartCommand","curr start Id:"+startId);
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public IBinder onBind(Intent intent) {
		int pid=Process.myPid();
		Log.i(Tag+" onBind","curr process pid:"+pid );
		return stub;
//		return b;
	}
	
	public class MyBinder extends Binder {
		
		public Service getService() {
			
			return MPlayService.this;
		}
	}
	
	public void my_start() {
		
		mPlayer.start();
	}
	
	public void pause() {
		
		mPlayer.pause();
	}
	
	public void stop() {
		
		mPlayer.stop();
	}
	private Person p;
	public void sendPerson(Person p) {
		
		this.p= p;
	}
	
	@Override
	public boolean stopService(Intent name) {

		return super.stopService(name);
	}
	
	public int getCurr() {
		
		return mPlayer.getCurrentPosition();
	}
	
	@Override
	public void onCreate() {

		super.onCreate();
		Log.i(Tag+" onCreate()","curr process pid:"+android.os.Process.myPid() );
		b= new MyBinder();
		mPlayer= MediaPlayer.create(getApplicationContext(), R.raw.test);
	}
	
	@Override
	public void onDestroy() {

		super.onDestroy();
		Log.i(Tag+" onDestroy()","curr process pid:"+android.os.Process.myPid() );
		mPlayer.stop();
		System.out.println("onDestroy");
	}
}