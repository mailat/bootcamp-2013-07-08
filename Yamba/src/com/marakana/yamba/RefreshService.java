package com.marakana.yamba;

import java.util.List;
import com.marakana.android.yamba.clientlib.YambaClient.Status;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class RefreshService extends IntentService {	
	private static final String TAG = RefreshService.class.getSimpleName();
	
	public RefreshService() {
		super(TAG);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreate()");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		try {
			Log.d(TAG, "onHandleIntent()");
			//get all the posts from the timeline
			List<Status> timeline = ((YambaApplication) getApplication()).getYambaClient().getTimeline(20);
			
			for (Status status:timeline)
			{
				Log.d(TAG, "createdAt: " + status.getCreatedAt() + ", user: " + status.getUser() + ", posted: " + status.getMessage());
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy()");
	}

}
