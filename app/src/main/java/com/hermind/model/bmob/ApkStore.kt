package com.hermind.model.bmob

import cn.bmob.v3.BmobObject
import cn.bmob.v3.datatype.BmobFile

class ApkStore : BmobObject() {
    var apk_file: BmobFile? = null
}
