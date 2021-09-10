package com.example.mintsocialcompose.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mintsocialcompose.extensions.isValidEmailAddress
import com.example.mintsocialcompose.repository.AmplifyRepository
import com.example.mintsocialcompose.type.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val amplifyRepository: AmplifyRepository
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private var _emailError = MutableLiveData<Boolean>()
    val emailError: LiveData<Boolean> get() = _emailError

    fun onEmailChange(email: String) {
        _emailError.value = !email.isValidEmailAddress()
        _email.value = email
    }

    private var _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    fun onPasswordChange(pwd: String) {
        _password.value = pwd
    }

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status> get() = _status

    fun signIn(
        email: String,
        pwd: String
    ) = viewModelScope.launch {
        _status.value = Status.Loading
        when (amplifyRepository.signIn(email, pwd)) {
            AmplifyRepository.AuthStatus.VALID -> _status.value = Status.Success
            else -> _status.value = Status.Error
//        AmplifyRepository.AuthStatus.NETWORK_ERROR -> _toast.postValue(R.string.login_network_error)
//        AmplifyRepository.AuthStatus.WRONG_PASSWORD -> _toast.postValue(R.string.login_incorrect_password)
//        AmplifyRepository.AuthStatus.UNKNOWN_ACCOUNT -> _showRegisterAlert.call()
        }
    }
}