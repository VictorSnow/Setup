package com.victorup.setup;

import android.app.Application;

public class SetupApplication extends Application {
    private boolean isRunning;

    @Override
    public void onCreate() {
        isRunning = false;
        super.onCreate();
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean status) {
        isRunning = status;
    }
}
