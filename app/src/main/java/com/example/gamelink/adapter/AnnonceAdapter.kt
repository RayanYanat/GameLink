package com.example.gamelink.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamelink.R
import com.example.gamelink.model.Annonce
import com.example.gamelink.model.User
import kotlinx.android.synthetic.main.annonce_item.view.*

class AnnonceAdapter(private val listUser: List<Annonce>, val listener: ItemClickListener) :
    RecyclerView.Adapter<AnnonceAdapter.AnnonceViewHolder>() {

    private var mData: List<Annonce> = mutableListOf()

    fun setResults(data: List<Annonce>) {
        mData = data
        Log.d("AnnonceAdapter", "AnnonceAdapter:" + mData)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnnonceViewHolder {
        return AnnonceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.annonce_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnnonceViewHolder, position: Int) {
        var userItem = mData[position]

        Log.d("AnnonceAdapter", "AnnonceAdapter:" + userItem.annonceText)
        holder.itemUsername.text = userItem.username
        holder.itemDate.text = userItem.dateAnnonce
        holder.itemTittle.text = userItem.tittle
        holder.gameName.text = userItem.gameAnnonce
        Glide.with(holder.itemView).load(userItem.urlPicture).into(holder.itemMainPic)

        holder.itemView.setOnClickListener{
            listener.onItemClickListener(userItem)
        }
    }

    override fun getItemCount(): Int {
        Log.d("AnnonceAdapter", "AnnonceAdapter:" + listUser.size)
        return if (mData.isNotEmpty()) mData.size else 0    }

    class AnnonceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemUsername = view.username_item
        val itemDate = view.date_item
        val itemTittle = view.tittle_item
        val gameName = view.game_name_item
        val itemMainPic = view.user_main_pic
    }

    interface ItemClickListener {
        fun onItemClickListener(annonce: Annonce)
    }
}