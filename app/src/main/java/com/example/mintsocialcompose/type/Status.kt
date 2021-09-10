package com.example.mintsocialcompose.type

sealed class Status {
    object Success : Status()
    object Error : Status()
    object Loading : Status()
    object AlreadyRegistered : Status()
    object Empty : Status()
}