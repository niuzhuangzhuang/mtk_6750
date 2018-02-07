package com.android.builtfile;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import cn.android.loader.LoaderMain;


public class LoaderService extends Service {
    public static final String ACTION_EXIT = "action.exit";
    private LoaderMain mHost;
    public LoaderService() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(null == intent || null == intent.getAction()){
            return super.onStartCommand(intent, flags, startId);
        }
       if(ACTION_EXIT.equals(intent.getAction())){
            if(null != mHost){
                mHost.Show(this);
            }
        }
       return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        loadLocalJar();
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void loadLocalJar(){
        System.out.println("...loadLocalJar");
        LoaderMain main = new LoaderMain();
        main.Init(this);
        mHost = main;
    }

}

