package com.hermind.view

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {



    fun toast(content: String){
        Toast.makeText(applicationContext,content, Toast.LENGTH_SHORT).show()
    }

}