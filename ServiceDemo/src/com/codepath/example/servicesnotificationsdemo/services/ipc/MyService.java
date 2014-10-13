package com.codepath.example.servicesnotificationsdemo.services.ipc;

import com.codepath.example.servicesnotificationsdemo.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {

	
	public void onCreate() {
	
		mediaThread.start();
	}
	
	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}
	
	public Thread mediaThread= new Thread() {
		
		MediaPlayer player= null;
		public void run() {
			
			player= MediaPlayer.create(getApplicationContext(), R.raw.test);
		}
		
		public void my_start() {
			
			player.start();
		}
		
		public void my_pause() {
			
			player.pause();
		}
		
		public void my_stop() {
			
			player.stop();
		}
	};
	
	public void start() {
		
//		mediaThread.
	}
	
	public void pause() {
		
		
	}
	
	public void stop() {
		
		
	}
}