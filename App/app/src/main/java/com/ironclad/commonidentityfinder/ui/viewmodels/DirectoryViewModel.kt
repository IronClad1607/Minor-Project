package com.ironclad.commonidentityfinder.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.ironclad.api.models.entities.User
import com.ironclad.api.models.response.AllUserResponse
import com.ironclad.commonidentityfinder.data.CafRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DirectoryViewModel : ViewModel() {

    private val _allUsers = MutableLiveData<List<User>>()
    val allUser: LiveData<List<User>> = _allUsers

    fun getAllUsers() = viewModelScope.launch {
        Log.d("Ishaan", "Scope opened")

        withContext(Dispatchers.IO) {
            val users = CafRepo.getAllUsers()
            _allUsers.postValue(users)
        }
    }
}