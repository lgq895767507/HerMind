package com.hermind.iview

import com.hermind.model.bean.VersionModel

/**
 * Create by lewis on 18-6-11.
 *
 */
interface IVersionView {

    fun reqLastestVersion(versionModel: VersionModel)

    fun reqVersionFailed(e: Throwable)
}