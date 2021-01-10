package com.ironclad.commonidentityfinder.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironclad.api.models.entities.User
import com.ironclad.commonidentityfinder.data.CafRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class FinderViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val user: LiveData<List<User>> = _users

    fun getResult(img: MultipartBody.Part) = viewModelScope.launch {
        Log.d("Ishaan", "Result: Scope Opened")

        withContext(Dispatchers.IO) {
            val user = CafRepo.getResult(img)
            Log.d("Ishaan", "value set")
            _users.postValue(user)
        }
    }
}