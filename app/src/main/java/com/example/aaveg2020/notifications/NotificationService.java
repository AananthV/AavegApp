package com.example.aaveg2020.notifications;

import android.app.NotificationManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.aaveg2020.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }

    public void showNotification(String title,String message){
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this,"MyNotifications")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.aavegwhite)
                .setAutoCancel(true)
                .setContentText(message);
        NotificationManagerCompat manager=NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());
    }
}
