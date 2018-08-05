package com.hermind.view

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import com.hermind.R
import com.hermind.iview.IPublishView
import com.hermind.presenter.PublishPresenter
import kotlinx.android.synthetic.main.activity_publish.*

private const val MAX_TEXT_LENGTH = 99

class PublishActivity : BaseActivity(),IPublishView {

    private val publishPresenter by lazy { PublishPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)
        if (supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        calculateLeaveTextlength()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_button, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_send){
            if (messageEdit.text.isBlank()){
                toast(getString(R.string.publish_content_not_null))
            }else{
                publishPresenter.publishMessage(messageEdit.text.toString())
            }
            true
        }else return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun calculateLeaveTextlength(){
        messageEdit.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(MAX_TEXT_LENGTH))
        messageEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                leaveTextCount.text = String.format(getString(R.string.text_content_one), MAX_TEXT_LENGTH - messageEdit.text.length)
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })
    }

    override fun publishMessageSuccess() {
        finish()
    }

    override fun publishMessageFailed(e: Throwable) {
        toast(getString(R.string.publish_error) + e.toString())
    }
}
