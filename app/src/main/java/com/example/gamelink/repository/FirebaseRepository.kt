package com.example.gamelink.repository

import com.example.gamelink.model.Annonce
import com.example.gamelink.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class FirebaseRepository {

    private val COLLECTION_NAME = "users"

    fun getUsersCollection(): CollectionReference {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME)
    }

    fun createUser(username: String,uid: String , urlPicture: String?): Task<Void?> {
        val userToCreate = User(username, uid, urlPicture, null)
        return getUsersCollection().document(uid).set(userToCreate)
    }

    fun getUser(uid: String): Task<DocumentSnapshot?> {
        return getUsersCollection().document(uid).get()
    }

    fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    fun getCurrentUserId(): String {
        return Objects.requireNonNull(FirebaseAuth.getInstance().currentUser).uid
    }

    fun updateAnnonce(annonce: List<Annonce>?, uid: String): Task<Void?> {
        return getUsersCollection().document(uid).update("listAnnonce", annonce)
    }
}
