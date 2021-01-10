package com.ironclad.commonidentityfinder.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ironclad.commonidentityfinder.R
import com.ironclad.commonidentityfinder.databinding.ActivityLoginBinding
import com.ironclad.commonidentityfinder.utils.Constants
import com.ironclad.commonidentityfinder.utils.Constants.Companion.SCREEN_TAG

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    private val currentUser by lazy {
        Firebase.auth.currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this).load(R.drawable.splash_image).into(binding.ivSplash)

        auth = Firebase.auth

        if (currentUser != null) {
            val directIntent = Intent(this, HomeActivity::class.java)
            Log.d(SCREEN_TAG, "Going to Home")
            startActivity(directIntent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}