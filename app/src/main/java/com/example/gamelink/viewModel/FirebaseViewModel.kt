package com.example.gamelink.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamelink.model.Annonce
import com.example.gamelink.model.User
import com.example.gamelink.repository.FirebaseRepository
import com.google.firebase.firestore.EventListener

class FirebaseViewModel : ViewModel() {

    private var firebaseRepository = FirebaseRepository()
    private var savedUsers : MutableLiveData<List<User>> = MutableLiveData()
    private var currentUser: MutableLiveData<User> = MutableLiveData()



    fun saveAnnonceToFirebase(annonce :List<Annonce>?, uid: String){
        firebaseRepository.updateAnnonce(annonce,uid).addOnFailureListener {
            Log.e(TAG,"Failed to save Annonce!")
        }
    }



    fun getUser (uid: String) : LiveData<User>{
        val curreentUserr = MutableLiveData<User>()
        firebaseRepository.getUser(uid).addOnSuccessListener {
            val userItem = it?.toObject(User::class.java)
            currentUser.value = userItem
            curreentUserr.value = userItem!!
        }
        return curreentUserr
    }



    fun createUserToFirebase(username :String, uid: String, photoUrl : String){
        firebaseRepository.createUser(username,uid,photoUrl).addOnFailureListener {
            Log.e(TAG,"Failed to save Address!")
        }
    }


    fun getSavedUsers(): LiveData<List<User>> {
        firebaseRepository.getUsersCollection().addSnapshotListener(EventListener { value, e ->
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