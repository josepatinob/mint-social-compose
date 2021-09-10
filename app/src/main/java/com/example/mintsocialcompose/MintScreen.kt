package com.example.mintsocialcompose

import android.provider.ContactsContract
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import java.lang.IllegalArgumentException

enum class MintScreen(
    val title: String = "",
    val icon: ImageVector? = null,
    val isHomeDestination: Boolean,
    val supportsActionBar: Boolean
) {
    Login(isHomeDestination = false, supportsActionBar = false),
    CreateAccount(isHomeDestination = false, supportsActionBar = false),
    BlogList(
        title = "Blog List",
        icon = Icons.Filled.FormatListBulleted,
        isHomeDestination = true,
        supportsActionBar = true
    ),
    BlogDetail(title = "Blog Detail", isHomeDestination = false, supportsActionBar = true),
    CreateBlog(
        title = "Create Blog",
        icon = Icons.Filled.AddBox,
        isHomeDestination = true,
        supportsActionBar = true
    ),
    Profile(
        title = "Profile",
        icon = Icons.Filled.Person,
        isHomeDestination = true,
        supportsActionBar = true
    );

    companion object {
        fun fromRoute(route: String?): MintScreen =
            when (route?.substringBefore(if (route.contains("?")) "?" else "/")) {
                Login.name -> Login
                CreateAccount.name -> CreateAccount
                BlogList.name -> BlogList
                BlogDetail.name -> BlogDetail
                CreateBlog.name -> CreateBlog
                Profile.name -> Profile
                null -> BlogList
                else -> throw IllegalArgumentException("Route $route is not recognized!")
            }
    }
}