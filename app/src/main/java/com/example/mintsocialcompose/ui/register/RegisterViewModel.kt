package com.example.mintsocialcompose.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.core.Amplify
import com.example.mintsocialcompose.extensions.containsNumbers
import com.example.mintsocialcompose.extensions.containsUpperCaseChar
import com.example.mintsocialcompose.extensions.isValidEmailAddress
import com.example.mintsocialcompose.repository.AmplifyRepository
import com.example.mintsocialcompose.type.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val amplifyRepository: AmplifyRepository
) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private var _emailError = MutableLiveData<Boolean>()
    val emailError: LiveData<Boolean> get() = _emailError

    fun onEmailChange(email: String) {
        _emailError.value = !email.isValidEmailAddress()
        _email.value = email
        validateEmailAndPassword()
    }

    private var _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private var _passwordError = MutableLiveData<Boolean>()
    val passwordError: LiveData<Boolean> get() = _passwordError

    fun onPasswordChange(pwd: String) {
        _passwordError.value = !validatePassword(pwd)
        _confirmPasswordError.value = !checkPasswordsMatch(pwd, _confirmPassword.value ?: "")
        _password.value = pwd
        validateEmailAndPassword()
    }

    private fun validatePassword(pwd: String): Boolean {
        return pwd.length > 7 && pwd.containsNumbers() && pwd.containsUpperCaseChar()
    }

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: LiveData<String> get() = _confirmPassword

    private val _confirmPasswordError = MutableLiveData<Boolean>()
    val confirmPasswordError: LiveData<Boolean> get() = _confirmPasswordError

    fun onConfirmPasswordChange(pwd: String) {
        _confirmPasswordError.value = !checkPasswordsMatch(pwd, _password.value ?: "")
        _confirmPassword.value = pwd
        validateEmailAndPassword()
    }

    private fun checkPasswordsMatch(pwd1: String, pwd2: String): Boolean {
        return pwd1.isNotEmpty() && pwd2.isNotEmpty() && (pwd1 == pwd2)
    }

    private var _allCriteriaMet = MutableLiveData(false)
    val allCriteriaMet: LiveData<Boolean> get() = _allCriteriaMet

    private fun validateEmailAndPassword() {
        _allCriteriaMet.value =
            validatePassword(_password.value ?: "") && _email.value.toString()
                .isValidEmailAddress() && _confirmPassword.value.toString() == _password.value.toString()
    }

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status> get() = _status

    fun signUp(email: String, pwd: String) = viewModelScope.launch {
        _status.value = Status.Loading
        when (amplifyRepository.signUp(email, pwd)) {
            AmplifyRepository.AuthStatus.SIGN_UP_SUCCESS -> _status.value = Status.Success
            AmplifyRepository.AuthStatus.USER_ALREADY_EXISTS -> {
                _status.value = Status.AlreadyRegistered
            }
            else -> _status.value = Status.Error
        }
    }
}