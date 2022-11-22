package com.coolhabit.filmsearchapp

import com.coolhabit.filmsearchapp.ioc.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class FilmsSearchApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
            .factory()
            .create(this)
    }
}
