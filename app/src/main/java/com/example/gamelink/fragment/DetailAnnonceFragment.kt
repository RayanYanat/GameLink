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
import com.example.gamelink.R
import com.example.gamelink.activity.ChatLogActivity
import com.example.gamelink.model.Annonce
import com.example.gamelink.model.User
import com.example.gamelink.viewModel.FirebaseViewModel
import kotlinx.android.synthetic.main.create_annonce.*
import kotlinx.android.synthetic.main.fragment_detail_annonce.*

class DetailAnnonceFragment : Fragment() {

    private lateinit var mViewModel: FirebaseViewModel
    private lateinit var currentAnnonce: Annonce


    companion object {
        val USER_KEY = "USER_KEY"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_detail_annonce, container, false)
        mViewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
        if(arguments != null) {
            val currentAnnonceId = requireArguments().getString("ANNONCE_ID")
            mViewModel.getSavedUsers().observe(viewLifecycleOwner, Observer<List<User>> {
                it.forEach { user ->
                    if (user.listAnnonce != null) {
                        user.listAnnonce.forEach {
                            if (it.annonceText == currentAnnonceId) {
                                currentAnnonce = it
                                updateUi(currentAnnonce)
                                Log.d("DetailAnnonceFragment", "DetailAnnonceFragment:" + currentAnnonce)
                            }
                        }


                    }
                }
            })
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedUserUidAnnonce = requireArguments().getString("USER_ANNONCE_ID")
        send_msg_btn_annonce.setOnClickListener {
            mViewModel.getUser(selectedUserUidAnnonce!!).observe(viewLifecycleOwner, Observer {
                val selectedUserFromAnnonce = it
                val intent = Intent(view.context, ChatLogActivity::class.java)
                intent.putExtra(USER_KEY,selectedUserFromAnnonce)
                startActivity(intent)
            })

        }

    }

    private fun updateUi(result: Annonce) {

        tittle_annonce_detail.text = result.tittle
        desc_annonce_detail.text= result.annonceText
        game_annonce_detail.text = result.gameAnnonce
    }
}

