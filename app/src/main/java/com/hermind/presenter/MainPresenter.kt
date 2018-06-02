package com.hermind.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hermind.iview.IMainView
import com.hermind.model.bmob.Message

class MainPresenter(val iMainVIew: IMainView) {


    fun loadDatas() {
        val query = BmobQuery<Message>()
        query.setLimit(10).order("-createdAt")
                .findObjects(object : FindListener<Message>() {
                    override fun done(list: List<Message>?, exception: BmobException?) {
                        if (exception == null) {
                            iMainVIew.showDatas(list ?: ArrayList<Message>())
                        } else {
                            iMainVIew.showDatasFailed(exception)
                        }
                    }

                })
    }


}