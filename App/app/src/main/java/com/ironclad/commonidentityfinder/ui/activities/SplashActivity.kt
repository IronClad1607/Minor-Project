package com.ironclad.commonidentityfinder.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ironclad.commonidentityfinder.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}