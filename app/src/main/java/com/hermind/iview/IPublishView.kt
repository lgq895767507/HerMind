package com.hermind.iview

/**
 * Create by lewis on 18-8-4.
 *
 */
interface IPublishView {

    fun publishMessageSuccess()

    fun publishMessageFailed(e: Throwable)
}