package com.amaryllis.servantarchive

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    companion object {
        const val KEY_SERVANT = "key_servant"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataServant = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Servant>(KEY_SERVANT, Servant::class.java)
        } else {
            @Suppress("DEPRECATED")
            intent.getParcelableExtra(KEY_SERVANT)
        }

        supportActionBar!!.title = dataServant?.name

        val tvItemName: TextView = findViewById(R.id.servant_name)
        val tvItemKelas: TextView = findViewById(R.id.servant_class)
        val tvItemTipe: TextView = findViewById(R.id.servant_type)
        val imgItemIllustfull: ImageView = findViewById(R.id.servant_illustfull)
        val imgItemIllust: ImageView = findViewById(R.id.servant_illust)
        val tvItemCharinfo: TextView = findViewById(R.id.servant_charinfo)
        val tvItemProfile: TextView = findViewById(R.id.servant_profile)

        if (dataServant != null) {
            tvItemName.text = dataServant.name
            tvItemKelas.text = dataServant.kelas
            tvItemTipe.text = dataServant.tipe
            imgItemIllustfull.setImageResource(dataServant.illustfull)
            imgItemIllust.setImageResource(dataServant.illust)
            tvItemCharinfo.text = dataServant.charinfo
            tvItemProfile.text = dataServant.profile
        }

        val shareButton: Button = findViewById(R.id.share)

        shareButton.setOnClickListener {
            val intentShare = Intent()
            intentShare.action = Intent.ACTION_SEND
            intentShare.putExtra(Intent.EXTRA_TEXT, "${dataServant?.link}")
            intentShare.type = "text/plain"
            startActivity(Intent.createChooser(intentShare, "Bagikan link"))
        }
    }

}