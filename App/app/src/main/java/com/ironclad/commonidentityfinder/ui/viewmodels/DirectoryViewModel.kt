package com.ironclad.commonidentityfinder.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.ironclad.api.models.entities.User
import com.ironclad.api.models.response.AllUserResponse
import com.ironclad.commonidentityfinder.data.CafRepo
import kotlinx.coroutines.launch

class DirectoryViewModel : ViewModel() {

    private val _allUsers = MutableLiveData<List<User>>()
    val allUser :LiveData<List<User>> = _allUsers

    fun getAllUsers() = viewModelScope.launch {
        Log.d("Ishaan","Scope opened")

        val users = listOf(
            User(1, "M", 3, "N", "Ishaan", "Delhi"),
            User(1, "M", 3, "N", "Ishaan", "Delhi"),
            User(1, "M", 3, "N", "Ishaan", "Delhi")
        )
        Log.d("Ishaan","users list = $users")

        _allUsers.postValue(users)
    }
}