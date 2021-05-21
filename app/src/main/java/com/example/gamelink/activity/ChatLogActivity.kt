package com.example.gamelink.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gamelink.R
import com.example.gamelink.adapter.ChatFromItem
import com.example.gamelink.adapter.ChatToItem
import com.example.gamelink.fragment.DetailAnnonceFragment
import com.example.gamelink.model.ChatMessage
import com.example.gamelink.model.User
import com.example.gamelink.viewModel.FirebaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*

class ChatLogActivity : AppCompatActivity() {

    private val adapter = GroupAdapter<ViewHolder>()
    private lateinit var mViewModel: FirebaseViewModel
    private lateinit var chatMessage: ChatMessage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        mViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
        val user = intent.getParcelableExtra<User>(DetailAnnonceFragment.TO_USER_KEY)

        recyclerview_chat_log.adapter = adapter

        supportActionBar?.title = user!!.uid
        Log.d("ChatLogtouseridCreate", user.uid)

        listenForMessage()

        send_button_chat_log.setOnClickListener {
            performSendMessage()
        }
    }

    //retrieves and updates user-to-user chats
    private fun listenForMessage() {

        val user = intent.getParcelableExtra<User>(DetailAnnonceFragment.TO_USER_KEY)
        val fromUser = intent.getParcelableExtra<User>(DetailAnnonceFragment.FROM_USER_KEY)
        val currentUserUid = FirebaseAuth.getInstance().uid

        Log.d("ChatLogSize", currentUserUid!!)

        val ref = FirebaseFirestore.getInstance()
            .collection("/user_messages/$currentUserUid/${user?.username}")
            .orderBy("timestamp", Query.Direction.ASCENDING)

        ref.addSnapshotListener { snapshots, e ->
            if (e != null) {
                Log.w("TAG", "listen:error", e)
                return@addSnapshotListener
            }

            for (dc in snapshots!!.documentChanges) {
                when (dc.type) {
                    DocumentChange.Type.ADDED -> {
                        chatMessage = dc.document.toObject(ChatMessage::class.java)
                        if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                            adapter.add(ChatFromItem(chatMessage.text, fromUser!!))
                        } else {
                            adapter.add(ChatToItem(chatMessage.text, user!!))
                        }
                    }
                    DocumentChange.Type.MODIFIED -> { }
                    DocumentChange.Type.REMOVED -> { }
                }
            }

        }
    }


    // send message to firestore
    private fun performSendMessage() {

        val text = edittext_chat_log.text.toString()

        val fromId = FirebaseAuth.getInstance().uid
        val user = intent.getParcelableExtra<User>(DetailAnnonceFragment.TO_USER_KEY)
        val toId = user!!.username

        if (fromId == null) return


        val reference = FirebaseFirestore.getInstance().collection("/user_messages/$fromId/$toId")
        val toReference = FirebaseFirestore.getInstance().collection("/user_messages/$toId/$fromId")
        val msgReference = FirebaseFirestore.getInstance().collection("messages")


        val chatMessage = ChatMessage(
            reference.document().id,
            text,
            fromId,
            toId,
            System.currentTimeMillis() / 1000
        )
        reference.add(chatMessage)
            .addOnSuccessListener {
                Log.d("ChatLog", "Saved our chat message: ${reference.document().id}")
                edittext_chat_log.text.clear()
                recyclerview_chat_log.scrollToPosition(adapter.itemCount - 1)
            }

        toReference.add(chatMessage)
        msgReference.add(chatMessage)

        val latestMessageRef =
            FirebaseFirestore.getInstance().collection("/latest-messages/$fromId/$toId")
        latestMessageRef.document("lastestMsg").set(chatMessage)


        val latestMessageToRef =
            FirebaseFirestore.getInstance().collection("/latest-messages/$toId/$fromId")
        latestMessageToRef.document("lastestMsg").set(chatMessage)

    }

}




