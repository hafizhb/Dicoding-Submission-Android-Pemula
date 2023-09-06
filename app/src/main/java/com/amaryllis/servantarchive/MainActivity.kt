package com.amaryllis.servantarchive

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvServants: RecyclerView
    private val list = ArrayList<Servant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvServants = findViewById(R.id.rv_servants)
        rvServants.setHasFixedSize(true)

        list.addAll(getListServants())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListServants(): ArrayList<Servant> {
        val dataName = resources.getStringArray(R.array.servant_name)
        val dataKelas = resources.getStringArray(R.array.servant_class)
        val dataTipe = resources.getStringArray(R.array.servant_type)
        val dataIllustfull = resources.obtainTypedArray(R.array.servant_illustfull)
        val dataIllust = resources.obtainTypedArray(R.array.servant_illust)
        val dataCharinfo = resources.getStringArray(R.array.servant_charinfo)
        val dataProfile = resources.getStringArray(R.array.servant_profile)
        val dataLink = resources.getStringArray(R.array.servant_link)
        val listServant = ArrayList<Servant>()
        for (i in dataName.indices) {
            val servant = Servant(
                dataName[i],
                dataKelas[i],
                dataTipe[i],
                dataIllustfull.getResourceId(i,-1),
                dataIllust.getResourceId(i, -1),
                dataCharinfo[i],
                dataProfile[i],
                dataLink[i]
            )
            listServant.add(servant)
        }
        return listServant
    }

    private fun showRecyclerList() {
        rvServants.layoutManager = LinearLayoutManager(this)
        val listServantAdapter = ListServantAdapter(list)
        rvServants.adapter = listServantAdapter

        listServantAdapter.setOnClickListener(object: ListServantAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Servant) {
                val item = Servant(data.name, data.kelas, data.tipe, data.illustfull, data.illust, data.charinfo, data.profile, data.link)
                val moveObjIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveObjIntent.putExtra(DetailActivity.KEY_SERVANT, item)
                startActivity(moveObjIntent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        selectMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun selectMenu(selectedMenu: Int) {
        when(selectedMenu) {
            R.id.about -> {
                val intentAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentAbout)
            }
        }

    }
}
