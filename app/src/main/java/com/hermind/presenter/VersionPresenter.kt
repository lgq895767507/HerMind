package com.hermind.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hermind.iview.IVersionView
import com.hermind.model.bean.VersionModel
import com.hermind.model.bmob.Version

class VersionPresenter(val iVersionView: IVersionView) {


    /**
     * 请求版本信息
     */
    fun getVersionInfo() {
        val query = BmobQuery<Version>()
        query.setLimit(1).order("-createdAt")
                .findObjects(object : FindListener<Version>() {
                    override fun done(list: List<Version>?, exception: BmobException?) {
                        if (exception == null) {
                            if (list?.size ?: 0 > 0) {
                                iVersionView.reqLastestVersion(VersionModel(list?.get(0)?.versionId ?: 0,
                                        list?.get(0)?.versionName ?: "",
                                        list?.get(0)?.content ?: ""))
                            }
                        } else {
                            iVersionView.reqVersionFailed(exception)
                        }
                    }

                })
    }
}