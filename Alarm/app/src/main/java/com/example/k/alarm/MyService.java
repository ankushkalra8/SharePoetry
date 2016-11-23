package com.example.k.alarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by K on 6/18/2016.
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onStart(Intent intent,int startID){
        MediaPlayer mp;
        mp = MediaPlayer.create(getApplicationContext(),R.raw.song);
        mp.start();
    }
}
