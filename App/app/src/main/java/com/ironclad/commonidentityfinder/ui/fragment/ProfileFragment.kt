package com.ironclad.commonidentityfinder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ironclad.commonidentityfinder.databinding.FragmentProfileBinding
import com.ironclad.commonidentityfinder.ui.activities.HomeActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    private val currentUser by lazy {
        Firebase.auth.currentUser
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        auth = Firebase.auth

        binding.tvLogin.text = currentUser?.email

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            Toast.makeText(context, "Logged out Successful", Toast.LENGTH_LONG).show()
            (activity as HomeActivity).logOutIntent()
        }

        return binding.root
    }
}