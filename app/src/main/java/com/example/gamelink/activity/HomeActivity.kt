package com.example.gamelink.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gamelink.R
import com.example.gamelink.fragment.*
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.nav_header.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val lolAnnonceFragment = LolAnnonceFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_fragment, lolAnnonceFragment)
            .commit()

        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        mAuth = FirebaseAuth.getInstance()

        setSupportActionBar(toolbar)
        updateUiWhenCreating()
        configureDrawerLayout()
        configureNavigationView()
    }

    private fun configureDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            0,
            0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun configureNavigationView() {
        val navigationView = findViewById<NavigationView>(R.id.home_activity_nav_view)
        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val createAnnonceFragment = CreateAnnonceFragment()
        val lolAnnonceFragment = LolAnnonceFragment()
        val lolCommunityFragment = LolCommunauteFragment()
        val lolStatFragmet = LolStatFragment()
        val lastestMsgChatFragment = LastestMsgChatFragment()

        when (item.itemId) {
            R.id.create_annone -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.main_fragment,
                    createAnnonceFragment
                ).commit()

            }
            R.id.lol_stat -> {
                Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().replace(
                    R.id.main_fragment,
                    lolStatFragmet
                ).commit()

            }
            R.id.lol_annonces -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.main_fragment,
                    lolAnnonceFragment
                ).commit()

            }
            R.id.lol_community -> {
                Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().replace(
                    R.id.main_fragment,
                    lolCommunityFragment
                ).commit()
            }
            R.id.chat -> {
                Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().replace(
                    R.id.main_fragment,
                    lastestMsgChatFragment
                ).commit()
            }
            R.id.jeux_2_stat -> {
                Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show()

            }
            R.id.jeux_2_annonces -> {
                Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show()

            }
            R.id.jeux_2_community -> {
                Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.jeux_3_stat -> {
                Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show()

            }
            R.id.jeux_3_annonces -> {
                Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show()

            }
            R.id.jeux_3_community -> {
                Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.log_out -> {
                signOutUserFromFirebase()
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    fun updateUiWhenCreating() {
        val currentUser = mAuth.currentUser
        val navigationView = findViewById<NavigationView>(R.id.home_activity_nav_view)
        val headerContainer: View = navigationView.getHeaderView(0)
        val mImageView = headerContainer.findViewById<ImageView>(R.id.drawer_imageview_profile)
        val mNameText = headerContainer.findViewById<TextView>(R.id.drawer_username)
        val mEmailText = headerContainer.findViewById<TextView>(R.id.drawer_email)

        mNameText.text = currentUser!!.displayName
        mEmailText.text = currentUser.email

        Glide.with(this)
            .load(currentUser.photoUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(mImageView)


    }

    private fun signOutUserFromFirebase() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnSuccessListener(this, updateUIAfterRESTRequestsCompleted()!!)
    }

    private fun updateUIAfterRESTRequestsCompleted(): OnSuccessListener<Void?>? {
        return OnSuccessListener { aVoid: Void? -> finish() }
    }
}