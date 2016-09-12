package org.imperiamobile.notificationslib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.Map;

/**
 * Created by borislav.chomonev on 07/09/2016.
 */
public class IMPushUtil {
    public static final String FRAGMENT_ID = "fragmentId";

    /**
     * @param params Bundle with arguments
     * @param map    String id -> Fragment.class; example -> ("2" -> FragmentA.class)
     * @return the class of the fragment the needs to be opened
     */
    @Nullable
    public static Class<? extends Fragment> checkForPush(Bundle params, Map<String, Class<? extends Fragment>> map) {
        if (params != null && params.containsKey(FRAGMENT_ID)) {
            String id = params.getString(FRAGMENT_ID);
            if (map != null && map.containsKey(id)) {
                return map.get(id);
            }
        }
        return null;
    }
}
