package com.marakana.yamba;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdaterService extends Service {	
	private static final String TAG = UpdaterService.class.getSimpleName();
	
	static final int DELAY = 60000;
	private boolean runFlag = false;
	private Updater updater;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		//instance of thread, not yet in run mode
		this.updater = new Updater();
		
		Log.d(TAG, "onCreate()");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		
		//we mark it as started
		this.runFlag = true;
		this.updater.start();

		Log.d(TAG, "onStartCommand()");
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		this.runFlag = false;
		
		this.updater.interrupt();
		this.updater = null;
		
		Log.d(TAG, "onDestroy()");
	}
	
	/**
	 * Updater
	 * performs the actual get posts from yamba API
	 */
	private class Updater extends Thread
	{
		public Updater ()
		{
			super ("UpdaterService-UpdaterThread");
		}

		@Override
		public void run() {
			//actual work in the service
			UpdaterService updaterService = UpdaterService.this;
			
			while (updaterService.runFlag)
			{
				Log.d(TAG, "heavy work in Updater thread");
				
				try {
					Log.d(TAG, "Updater run");
					Thread.sleep(DELAY);
				} catch (Throwable e) {
					e.printStackTrace();
					updaterService.runFlag = false;
				}
			}
		}
	}
	
}
