package com.ironclad.commonidentityfinder.ui.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ironclad.commonidentityfinder.data.User
import com.ironclad.commonidentityfinder.databinding.FragmentFinderBinding
import com.ironclad.commonidentityfinder.ui.viewmodels.FinderViewModel
import com.theartofdev.edmodo.cropper.CropImage
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

class FinderFragment : Fragment() {

    private var binding: FragmentFinderBinding? = null
    private lateinit var viewModel: FinderViewModel
    private var fileRequestBody: MultipartBody.Part? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFinderBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(FinderViewModel::class.java)

        binding?.btnSelect?.setOnClickListener {
            CropImage.activity().start(requireContext(), this)
        }

        return binding?.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)

            if (resultCode == Activity.RESULT_OK) {
                val imageURI = result.uri
                Log.d("Ishaan", "$imageURI")
                val file = imageURI.toFile()
                val filePart = MultipartBody.Part.createFormData(
                    "img", file.name, RequestBody.create(
                        MediaType.parse("image/jpg"), file
                    )
                )
                fileRequestBody = filePart
                selectImage(imageURI, filePart)
            }
        }
    }

    private fun selectImage(imageURI: Uri?, filePart: MultipartBody.Part) {
        Glide.with(this).load(imageURI).into(binding?.ivSelect!!)
        binding?.btnSubmit?.visibility = View.VISIBLE
        binding?.btnSubmit?.setOnClickListener {
            binding?.apply {
                btnSelect.isClickable = false
                btnSubmit.isClickable = false
            }
            viewModel.getResult(filePart)
            binding?.fragmentView?.alpha = 0.4f
            binding?.progressDialog?.visibility = View.VISIBLE
            viewModel.user.observe({ lifecycle }) {
                Log.d("Ishaan", "Fragment: $it")
                binding?.progressDialog?.visibility = View.GONE
                binding?.fragmentView?.alpha = 1F
                val user = User(
                    it[0].age,
                    it[0].gender,
                    it[0].id,
                    it[0].maritalStatus,
                    it[0].name,
                    it[0].placeOfBirth,
                    it[0].imageUrl
                )
                findNavController().navigate(FinderFragmentDirections.goToDetails(user))
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}