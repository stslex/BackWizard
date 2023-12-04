package com.stslex.feature.auth.di

import com.stslex.feature.auth.data.AuthRepository
import com.stslex.feature.auth.data.AuthRepositoryImpl
import com.stslex.feature.auth.domain.AuthInteractor
import com.stslex.feature.auth.domain.AuthInteractorImpl
import com.stslex.feature.auth.utils.JwtGenerator
import com.stslex.feature.auth.utils.JwtGeneratorImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    singleOf(::AuthInteractorImpl) { bind<AuthInteractor>() }
    singleOf(::JwtGeneratorImpl) { bind<JwtGenerator>() }
}