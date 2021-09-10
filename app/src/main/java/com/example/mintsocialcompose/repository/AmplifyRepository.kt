package com.example.mintsocialcompose.repository

import android.util.Log
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUser
import com.amplifyframework.auth.AuthUserAttribute
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.kotlin.core.Amplify
import java.lang.Exception
import javax.inject.Inject

class AmplifyRepository @Inject constructor() {

    companion object {
        const val AUTH_TAG = "AmplifyRepository"
    }

    enum class AuthStatus {
        VALID,
        WRONG_PASSWORD,
        UNKNOWN_ACCOUNT,
        NETWORK_ERROR,
        SIGN_UP_SUCCESS,
        USER_ALREADY_EXISTS
    }

    suspend fun isSignedIn() = Amplify.Auth.fetchAuthSession().isSignedIn

    suspend fun getAmplifySession() {
        try {
            val session = Amplify.Auth.fetchAuthSession()
            Log.i("AmplifyQuickstart", "Auth session = $session")
        } catch (error: AuthException) {
            Log.e("AmplifyQuickstart", "Failed to fetch auth session", error)
        }
    }

    fun getCurrentUser(): AuthUser? {
        return try {
            Amplify.Auth.getCurrentUser()
        } catch (e: Exception) {
            Log.e(AUTH_TAG, e.message.toString())
            null
        }
    }

    suspend fun signOut() {
        try {
            Amplify.Auth.signOut()
        } catch (e: Exception) {
            Log.e(AUTH_TAG, e.message.toString())
        }
    }

    suspend fun signIn(user: String, pwd: String): AuthStatus {
        try {
            val result = Amplify.Auth.signIn(user, pwd)
            setAttributeEmail(user)
            return if (result.isSignInComplete) {
                Log.i(AUTH_TAG, "Sign in succeeded")
                AuthStatus.VALID
            } else {
                Log.e(AUTH_TAG, "Sign in not complete")
                AuthStatus.NETWORK_ERROR
            }
        } catch (error: AuthException.UserNotFoundException) {
            Log.e(AUTH_TAG, "Sign in failed UserNotFoundException", error)
            return AuthStatus.UNKNOWN_ACCOUNT
        } catch (error: AuthException) {
            Log.e(AUTH_TAG, "Sign in failed AuthException", error)
            return AuthStatus.WRONG_PASSWORD
        }
    }

    suspend fun getAttributeEmail(): String? {
//        val attributes = Amplify.Auth.fetchUserAttributes()
//        for (attribute in attributes) {
//            if (attribute.key == AuthUserAttributeKey.email()) {
//                return attribute.value
//            }
//        }
//        return null
        return Amplify.Auth.fetchUserAttributes().find { it.key == AuthUserAttributeKey.email() }
            ?.value
    }

    suspend fun setAttributeEmail(email: String) {
        Amplify.Auth.updateUserAttribute(AuthUserAttribute(AuthUserAttributeKey.email(), email))
    }

    suspend fun signUp(username: String, pwd: String): AuthStatus {
        return try {
            val result = Amplify.Auth.signUp(username, pwd)
            if (result.isSignUpComplete) {
                Log.i(AUTH_TAG, "Signup succeeded")
                AuthStatus.SIGN_UP_SUCCESS
            } else {
                Log.i(AUTH_TAG, "Sign up not complete")
                AuthStatus.NETWORK_ERROR
            }
        } catch (e: AuthException.UsernameExistsException) {
            Log.e(AUTH_TAG, e.message, e)
            AuthStatus.USER_ALREADY_EXISTS
        } catch (e: Exception) {
            Log.e(AUTH_TAG, e.message, e)
            AuthStatus.NETWORK_ERROR
        }
    }


}