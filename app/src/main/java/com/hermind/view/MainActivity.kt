package com.hermind.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.hermind.R
import com.hermind.adapter.MainDataAdapter
import com.hermind.iview.IDownloadView
import com.hermind.iview.IMainView
import com.hermind.iview.IVersionView
import com.hermind.model.bean.VersionModel
import com.hermind.model.bmob.Message
import com.hermind.presenter.DownloadPresenter
import com.hermind.presenter.MainPresenter
import com.hermind.presenter.VersionPresenter
import com.hermind.public.utils.SystemUtils
import com.hermind.utils.InstallApkUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.content_main.*
import java.util.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, IMainView, IDownloadView, IVersionView {


    private var skip = 10
    //remember init data list
    private var datas: ArrayList<Message> = ArrayList()
    private var mainDataAdapter: MainDataAdapter? = null
    private val mainPresenter by lazy { MainPresenter(this) }
    private val downloadPresenter by lazy { DownloadPresenter(this) }
    private val versionPresenter by lazy { VersionPresenter(this) }

    //init progress view
    private var progressText: TextView? = null
    private var progressBar: ProgressBar? = null
    private var progressDialog: AlertDialog? = null
    private val progressHandler: ProgressHandler = ProgressHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //system view by create
        formatInitView()
        //myself view init
        initView()
        initPermission()
        initData()
    }

    private fun formatInitView() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun initView() {
        recycleView.layoutManager = LinearLayoutManager(this)
    }

    private fun initPermission() {
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe({
                    if (it) {
                        toast(getString(R.string.agress_permission))
                    } else {
                        toast(getString(R.string.refuse_permission))
                    }
                })

    }

    private fun initData() {
        mainPresenter.loadDatas()
        versionPresenter.getVersionInfo()
    }


    override fun showDatas(list: List<Message>) {
        datas = list as ArrayList<Message>
        mainDataAdapter = MainDataAdapter(datas, this)
        recycleView.adapter = mainDataAdapter
        refreshLayout.setOnRefreshListener {
            it.finishRefresh(2000)
            mainPresenter.refreshDatas()
        }
        refreshLayout.setOnLoadMoreListener {
            it.finishLoadMore(2000)
            mainPresenter.loadMoreDatas(skip)
        }
    }

    override fun showDatasFailed(e: Throwable) {
        toast(e.toString())
    }

    override fun refreshDatas(list: List<Message>) {
        datas.clear()
        datas.addAll(list)
        mainDataAdapter?.notifyDataSetChanged()
    }

    override fun refreshDatasFailed(e: Throwable) {
        toast(e.toString())
    }

    override fun loadMoreDatas(list: List<Message>) {
        //分页加载更多，每次加载最多10条数据
        if (list.isEmpty()) {
            toast(getString(R.string.no_more_data))
        } else if (list.size < skip) {
            //最后一页
            if (datas.size < list.size + skip) {
                datas.addAll(list)
                mainDataAdapter?.notifyDataSetChanged()
            } else if (datas.size == list.size + skip) {
                toast(getString(R.string.no_more_data))
            }
        } else {
            //说明还有下一页
            skip += skip
            datas.addAll(list)
            mainDataAdapter?.notifyDataSetChanged()
        }
    }

    override fun loadMoreDatasFailed(e: Throwable) {
        toast(e.toString())
    }


    override fun reqLastestVersion(versionModel: VersionModel) {
        if (versionModel.versionCode > SystemUtils.getLocalVersionCode(this)) {
            AlertDialog.Builder(this).setTitle("提示")
                    .setMessage("发现新版本" + versionModel.versionName + ",是否更新?")
                    .setNegativeButton("取消", { dialog, _ ->
                        dialog.dismiss()
                    })
                    .setPositiveButton("确认", { dialog, which ->
                        dialog.dismiss()
                        toast("更新下载")
                        downloadPresenter.getAPkFileAndUpdate()
                        progressDialog = AlertDialog.Builder(this).create()
                        val view = LayoutInflater.from(this).inflate(R.layout.layout_progress_dialog, null)
                        progressDialog?.setView(view)
                        progressText = view.findViewById<TextView>(R.id.progressText)
                        progressBar = view.findViewById(R.id.progressBar)
                        progressDialog?.show()
                    })
                    .show()
        }
    }


    override fun reqVersionFailed(e: Throwable) {
        toast(e.toString())
    }


    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            startActivity(Intent(this@MainActivity, PublishActivity::class.java))
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.version_name) {
            AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage(String.format(Locale.getDefault(),
                            getString(R.string.local_version), SystemUtils.getLoaalVesionName(this)))
                    .setPositiveButton("我知道了") { dialog, _ -> dialog?.dismiss() }
                    .show()

            // Handle the camera action
        } else if (id == R.id.connect_me) {

        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun downloadProgress(progress: Int) {
        val message = progressHandler.obtainMessage()
        message.arg1 = progress
        progressHandler.sendMessage(message)
    }


    override fun downloadSuccess(apkFileUri: String) {
        toast("下载完成")
        progressDialog?.dismiss()
        InstallApkUtils.install(this, apkFileUri)
    }

    override fun downloadFailed(e: Throwable) {
        toast("下载失败" + e.toString())
        progressDialog?.dismiss()
    }


    inner class ProgressHandler : Handler() {
        override fun handleMessage(msg: android.os.Message?) {
            super.handleMessage(msg)
            progressText?.text = "正在下载:" + msg?.arg1 + "%"
            progressBar?.progress = msg?.arg1 ?: 0
        }
    }
}
