package com.hermind.presenter

import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hermind.iview.IPublishView
import com.hermind.model.bmob.Message

/**
 * Create by lewis on 18-8-4.
 *
 */

class PublishPresenter(val iView: IPublishView){

    /**
     * 发布消息到服务器
     */
    fun publishMessage(content: String){
        val message = Message()
        message.content = content
        message.save(object : SaveListener<String>() {
            override fun done(s: String, e: BmobException?) {
                if (e == null) {
                    iView.publishMessageSuccess()
                } else {
                    iView.publishMessageFailed(e)
                }
            }
        })
    }

}