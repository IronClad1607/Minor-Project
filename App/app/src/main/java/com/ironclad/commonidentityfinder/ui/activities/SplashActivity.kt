package com.ironclad.commonidentityfinder.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.ironclad.commonidentityfinder.R
import com.ironclad.commonidentityfinder.databinding.ActivitySplashBinding
import com.ironclad.commonidentityfinder.utils.Constants.Companion.SPLASH_DELAY

class SplashActivity : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        Glide.with(this).load(R.drawable.splash_image).into(binding?.ivSplash!!)

        Handler().postDelayed({
            val splashIntent = Intent(this, HomeActivity::class.java)
            //TODO add log messages
            startActivity(splashIntent)
        }, SPLASH_DELAY)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}