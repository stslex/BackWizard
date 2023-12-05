package com.stslex.feature.user.di

import com.stslex.feature.user.data.repository.UserRepository
import com.stslex.feature.user.data.repository.UserRepositoryImpl
import com.stslex.feature.user.domain.UserInteractor
import com.stslex.feature.user.domain.UsernameInteractorImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val userModule = module {
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::UsernameInteractorImpl) { bind<UserInteractor>() }
}