package com.ironclad.commonidentityfinder.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ironclad.commonidentityfinder.databinding.FragmentDirectoryBinding
import com.ironclad.commonidentityfinder.ui.viewmodels.DirectoryViewModel

class DirectoryFragment : Fragment() {
    private var binding: FragmentDirectoryBinding? = null
    private lateinit var viewModel: DirectoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO create layout file
        //TODO create adapter
        binding = FragmentDirectoryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DirectoryViewModel::class.java)
        viewModel.getAllUsers()
        viewModel.allUser.observe({lifecycle}){
            Log.d("Ishaan","$it")
        }
        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}