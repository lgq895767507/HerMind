package com.hermind;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Create by lewis on 18-6-3.
 */
public class HerMindApplication extends Application {

    private static HerMindApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        mContext = this;
        Bmob.initialize(this, "29f80e533b1142e1a4b5dc31c88a11b3");

    }

    public static Context getContext(){
        return mContext;
    }
}
