package com.codandotv.streamplayerapp.di

import org.koin.core.context.startKoin

class KoinIosHelper {
    fun initKoin() {
        startKoin {
            modules(AppModule.list)
        }
    }
}