package com.ironclad.commonidentityfinder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ironclad.commonidentityfinder.databinding.FragmentDirectoryBinding

class DirectoryFragment : Fragment() {
    private var binding: FragmentDirectoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDirectoryBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}