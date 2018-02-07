package com.android.phonedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by fantao on 17-6-14.
 */

public class DemoReceiver extends BroadcastReceiver {
    private static final String ACTION_DEMO_OPEN = "com.android.phonedemo.open";
    private static final String ACTION_DEMO_CLOSE = "com.android.phonedemo.close";
    private static final String ACTION_DEMO_START = "com.android.phonedemo.start";
    private static boolean mIsOpen;

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case ACTION_DEMO_OPEN:
                mIsOpen = true;
                startShowDemo(context);
                break;
            case ACTION_DEMO_CLOSE:
                mIsOpen = false;
                break;
            case ACTION_DEMO_START:
                if (mIsOpen) {
                    startShowDemo(context);
                }
                break;
        }
    }

    private void startShowDemo(Context context) {
        context.startActivity(new Intent(context, DemoActivity.class));
    }
}
