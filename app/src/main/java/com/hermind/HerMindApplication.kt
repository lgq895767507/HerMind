package com.hermind

import android.app.Application
import android.content.Context

import cn.bmob.v3.Bmob

/**
 * Create by lewis on 18-6-3.
 */
class HerMindApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        mContext = this
        Bmob.initialize(this, "29f80e533b1142e1a4b5dc31c88a11b3")
    }

    companion object {

        private var mContext: HerMindApplication? = null

        val context: Context?
            get() = mContext
    }
}
