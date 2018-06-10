package com.hermind.iview

import com.hermind.model.bean.VersionModel
import com.hermind.model.bmob.Message

interface IMainView {

    fun showDatas(list: List<Message>)

    fun showDatasFailed(e: Exception)

    fun refreshDatas(list: List<Message>)

    fun refreshDatasFailed(e: Exception)

    fun loadMoreDatas(list: List<Message>)

    fun loadMoreDatasFailed(e: Exception)

    fun reqLastestVersion(versionModel: VersionModel)

    fun reqVersionFailed(e: Exception)


}