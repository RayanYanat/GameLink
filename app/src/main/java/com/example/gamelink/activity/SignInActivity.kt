package com.example.gamelink.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.gamelink.R
import com.example.gamelink.viewModel.FirebaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.DocumentSnapshot

class SignInActivity: FragmentActivity (){

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient
    private lateinit var mViewModel: FirebaseViewModel
    private val RC_SIGN_IN = 110


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)
        mViewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
        val signInBtn = findViewById<SignInButton>(R.id.sign_in_button)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        mAuth = FirebaseAuth.getInstance()

        signInBtn.setOnClickListener{
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if(task.isSuccessful){
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("SignInActivity", "Google sign in failed", e)
                }
            }else{
                Log.w("SignInActivity", exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("SignInActivity", "signInWithCredential:success")
                        createUserInFirestore()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("SignInActivity", "signInWithCredential:failure", task.exception)
                    }
                }
    }

    private fun createUserInFirestore(){
        val currentUser = mAuth.currentUser
        val uid = currentUser?.uid
        val username = currentUser?.displayName
        val urlPicture = currentUser?.photoUrl

        mViewModel.getUser(uid!!).observe(this, Observer { user ->
            if(user != null && user.listAnnonce != null  ){
                mViewModel.saveAnnonceToFirebase(user.listAnnonce,uid)
            }else{
                mViewModel.getSavedUsers().observe(this,Observer {
                 if (!(it.contains(user))){
                     mViewModel.createUserToFirebase(username!!,uid,urlPicture.toString())
                 }
                })
            }
        })


    }
}