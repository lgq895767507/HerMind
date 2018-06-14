package com.hermind.presenter

import android.os.Environment
import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.DownloadFileListener
import cn.bmob.v3.listener.FindListener
import com.hermind.iview.IDownloadView
import com.hermind.model.bmob.ApkStore
import java.io.File

class DownloadPresenter(val iDownloadView: IDownloadView) {


    fun getAPkFileAndUpdate() {

        val query = BmobQuery<ApkStore>()
        query.findObjects(object : FindListener<ApkStore>() {
            override fun done(p0: MutableList<ApkStore>?, exception: BmobException?) {
                if (exception == null) {
                    val apkFile = p0?.get(0)?.apk_file
                    val saveFile: File = File(Environment.getExternalStorageDirectory().path)
                    apkFile?.download(saveFile, object : DownloadFileListener() {

                        override fun onProgress(value: Int?, newworkSpeed: Long) {
                            Log.i("lgq", "下载进度：$value,$newworkSpeed")
                        }

                        override fun done(p0: String?, p1: BmobException?) {
                            iDownloadView.downloadSuccess()
                        }

                    })
                    Log.i("lgq", "p0:" + p0.toString())

                } else {
                    iDownloadView.downloadFailed(exception)
                }
            }

        })
    }
}