package org.imperiamobile.notificationslib;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by borislav.chomonev on 07/09/2016.
 */
public class BackgroundNotificationHandler {
    private static BackgroundNotificationHandler instance;
    private Bundle args;

    public static BackgroundNotificationHandler getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    public static void init(Bundle args) {
        if (args == null) {
            args = new Bundle();
        }
        if (instance == null) {
            instance = new BackgroundNotificationHandler(args);
        } else {
            instance.args = args;
        }
    }

    private BackgroundNotificationHandler(Bundle args) {
        this.args = args;
    }

    @NonNull
    public Bundle getArgs() {
        return args;
    }
}
