package com.hermind

import android.app.Application
import android.content.Context

import cn.bmob.v3.Bmob
/*import zlc.season.rxdownload3.core.DownloadConfig
import zlc.season.rxdownload3.extension.ApkInstallExtension
import zlc.season.rxdownload3.extension.ApkOpenExtension*/

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
/*
        val builder = DownloadConfig.Builder.create(this)
                .setDebug(true)
                .enableDb(true)
                //              .setDbActor(CustomSqliteActor(this))
//                .enableService(true)
                .enableNotification(true)
                .addExtension(ApkInstallExtension::class.java)
                .addExtension(ApkOpenExtension::class.java)

        DownloadConfig.init(builder)*/
    }

    companion object {

        private var mContext: HerMindApplication? = null

        val context: Context?
            get() = mContext
    }
}
