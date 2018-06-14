package com.hermind.iview

/**
 * Create by lewis on 18-6-11.
 *
 */
interface IDownloadView{

    fun downloadSuccess()

    fun downloadFailed(e: Exception)
}