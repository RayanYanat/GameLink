package com.example.gamelink.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamelink.model.Annonce
import com.example.gamelink.model.User
import com.example.gamelink.repository.FirebaseRepository
import com.example.gamelink.repository.LolApiRepository
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class FirebaseViewModel : ViewModel() {

    var firebaseRepository = FirebaseRepository()

    var savedUsers : MutableLiveData<List<User>> = MutableLiveData()
    lateinit var  currentUser: LiveData<User>



    fun saveAnnonceToFirebase(annonce :List<Annonce>?, uid: String){
        firebaseRepository.updateAnnonce(annonce,uid).addOnFailureListener {
            Log.e(TAG,"Failed to save Annonce!")
        }
    }

    fun getCurrentUser(){
        firebaseRepository.getCurrentUser()
    }
    fun getCurrentUserId(){
        firebaseRepository.getCurrentUserId()
    }





    fun createUserToFirebase(username :String, uid: String, photoUrl : String){
        firebaseRepository.createUser(username,uid,photoUrl).addOnFailureListener {
            Log.e(TAG,"Failed to save Address!")
        }
    }


    fun getSavedUsers(): LiveData<List<User>> {
        firebaseRepository.getUsersCollection().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                savedUsers.value = null
                return@EventListener
            }

            val savedUserList : MutableList<User> = mutableListOf()
            for (doc in value!!) {
                val userItem = doc.toObject(User::class.java)
                savedUserList.add(userItem)
            }
            savedUsers.value = savedUserList
        })

        return savedUsers
    }
}