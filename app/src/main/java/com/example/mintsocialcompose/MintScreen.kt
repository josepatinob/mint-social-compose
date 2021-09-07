package com.example.mintsocialcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import java.lang.IllegalArgumentException

enum class MintScreen(
    val title: String = "",
    val icon: ImageVector? = null,
    val homeDestination: Boolean,
    val hasAppBar: Boolean
) {
    Login(homeDestination = false, hasAppBar = false),
    CreateAccount(homeDestination = false, hasAppBar = false),
    BlogList(
        title = "Blog List",
        icon = Icons.Filled.FormatListBulleted,
        homeDestination = true,
        hasAppBar = true
    ),
    BlogDetail(title = "Blog Detail", homeDestination = false, hasAppBar = true),
    CreateBlog(
        title = "Create Blog",
        icon = Icons.Filled.AddBox,
        homeDestination = true,
        hasAppBar = true
    ),
    Profile(
        title = "Profile",
        icon = Icons.Filled.Person,
        homeDestination = true,
        hasAppBar = true
    );

    companion object {
        fun fromRoute(route: String?): MintScreen =
            when (route?.substringBefore("/")) {
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