package org.imperiamobile.notificationslib;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import java.util.Map;

/**
 * Created by borislav.chomonev on 02/09/2016.
 */
public class ForegroundNotificationHandler {

    public static final String EVENT_SNACKBAR = "EVENT_SNACK_BAR";
    private BroadcastReceiver mMessageReceiver;
    private Activity mActivity;
    private IOnSnackbarActionClicked mOnSnackbarActionClicked;
    private Map<String, Class<? extends Fragment>> map;

    public ForegroundNotificationHandler(Activity activity, Map<String, Class<? extends Fragment>> map, IOnSnackbarActionClicked onSnackbarActionClicked) {
        this.mActivity = activity;
        this.mOnSnackbarActionClicked = onSnackbarActionClicked;
        this.map = map;

        initBroadcastReceiver(mActivity);
        LocalBroadcastManager.getInstance(mActivity).registerReceiver(mMessageReceiver, new IntentFilter(EVENT_SNACKBAR));
    }

    private void initBroadcastReceiver(final Activity activity) {
        mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final Bundle args = intent.getExtras();
                String title = "";
                String body = "";
                if (args != null) {
                    title = args.getString(IMPushService.TITLE);
                    body = args.getString(IMPushService.BODY);
                }

                showSnackbar(args, title, body, activity);
            }
        };
    }

    private void showSnackbar(final Bundle args, String title, String body, Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        View viewById = decorView.findViewById(android.R.id.content);

        Snackbar snackbar = Snackbar.make(viewById, body, Snackbar.LENGTH_INDEFINITE)
                .setAction(title, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnSnackbarActionClicked != null) {
                            mOnSnackbarActionClicked.onSnackbarActionHit(IMPushUtil.checkForPush(args, map));
                        }
                    }
                });
        snackbar.show();
    }

    public interface IOnSnackbarActionClicked {
        void onSnackbarActionHit(Class<? extends Fragment> clazz);
    }

    /**
     * Call this method in the corresponding activity's onPause method to unregister BroadcastReceiver
     */
    public void onPause() {
        LocalBroadcastManager.getInstance(mActivity).unregisterReceiver(mMessageReceiver);
    }

}
