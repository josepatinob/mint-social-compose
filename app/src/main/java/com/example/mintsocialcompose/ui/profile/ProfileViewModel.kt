package com.example.mintsocialcompose.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mintsocialcompose.model.Blog
import com.example.mintsocialcompose.repository.AmplifyRepository
import com.example.mintsocialcompose.repository.BlogRepository
import com.example.mintsocialcompose.type.BlogFilter
import com.example.mintsocialcompose.type.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val amplifyRepository: AmplifyRepository,
    val blogRepository: BlogRepository
) : ViewModel() {
    private var _blogList = MutableLiveData<List<Blog>>()
    val blogList: LiveData<List<Blog>> get() = _blogList

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status> get() = _status

    private var _isLoggedInUserProfile = MutableLiveData<Boolean>()
    val isLoggedInUserProfile: LiveData<Boolean> get() = _isLoggedInUserProfile

    // check if user is logged in
    private var _isSignedIn = MutableLiveData<Boolean>()
    val isSignedIn: LiveData<Boolean> get() = _isSignedIn

    private fun checkUserAuthStatus() = viewModelScope.launch {
        _isSignedIn.value = amplifyRepository.isSignedIn()
    }

    init {
        checkUserAuthStatus()
    }

    fun getBlogs(authorId: String?) = viewModelScope.launch {
        try {
            _blogList.value = blogRepository.getBlogs(authorId, BlogFilter.Mine.filter)
            _status.value = Status.Success
        } catch (e: Exception) {
            _status.value = Status.Error
            Log.d("ProfileViewModel", e.message.toString())
        }
    }

    fun checkForMyProfile(authorId: String?) {
        _isLoggedInUserProfile.value = if (authorId.isNullOrEmpty()) {
            loggedInUserEmail() != null
        } else {
            amplifyRepository.getCurrentUser()?.userId == authorId
        }
    }

    fun loggedInUserEmail() = amplifyRepository.getCurrentUser()?.username
    fun loggedInUserId() = amplifyRepository.getCurrentUser()?.userId

    fun signOut() = viewModelScope.launch {
        try {
            _status.value = Status.Loading
            amplifyRepository.signOut()
            _status.value = Status.Success
        } catch (e: Exception) {
            _status.value = Status.Error
            Log.d("ProfileViewModel", e.message.toString())
        }
    }
}