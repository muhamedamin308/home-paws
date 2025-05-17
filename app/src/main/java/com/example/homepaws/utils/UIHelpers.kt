package com.example.homepaws.utils

import android.util.Log

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

fun log(tag: String, message: String?) {
    Log.i(tag, message ?: "null")
}

fun Boolean.convertBooleanToYesNo() =
    if (this) "Yes" else "No"