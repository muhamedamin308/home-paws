package com.example.homepaws.di

import com.example.homepaws.data.repo.AnimalRepository
import com.example.homepaws.data.repo.AnimalRepositoryImpl
import com.example.homepaws.data.repo.AuthRepository
import com.example.homepaws.data.repo.AuthRepositoryImpl
import org.koin.dsl.module

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

val repositoryModule = module {
    single<AnimalRepository> {
        AnimalRepositoryImpl(get())
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
}