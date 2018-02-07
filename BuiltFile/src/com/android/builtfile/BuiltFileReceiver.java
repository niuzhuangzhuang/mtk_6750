package com.android.builtfile;

import android.app.AppGlobals;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.android.builtfile.R;

public class BuiltFileReceiver extends BroadcastReceiver {
    private final String TAG = "BuiltFileReceiver";
    private final String SYSTEM_FILE_PATH = "/system/media/audio/music/";
    private String[] mDec;
    private String mInternal;

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        boolean success = false;
        //cyb add end
        if (action.equals("android.intent.action.BOOT_COMPLETED")) {
            openService(context);
            success = startCopy(context);
        }
        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            openService(context);
        }

        if (!success) {
            Log.d(TAG, "Create Directory And File, Error!");
        } else {
            Log.d(TAG, "Create Directory And File, Success!");
        }
    }

    private boolean startCopy(Context context) {
        boolean justOnce = context.getResources().getBoolean(R.bool.config_copy_files_only_once);
        boolean isFirstBoot = false;
        //cyb add 20150709
        try {
            isFirstBoot = AppGlobals.getPackageManager().isFirstBoot();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "isFirstBoot : " + isFirstBoot);
        File file = new File(SYSTEM_FILE_PATH);
        if (file.isDirectory()) {
            mDec = file.list();
        }
        if (mDec == null || mDec.length == 0) {
            return false;
        }
        mInternal = Environment.getExternalStorageDirectory().getPath() + "/";
        boolean bool  = false;
        if (justOnce) {
            if (isFirstBoot) {
                bool = createDirectory();
            }
        } else {
            bool = createDirectory();
        }
        return bool;
    }

    private void openService(Context context) {
        Intent intent = new Intent();
        intent.setPackage("com.android.builtfile");
        ComponentName cn = new ComponentName("com.android.builtfile", "com.android.builtfile.LoaderService");
        intent.setComponent(cn);
        context.startService(intent);
    }

    private boolean createDirectory() {
        boolean result = false;
        String mSysCodeName;
        String mDataCodeName;
        String filename;

        for (String name : mDec) {
            mSysCodeName = SYSTEM_FILE_PATH + name;
            mDataCodeName = mInternal + "Music/";

            try {
                File parent = new File(mDataCodeName);
                if (!parent.isDirectory() && !parent.mkdir()) {
                    Log.d(TAG, "Can't create parent directory : " + parent.getPath());
                    return result;
                }
            }
            catch (Exception e) {
            }

            result = copyFile(mSysCodeName, mDataCodeName, name);
            if (!result) {
                Log.d(TAG, "copy file:" + mSysCodeName + " to " + mDataCodeName + " , error!");
            }
        }
        return result;
    }

    private boolean copyFile(String src, String dest, String filename) {
        boolean result = false;
        InputStream in = null;
        FileOutputStream out = null;

        try {
            File srcFile = new File(src);
            File destFile = new File(dest + filename);
            if (!srcFile.exists() && destFile.exists()) {
                Log.d(TAG, "file:" + dest + "/" + " , exists!");
                return result;
            }
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[4096];
            int i;
            while ((i = in.read(buffer)) >= 0) {
                out.write(buffer, 0, i);
            }

            out.flush();
            out.getFD().sync();

            in.close();
            out.close();
            result = true;
        } catch (IOException e) {
            Log.d(TAG, "copyfile error :" + e);
            return false;
        }
        return result;
    }
}
