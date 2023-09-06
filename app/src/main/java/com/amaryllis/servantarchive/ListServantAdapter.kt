package com.amaryllis.servantarchive

import android.content.ComponentCallbacks
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListServantAdapter(private val listServant: ArrayList<Servant>) : RecyclerView.Adapter<ListServantAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgIllust: ImageView = itemView.findViewById(R.id.servant_illust)
        val tvName: TextView = itemView.findViewById(R.id.servant_name)
        val tvKelas: TextView = itemView.findViewById(R.id.servant_class)
        val tvTipe: TextView = itemView.findViewById(R.id.servant_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_servant, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, kelas, tipe, illust) = listServant[position]
        holder.imgIllust.setImageResource(illust)
        holder.tvName.text = name
        holder.tvKelas.text = kelas
        holder.tvTipe.text = tipe

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listServant[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listServant.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Servant)
    }

    fun setOnClickListener(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}