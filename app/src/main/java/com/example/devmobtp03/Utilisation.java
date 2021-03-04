package com.example.devmobtp03;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class Utilisation implements LifecycleObserver {

    private String TAG = this.getClass().getSimpleName();
    private static int nbUtilisation;

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void nombreUtilisation() {
        Log.i(TAG, "Observer: ON_RESUME");
        nbUtilisation++;
    }

    public int getNbUtilisation() {
        return nbUtilisation;
    }

}
