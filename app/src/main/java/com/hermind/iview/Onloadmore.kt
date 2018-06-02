package com.hermind.iview

import com.hermind.adapter.MainDataAdapter
import com.hermind.model.bmob.Message
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener

/**
 * Create by lewis on 18-6-3.
 *
 */
class Onloadmore(private val mainDataAdapter: MainDataAdapter, private val datas: List<Message>) : OnLoadMoreListener {

    private var skip = 10

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        refreshLayout.finishLoadMore(2000)

        /*  val messagePresenter = MessagePresenter()
       messagePresenter.findMessageAll(object : MessagePresenter.MessageListCallBack {
           override fun success(list: List<Message>) {
               //加载更多，临时保存上次的数据
               if (list.size == 0) {
                   //没有任何数据
                   ToastUtils.showToast(FruitApplication.getContext(), "没有更多数据", Toast.LENGTH_SHORT)
               } else if (list.size < ConstantUtils.LIMIT_COUNT) {
                   //说明当前页面是最后一页
                   if (datas.size < skip + list.size) {
                       datas.addAll(list)
                       mainDataAdapter.notifyDataSetChanged()
                   } else if (datas.size == skip + list.size) {
                       ToastUtils.showToast(FruitApplication.getContext(), "没有更多数据", Toast.LENGTH_SHORT)
                   }
               } else {
                   //说明还会有下一页
                   skip = skip + skip
                   datas.addAll(list)
                   mainDataAdapter.notifyDataSetChanged()
                   ToastUtils.showToast(FruitApplication.getContext(), "加载更多", Toast.LENGTH_SHORT)
               }
           }

           override fun failed() {
               ToastUtils.showToast(FruitApplication.getContext(), "没有更多", Toast.LENGTH_SHORT)
           }
       }, skip)*/
    }


}
