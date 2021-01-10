package com.ironclad.commonidentityfinder.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ironclad.commonidentityfinder.databinding.BottomSheetDetailsBinding

class BottomSheetDetails : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetDetailsBinding
    private val args: BottomSheetDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDetailsBinding.inflate(inflater, container, false)
        val user = args.user
        Log.d("Ishaan", "$user")
        return binding.root
    }
}