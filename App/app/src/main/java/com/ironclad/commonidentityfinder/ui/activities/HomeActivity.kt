package com.ironclad.commonidentityfinder.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.ironclad.commonidentityfinder.NavHomeDirections
import com.ironclad.commonidentityfinder.R
import com.ironclad.commonidentityfinder.databinding.ActivityHomeBinding
import com.ironclad.commonidentityfinder.ui.fragment.DirectoryFragmentDirections
import com.ironclad.commonidentityfinder.utils.Constants.Companion.LOGIN_DELAY
import com.ironclad.commonidentityfinder.utils.Constants.Companion.SCREEN_TAG

class HomeActivity : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.homeTabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        Log.d(SCREEN_TAG, "Going to finder")
                        findNavController(R.id.container).navigate(NavHomeDirections.goToFinder())
                    }

                    1 -> {
                        Log.d(SCREEN_TAG, "Going to directory")
                        findNavController(R.id.container).navigate(NavHomeDirections.goToDirectory())
                    }
                    2 -> {
                        Log.d(SCREEN_TAG, "Going to profile")
                        findNavController(R.id.container).navigate(NavHomeDirections.goToProfile())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    fun logOutIntent() {
        Handler().postDelayed({
            val logoutIntent = Intent(this, LoginActivity::class.java)
            startActivity(logoutIntent)
        }, LOGIN_DELAY)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}