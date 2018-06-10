package com.hermind.public.utils

import android.content.Context

/**
 * Create by lewis on 18-6-10.
 *
 */
object SystemUtils{

    fun  getLocalVersionCode(context: Context):Int{
        var versionCode = 0
        try {
            val packageInfo = context.applicationContext
                    .packageManager
                    .getPackageInfo(context.packageName, 0 )
            versionCode =  packageInfo.versionCode
        }catch (e: Exception){
            e.printStackTrace()
        }
        return versionCode
    }


    fun getLoaalVesionName(context: Context):String{
        var versionName = ""
        try {
            val packageInfo = context.applicationContext
                    .packageManager
                    .getPackageInfo(context.packageName, 0 )
            versionName =  packageInfo.versionName
        }catch (e: Exception){
            e.printStackTrace()
        }
        return versionName
    }

}