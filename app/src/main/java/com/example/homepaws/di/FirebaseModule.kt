package com.example.homepaws.di

import com.example.homepaws.data.service.firebase.AuthenticationService
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

/**
 * @author Muhamed Amin Hassan on 14,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
    single { AuthenticationService(get()) }
}