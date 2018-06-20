package com.example.arbaaz.newsgateway;

/**
 * Created by Arbaaz on 5/3/2017.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class NewsService extends Service {

    private static final String TAG = "NewsService";
    private boolean isRunning = true;

    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

        //Creating new thread for my service
        //Always write your long running tasks in a separate thread, to avoid ANR

        new Thread(new Runnable() {
            @Override
            public void run() {

                //In this example we are just displaying a log message every 1000ms
                while (isRunning) {

                    if (isRunning) {
                        Intent intent = new Intent();

                        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                        intent.setAction("FREE_MEMORY_REPORT");
/*
                        intent.putExtra("PROCESSORS", String.format("%d", Runtime.getRuntime().availableProcessors()));
                        intent.putExtra("FREE_MEMORY", String.format("%,d bytes", Runtime.getRuntime().freeMemory()));
                        intent.putExtra("MAX_MEMORY", String.format("%,d bytes", Runtime.getRuntime().maxMemory()));
                        intent.putExtra("TOTAL_MEMORY", String.format("%,d bytes", Runtime.getRuntime().totalMemory()));
                        long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                        intent.putExtra("USED_MEMORY", String.format("%,d bytes", used));
*/
                        sendBroadcast(intent);
                        Log.d(TAG, "run: Broadcast sent!");
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();


        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
    }
}