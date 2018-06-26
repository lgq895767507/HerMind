package com.hermind.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import java.io.File

/**
 * Create by lewis on 18-6-26.
 *
 */
object InstallApkUtils {

    fun install(context: Context, apkPath: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            val file = File(apkPath)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val apkUri = FileProvider.getUriForFile(context, "com.hermind.provider", file)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(File(apkPath)), "application/vnd.android.package-archive")
        }
        context.startActivity(intent)
    }


}