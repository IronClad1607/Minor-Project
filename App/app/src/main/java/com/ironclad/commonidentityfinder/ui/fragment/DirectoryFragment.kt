package com.ironclad.commonidentityfinder.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironclad.commonidentityfinder.data.User
import com.ironclad.commonidentityfinder.databinding.FragmentDirectoryBinding
import com.ironclad.commonidentityfinder.ui.adapter.DirectoryAdapter
import com.ironclad.commonidentityfinder.ui.viewmodels.DirectoryViewModel

class DirectoryFragment : Fragment() {
    private var binding: FragmentDirectoryBinding? = null
    private lateinit var viewModel: DirectoryViewModel
    private lateinit var mAdapter: DirectoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDirectoryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DirectoryViewModel::class.java)
        mAdapter = DirectoryAdapter { user: User -> userClicked(user) }

        binding?.rvDirectory?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        viewModel.getAllUsers()
        viewModel.allUser.observe({ lifecycle }) {
            Log.d("Ishaan", "$it")
            mAdapter.submitList(it)
        }

        return binding?.root
    }

    private fun userClicked(user: User) {
        findNavController().navigate(DirectoryFragmentDirections.goToDetailsDirectory(user))
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}