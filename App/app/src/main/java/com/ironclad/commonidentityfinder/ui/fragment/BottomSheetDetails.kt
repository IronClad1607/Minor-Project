package com.ironclad.commonidentityfinder.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
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

        if (user.from?.equals("finder") == true) {
            Glide.with(this).load(user.imageUrl?.toUri()).into(binding.ivSelect)
        } else {
            binding.tvHeading.visibility = View.GONE
            binding.viewUnderline.visibility = View.GONE
            Glide.with(this).load(user.imageUrl).into(binding.ivSelect)
        }

        binding.apply {
            tvName.text = user.name
            tvAge.text = user.age.toString()
            tvGender.text = user.gender
            tvMS.text = user.maritalStatus
            tvPOB.text = user.placeOfBirth
        }
        return binding.root
    }
}