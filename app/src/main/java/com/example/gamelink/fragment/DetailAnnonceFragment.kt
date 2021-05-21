package com.example.gamelink.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gamelink.R
import com.example.gamelink.activity.ChatLogActivity
import com.example.gamelink.model.Annonce
import com.example.gamelink.model.User
import com.example.gamelink.viewModel.FirebaseViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_detail_annonce.*

class DetailAnnonceFragment : Fragment() {

    private lateinit var mViewModel: FirebaseViewModel
    private lateinit var currentAnnonce: Annonce

    private var selectedUserFromAnnonce : User? = null
    private var fromUser : User? = null


    companion object {
        const val TO_USER_KEY = "USER_KEY"
        const val FROM_USER_KEY = "FROM_USER_KEY"
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_detail_annonce, container, false)
        val annonceBtn = view.findViewById<Button>(R.id.send_msg_btn_annonce)
        mViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
        if(arguments != null) {
            val currentAnnonceId = requireArguments().getString("ANNONCE_ID")
            mViewModel.getSavedUsers().observe(viewLifecycleOwner, {
                it.forEach { user ->
                    if (user.listAnnonce != null) {
                        user.listAnnonce.forEach {
                            if (it.annonceText == currentAnnonceId) {
                                currentAnnonce = it
                                updateUi(currentAnnonce)
                            }
                        }
                    }
                }
            })
        }

        val selectedUserUidAnnonce = requireArguments().getString("USER_ANNONCE_ID")
        val currentUserId = FirebaseAuth.getInstance().uid

        mViewModel.getUser(selectedUserUidAnnonce!!).observe(viewLifecycleOwner, {
             selectedUserFromAnnonce = it

        })

        mViewModel.getUser(currentUserId!!).observe(viewLifecycleOwner, { user ->
            fromUser = user
        })

        if (selectedUserUidAnnonce == currentUserId){
            annonceBtn.visibility = View.INVISIBLE
        }

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            send_msg_btn_annonce.setOnClickListener {

                val intent = Intent(view.context, ChatLogActivity::class.java)
                intent.putExtra(TO_USER_KEY,selectedUserFromAnnonce)
                intent.putExtra(FROM_USER_KEY,fromUser)
                startActivity(intent)

            }



    }

    private fun updateUi(result: Annonce) {

        tittle_annonce_detail.text = result.tittle
        desc_annonce_detail.text= result.annonceText
        game_annonce_detail.text = result.gameAnnonce
    }
}

