package com.example.samsung.p1131_actionmode;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by samsung on 10.05.2017.
 */

class Messager {

    private static final String LOG_TAG = "myLogs";

    public static void sendToAllRecipients(final Context context, final String message) {
        Log.d(LOG_TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void sendToOnlyLog(final String message) {
        Log.d(LOG_TAG, message);
    }

}
