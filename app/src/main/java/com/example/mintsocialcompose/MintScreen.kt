package com.example.mintsocialcompose

import java.lang.IllegalArgumentException

enum class MintScreen {
    Login,
    CreateAccount,
    BlogList,
    BlogDetail,
    CreateBlog,
    Profile;

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