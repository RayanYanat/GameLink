package com.example.gamelink.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelink.R
import com.example.gamelink.activity.ChatLogActivity
import com.example.gamelink.adapter.LatestMessageRow
import com.example.gamelink.fragment.DetailAnnonceFragment.Companion.FROM_USER_KEY
import com.example.gamelink.fragment.DetailAnnonceFragment.Companion.TO_USER_KEY
import com.example.gamelink.model.ChatMessage
import com.example.gamelink.model.User
import com.example.gamelink.viewModel.FirebaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.lastes_chat_msg_fragment.*
import kotlinx.android.synthetic.main.lastes_chat_msg_fragment.view.*
import kotlinx.android.synthetic.main.latest_message_row.view.*

class LastestMsgChatFragment : Fragment() {

    private val adapter = GroupAdapter<ViewHolder>()
    private lateinit var mViewModel: FirebaseViewModel
    private val latestMessagesMap = HashMap<String, ChatMessage>()
    private var fromUser : User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.lastes_chat_msg_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_latest_messages)
        mViewModel =ViewModelProvider(this).get(FirebaseViewModel::class.java)

        recyclerView.adapter = adapter

        val currentUserId = FirebaseAuth.getInstance().uid
        mViewModel.getUser(currentUserId!!).observe(viewLifecycleOwner, { user ->
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

        mViewModel.getSavedUsers().observe(viewLifecycleOwner, {
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
                        latestMessagesMap[snapshots.id] = chatMessage
                        Log.d("lastestChatLog2", chatMessage.text)
                        refreshRecyclerViewMessages()
                    }
                }
            }
        })

    }

    private fun refreshRecyclerViewMessages() {
        latestMessagesMap.values.forEach {
            Log.d("lastestChatLog3",it.text)
            adapter.add(LatestMessageRow(it))
        }
    }

}

