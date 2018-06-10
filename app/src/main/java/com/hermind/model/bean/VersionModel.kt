package com.hermind.model.bean

import cn.bmob.v3.datatype.BmobFile

/**
 * Create by lewis on 18-6-10.
 *
 */
data class VersionModel(val versionCode:Int,
                        val versionName: String,
                        val downloadUrl: String,
                        val content: String)