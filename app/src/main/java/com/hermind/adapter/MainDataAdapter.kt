package com.hermind.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hermind.R
import com.hermind.model.bmob.Message
import com.hermind.viewholder.MainDataHolder

class MainDataAdapter(private val datas: List<Message>, private val mContext: Context  ): RecyclerView.Adapter<MainDataHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainDataHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.main_data_item, parent, false)
        return MainDataHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MainDataHolder, position: Int) {
        holder.dataItemText.text = datas[position].content
        holder.messageDate.text = datas[position].createdAt
    }
}