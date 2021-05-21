 package com.example.gamelink.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gamelink.R
import com.example.gamelink.model.Annonce
import com.example.gamelink.viewModel.FirebaseViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.create_annonce.*
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class CreateAnnonceFragment:Fragment() {

    private lateinit var mViewModel: FirebaseViewModel
    private lateinit var mAuth: FirebaseAuth
    private var  userListAnnonce = ArrayList<Annonce>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.create_annonce, container, false)
        mViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        val uid = currentUser!!.uid

        mViewModel.getSavedUsers().observe(viewLifecycleOwner, {
            it.forEach{ user ->
                if (user.listAnnonce != null && user.uid == uid){
                    userListAnnonce.clear()
                    user.listAnnonce.forEach {
                        userListAnnonce.add(it)
                    }
                }
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = mAuth.currentUser
        val uid = currentUser!!.uid

        post_button.setOnClickListener {
            val annonceTittle = annonce_tittle.text.toString()
            val annonceText = annonce_desc.text.toString()
            val annonceGame = annonce_game.text.toString()
            val calendar: Calendar = Calendar.getInstance()
            val currentDate: String = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
            val annonce = Annonce(annonceGame,annonceText,currentDate,annonceTittle,currentUser.displayName!!,uid,currentUser.photoUrl!!.toString())


            mViewModel.getSavedUsers().observe(viewLifecycleOwner, {
                it.forEach{ user ->
                    if (user.listAnnonce != null && user.uid == uid){
                        userListAnnonce.clear()
                        user.listAnnonce.forEach {
                            userListAnnonce.add(it)
                        }
                    }
                }
            })
            userListAnnonce.add(annonce)
            mViewModel.saveAnnonceToFirebase(userListAnnonce,uid)
        }
    }

}