package com.example.mintsocialcompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mintsocialcompose.repository.AmplifyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val amplifyRepository: AmplifyRepository
) : ViewModel() {

    // check if user is logged in
    private var _isSignedIn = MutableLiveData<Boolean>()
    val isSignedIn: LiveData<Boolean> get() = _isSignedIn

    fun checkUserAuthStatus() = viewModelScope.launch {
        _isSignedIn.value = amplifyRepository.isSignedIn()
    }

    init {
        checkUserAuthStatus()
    }
}