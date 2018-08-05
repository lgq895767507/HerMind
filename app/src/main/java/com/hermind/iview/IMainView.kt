package com.hermind.iview

import com.hermind.model.bmob.Message

interface IMainView {

    fun showDatas(list: List<Message>)

    fun showDatasFailed(e: Throwable)

    fun refreshDatas(list: List<Message>)

    fun refreshDatasFailed(e: Throwable)

    fun loadMoreDatas(list: List<Message>)

    fun loadMoreDatasFailed(e: Throwable)

}