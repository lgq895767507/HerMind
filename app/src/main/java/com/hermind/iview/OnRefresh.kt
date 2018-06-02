package com.hermind.iview
import android.widget.Toast
import com.hermind.adapter.MainDataAdapter
import com.hermind.model.bmob.Message
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import java.util.ArrayList


/**
 * Create by lewis on 18-6-3.
 *
 */

class OnRefresh(private val mainDataAdapter: MainDataAdapter, private val datas: List<Message>) : OnRefreshListener {

    override fun onRefresh(refreshlayout: RefreshLayout) {
        refreshlayout.finishRefresh(2000)

      /*  val messagePresenter = MessagePresenter()
        messagePresenter.findMessageAll(object : MessagePresenter.MessageListCallBack {
            override fun success(list: List<Message>) {
                datas.clear()
                datas.addAll(list)
                mainDataAdapter.notifyDataSetChanged()
                ToastUtils.showToast(FruitApplication.getContext(), "更新成功!", Toast.LENGTH_SHORT)
            }

            override fun failed() {

            }
        })*/


    }
}
