package com.hermind.view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.hermind.R
import com.hermind.adapter.MainDataAdapter
import com.hermind.iview.IMainView
import com.hermind.model.bmob.Message
import com.hermind.presenter.MainPresenter
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, IMainView {


    private var skip = 10
    //remember init data list
    private var datas: ArrayList<Message> = ArrayList()
    private var mainDataAdapter: MainDataAdapter? = null
    private val mainPresenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //system view by create
        formatInitView()
        //myself view init
        initView()
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

    private fun initData() {
        mainPresenter.loadDatas()
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

    override fun showDatasFailed(e: Exception) {
        toast(e.toString())
    }

    override fun refreshDatas(list: List<Message>) {
        datas.clear()
        datas.addAll(list)
        mainDataAdapter?.notifyDataSetChanged()
    }

    override fun refreshDatasFailed(e: Exception) {
        toast(e.toString())
    }

    override fun loadMoreDatas(list: List<Message>) {
        //分页加载更多，每次加载最多10条数据

        Log.i("lgq","list:" + list.toString())
        if (list.isEmpty()) {
            toast(getString(R.string.no_more_data))
        } else if (list.size < skip) {
            //最后一页
            if (datas.size < list.size + skip) {
                datas.addAll(list)
                mainDataAdapter?.notifyDataSetChanged()
            } else if (datas.size == list.size + skip){
                toast(getString(R.string.no_more_data))
            }
        } else {
            //说明还有下一页
            skip += skip
            datas.addAll(list)
            mainDataAdapter?.notifyDataSetChanged()
        }
    }

    override fun loadMoreDatasFailed(e: Exception) {
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
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.version_name) {
            // Handle the camera action
        } else if (id == R.id.connect_me) {

        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
