package com.example.gamelink.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamelink.R
import com.example.gamelink.activity.ChatFromItem
import com.example.gamelink.activity.ChatLogActivity
import com.example.gamelink.activity.ChatToItem
import com.example.gamelink.fragment.DetailAnnonceFragment.Companion.FROM_USER_KEY
import com.example.gamelink.fragment.DetailAnnonceFragment.Companion.TO_USER_KEY
import com.example.gamelink.model.ChatMessage
import com.example.gamelink.model.User
import com.example.gamelink.viewModel.FirebaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.lastes_chat_msg_fragment.*
import kotlinx.android.synthetic.main.lastes_chat_msg_fragment.view.*
import kotlinx.android.synthetic.main.latest_message_row.view.*

class LastestMsgChatFragment : Fragment() {

    val adapter = GroupAdapter<ViewHolder>()
    private lateinit var mViewModel: FirebaseViewModel
    val latestMessagesMap = HashMap<String, ChatMessage>()
    var fromUser : User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.lastes_chat_msg_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_latest_messages)
        mViewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)

        recyclerView.adapter = adapter

        val currentUserId = FirebaseAuth.getInstance().uid
        mViewModel.getUser(currentUserId!!).observe(viewLifecycleOwner, Observer { user ->
            fromUser = user
            Log.d("DetailAnnonceFragSid", "DetailAnnonceFragment:${fromUser}")
        })

        adapter.setOnItemClickListener { item, view ->
            val intent = Intent(context, ChatLogActivity::class.java)
            val row = item as LatestMessageRow
            intent.putExtra(TO_USER_KEY, row.chatPartnerUser)
            intent.putExtra(FROM_USER_KEY,fromUser)
            startActivity(intent)
        }

          listenForLatestMessages()



        return view
    }



    private fun listenForLatestMessages(){
        val fromId = FirebaseAuth.getInstance().uid

        mViewModel.getSavedUsers().observe(viewLifecycleOwner, Observer {
            it.forEach { user ->

                Log.d("lastestChatLog", user.uid)

                val ref = FirebaseFirestore.getInstance().collection("/latest-messages/$fromId/${user.username}").document("lastestMsg")
                ref.addSnapshotListener {snapshots, e ->
                    if (e != null) {
                        Log.w("TAG", "listen:error", e)
                        return@addSnapshotListener
                    }

                    if (snapshots!!.exists()){
                        val chatMessage = snapshots.toObject(ChatMessage::class.java)
                        Log.d("lastestChatLog", chatMessage!!.text)
                        latestMessagesMap[snapshots.id] = chatMessage!!
                        Log.d("lastestChatLog2", chatMessage!!.text)
                        refreshRecyclerViewMessages()
                    }

                }
            }
        })

    }

    class LatestMessageRow(val chatMessage: ChatMessage) : Item<ViewHolder>() {

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

    private fun refreshRecyclerViewMessages() {
      //  adapter.clear()
        latestMessagesMap.values.forEach {
            Log.d("lastestChatLog3",it.text)
            adapter.add(LatestMessageRow(it))
        }
    }

}

//                    for (dc in snapshots!!.documentChanges) {
//                        Log.d("ChatLogSize", snapshots!!.documentChanges.size.toString())
//                        if (dc.type == DocumentChange.Type.ADDED){
//
//                            val chatMessage = dc.document.toObject(ChatMessage::class.java)
//                            latestMessagesMap[dc.document.id] = chatMessage
//                            refreshRecyclerViewMessages()
//
//                            Log.d("ChatLog", chatMessage.text)
//                            Log.d("ChatLog", chatMessage.id)
//                        }
//                    }