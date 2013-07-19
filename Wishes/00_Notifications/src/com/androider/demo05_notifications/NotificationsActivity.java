package com.androider.demo05_notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NotificationsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
	public void notifyStatusBar(View view) {
		//instantiaza NotificationManager si Notification
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher, "New notification", System.currentTimeMillis());

		//ascunde notificare odata ce este selectat
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		//creaza un Intent pentru a redirection userul catre o noua activitate
		Intent intent = new Intent(this, NotificationDestinationActivity.class);
		PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
		notification.setLatestEventInfo(this, "Title notification", "This is a cool notificaion, if you click we jump on destination.", activity);
		
		//lanseaza notificarea
		notification.number += 1;
		notificationManager.notify(0, notification);
	}
	
}