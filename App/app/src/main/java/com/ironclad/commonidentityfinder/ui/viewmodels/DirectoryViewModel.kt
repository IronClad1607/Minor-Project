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

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun getAllUsers() = viewModelScope.launch {
        Log.d("Ishaan", "All User: Scope opened")

        withContext(Dispatchers.IO) {
            val users = CafRepo.getAllUsers()
            Log.d("Ishaan", "value set")
            _allUsers.postValue(users)
        }
    }

    fun getParticularUser(id: Int) = viewModelScope.launch {
        Log.d("Ishaan", "Particular: Scope opened")

        withContext(Dispatchers.IO) {
            val user = CafRepo.getUserById(id)
            Log.d("Ishaan", "value set")
            _user.postValue(user)
        }
    }
}