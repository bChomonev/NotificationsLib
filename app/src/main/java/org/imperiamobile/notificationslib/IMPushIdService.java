package org.imperiamobile.notificationslib;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * @since 1.9.2016 ã.
 * <p/>
 * To use this add to your :app dependencies -> compile 'com.google.firebase:firebase-messaging:9.4.0'
 * add in the end of :app gradle -> apply plugin: 'com.google.gms.google-services'
 * add in your Project gradle -> classpath 'com.google.gms:google-services:3.0.0'
 * <p/>
 * This service must have intent-filter -> <action android:name="com.google.firebase.INSTANCE_ID_EVENT" />
 * <p/>
 * Created by borislav.chomonev on 02/09/2016.
 */
public class IMPushIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //TODO @Override send the new token token to the server.
    }
}