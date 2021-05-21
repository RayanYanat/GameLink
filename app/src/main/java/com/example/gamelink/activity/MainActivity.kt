package com.example.gamelink.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import com.example.gamelink.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : Activity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed({
            if (user != null){
                val homeIntent = Intent(this,HomeActivity::class.java)
                startActivity(homeIntent)
                finish()
            }else{
                val signInIntent = Intent(this,SignInActivity::class.java)
                startActivity(signInIntent)
                finish()
            }

        },2000)
    }

}