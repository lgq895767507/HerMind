package com.hermind.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.hermind.R

class MainDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var dataItemText: TextView = itemView.findViewById(R.id.dataItemText)
    var messageDate: TextView = itemView.findViewById(R.id.messageDate)

}
