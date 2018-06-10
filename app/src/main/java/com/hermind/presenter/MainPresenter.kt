package com.hermind.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hermind.iview.IMainView
import com.hermind.model.bmob.Message

class MainPresenter(val iMainView: IMainView) {

    /**
     * 初始化获取数据
     */
    fun loadDatas() {
        val query = BmobQuery<Message>()
        query.setLimit(10).order("-createdAt")
                .findObjects(object : FindListener<Message>() {
                    override fun done(list: List<Message>?, exception: BmobException?) {
                        if (exception == null) {
                            iMainView.showDatas(list ?: ArrayList<Message>())
                        } else {
                            iMainView.showDatasFailed(exception)
                        }
                    }

                })
    }


    /**
     * 刷新数据
     */
    fun refreshDatas(){
        val query = BmobQuery<Message>()
        query.setLimit(10).order("-createdAt")
                .findObjects(object : FindListener<Message>() {
                    override fun done(list: List<Message>, e: BmobException?) {
                        if (e == null) {
                            iMainView.refreshDatas(list)
                        } else {
                            iMainView.refreshDatasFailed(e)
                        }
                    }
                })
    }


    /**
     * 加载更多数据，每次加载10条最多
     */
    fun loadMoreDatas(skip: Int){
        val query = BmobQuery<Message>()
        query.setLimit(10).setSkip(skip).order("-createdAt")
                .findObjects(object : FindListener<Message>() {
                    override fun done(list: List<Message>, e: BmobException?) {
                        if (e == null) {
                            iMainView.loadMoreDatas(list)
                        } else {
                            iMainView.loadMoreDatasFailed(e)
                        }
                    }
                })
    }


}