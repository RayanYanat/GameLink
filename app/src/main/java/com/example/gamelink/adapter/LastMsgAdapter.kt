package com.example.gamelink.adapter

import android.util.Log
import com.bumptech.glide.Glide
import com.example.gamelink.R
import com.example.gamelink.model.ChatMessage
import com.example.gamelink.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.latest_message_row.view.*

class LatestMessageRow(private val chatMessage: ChatMessage) : Item<ViewHolder>() {

    var chatPartnerUser: User?=null

    override fun bind(viewHolder: ViewHolder, position: Int) {


        val chatPartnerId: String

        if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
            chatPartnerId = chatMessage.toId
        } else {
            chatPartnerId = chatMessage.fromId
        }

        val ref = FirebaseFirestore.getInstance().collection("/users").document(chatPartnerId)

        ref.addSnapshotListener{snapshots, e ->
            if (e != null) {
                Log.w("TAG", "listen:error", e)
                return@addSnapshotListener
            }

            if (snapshots!!.exists()){
                chatPartnerUser = snapshots.toObject(User::class.java)
                viewHolder.itemView.username_textview_latest_message.text = chatPartnerUser?.uid
                val targetImageView = viewHolder.itemView.imageview_latest_message
                Glide.with(viewHolder.itemView).load(chatPartnerUser?.profileImageUrl).into(targetImageView)

            }
        }

        viewHolder.itemView.message_textview_latest_message.text = chatMessage.text
    }

    override fun getLayout(): Int {
        return R.layout.latest_message_row
    }
}