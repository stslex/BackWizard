package com.stslex.core.database.di

import com.stslex.core.database.sources.user.source.UserDatabaseSource
import com.stslex.core.database.sources.user.source.UserDatabaseSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::UserDatabaseSourceImpl) { bind<UserDatabaseSource>() }
}