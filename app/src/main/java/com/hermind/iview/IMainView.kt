package com.hermind.iview

import com.hermind.model.bmob.Message

interface IMainView {

    fun showDatas(list: List<Message>)

    fun showDatasFailed(e: Exception)


}