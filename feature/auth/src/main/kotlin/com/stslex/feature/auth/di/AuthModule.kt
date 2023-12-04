package com.stslex.feature.auth.di

import com.stslex.feature.auth.domain.AuthInteractor
import com.stslex.feature.auth.domain.AuthInteractorImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    singleOf(::AuthInteractorImpl) { bind<AuthInteractor>() }
}