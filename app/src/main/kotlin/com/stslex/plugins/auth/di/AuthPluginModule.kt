package com.stslex.plugins.auth.di

import com.stslex.plugins.auth.presenter.AuthPluginPresenter
import com.stslex.plugins.auth.presenter.AuthPluginPresenterImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authPluginModule = module {
    factoryOf(::AuthPluginPresenterImpl) { bind<AuthPluginPresenter>() }
}