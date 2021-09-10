package com.example.mintsocialcompose

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.kotlin.core.Amplify
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MintSocialApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            // Add this line, to include the Auth plugin.
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i("MintSocialApplication", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MintSocialApplication", "Could not initialize Amplify", error)
        }
    }
}