package com.stslex.plugins.auth.di

import com.stslex.plugins.auth.presenter.AuthPluginUtil
import com.stslex.plugins.auth.presenter.AuthPluginUtilImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authPluginModule = module {
    factoryOf(::AuthPluginUtilImpl) { bind<AuthPluginUtil>() }
}