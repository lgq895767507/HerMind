package com.hermind.model.bmob

import cn.bmob.v3.BmobObject

class Version : BmobObject() {
    var versionName: String? = null
    var versionId: Int? = 0
    var content:String? = null
}
