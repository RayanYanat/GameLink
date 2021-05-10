package com.example.gamelink.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.gamelink.R
import com.example.gamelink.fragment.DetailAnnonceFragment
import com.example.gamelink.model.ChatMessage
import com.example.gamelink.model.User
import com.example.gamelink.viewModel.FirebaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
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

        mViewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)

        recyclerview_chat_log.adapter = adapter
        val user = intent.getParcelableExtra<User>(DetailAnnonceFragment.USER_KEY)

        supportActionBar?.title = user!!.uid

       listenForMessages()



        send_button_chat_log.setOnClickListener {
            performSendMessage()
        }
    }


    private fun listenForMessages() {

        val user = intent.getParcelableExtra<User>(DetailAnnonceFragment.USER_KEY)
        val currentUserUid = FirebaseAuth.getInstance().uid
        val ref = FirebaseFirestore.getInstance().collection("/user_messages/$currentUserUid/${user?.username}")

        Log.d("ChatLogcurrentid", currentUserUid)
        Log.d("ChatLogtouserid", user?.uid)

        ref.addSnapshotListener {snapshots, e ->
        if (e != null) {
            Log.w("TAG", "listen:error", e)
            return@addSnapshotListener
        }

        for (dc in snapshots!!.documentChanges) {
            Log.d("ChatLog", snapshots!!.documentChanges.size.toString())
            if (dc.type == DocumentChange.Type.ADDED){
                chatMessage = dc.document.toObject(ChatMessage::class.java)
                Log.d("ChatLog", chatMessage.text)
                Log.d("ChatLog", chatMessage.id)

                if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                    mViewModel.getUser(currentUserUid!!).observe(this, Observer {
                        adapter.add(ChatFromItem(chatMessage.text,it))
                    })
                } else {
                    adapter.add(ChatToItem(chatMessage.text,user!!))
                }
            }
        }
    }
    }

    private fun performSendMessage() {
        // how do we actually send a message to firebase...
        val text = edittext_chat_log.text.toString()

        val fromId = FirebaseAuth.getInstance().uid
        val user = intent.getParcelableExtra<User>(DetailAnnonceFragment.USER_KEY)
        val toId = user!!.username

        if (fromId == null) return

        //val reference = FirebaseFirestore.getInstance().collection("messages")
        val reference = FirebaseFirestore.getInstance().collection("/user_messages/$fromId/$toId")
        val toReference = FirebaseFirestore.getInstance().collection("/user_messages/$toId/$fromId")


        val chatMessage = ChatMessage(reference.document().id,text, fromId, toId, System.currentTimeMillis() / 1000)
        reference.add(chatMessage)
            .addOnSuccessListener {
                Log.d("ChatLog", "Saved our chat message: ${reference.document().id}")
                edittext_chat_log.text.clear()
                recyclerview_chat_log.scrollToPosition(adapter.itemCount - 1)
            }

        toReference.add(chatMessage)
    }

}



class ChatFromItem(val text: String,val user: User): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val targetImageView = viewHolder.itemView.imageView
        viewHolder.itemView.textview_from_roww.text = text
        Glide.with(viewHolder.itemView).load(user.profileImageUrl).into(targetImageView)
    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }
}

class ChatToItem(val text: String,val user: User): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val targetImageView = viewHolder.itemView.imageView_to_row
        viewHolder.itemView.textview_to_row.text = text
        Glide.with(viewHolder.itemView).load(user.profileImageUrl).into(targetImageView)
    }

    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }



}
