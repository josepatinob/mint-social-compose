package com.example.mintsocialcompose.extensions

fun String.containsNumbers(): Boolean = matches(".*\\d.*".toRegex())
fun String.isValidEmailAddress(): Boolean =
    isNotEmpty() && matches("^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$".toRegex())

fun String.containsUpperCaseChar(): Boolean = any { it.isUpperCase() }