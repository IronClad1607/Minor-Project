package com.ironclad.commonidentityfinder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ironclad.commonidentityfinder.databinding.FragmentFinderBinding

class FinderFragment : Fragment() {

    private var binding: FragmentFinderBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFinderBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}