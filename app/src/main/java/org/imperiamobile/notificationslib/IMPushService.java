package org.imperiamobile.notificationslib;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * <p/>
 * To use this add to your :app dependencies -> compile 'com.google.firebase:firebase-messaging:9.4.0'
 * add in the end of :app gradle -> apply plugin: 'com.google.gms.google-services'
 * add in your Project gradle -> classpath 'com.google.gms:google-services:3.0.0'
 * <p/>
 * This service must have intent-filter -> <action android:name="com.google.firebase.MESSAGING_EVENT" />
 * <p/>
 * Created by borislav.chomonev on 02/09/2016.
 */
public class IMPushService extends FirebaseMessagingService {

    public static final String TITLE = "title";
    public static final String BODY = "body";

    /**
     * This method is called only if the app is in foreground and new PushNotification is received.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        doSendBroadcast(remoteMessage.getNotification(), remoteMessage.getData());
    }


    private void doSendBroadcast(RemoteMessage.Notification notification, Map<String, String> data) {
        Intent it = new Intent(ForegroundNotificationHandler.EVENT_SNACKBAR);

        if (data != null) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                it.putExtra(entry.getKey(), entry.getValue());
            }
        }

        if (notification != null) {
            String title = notification.getTitle();
            String body = notification.getBody();
            it.putExtra(TITLE, title);
            it.putExtra(BODY, body);
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(it);
    }

}