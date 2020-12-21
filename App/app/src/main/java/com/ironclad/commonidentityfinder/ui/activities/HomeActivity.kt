package com.ironclad.commonidentityfinder.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.ironclad.commonidentityfinder.R
import com.ironclad.commonidentityfinder.databinding.ActivityHomeBinding

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
                        findNavController(R.id.container).navigate(R.id.goToFinder)
                    }

                    1 -> {
                        findNavController(R.id.container).navigate(R.id.goToDirectory)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}