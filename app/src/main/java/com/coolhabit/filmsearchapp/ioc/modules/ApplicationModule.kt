package com.coolhabit.filmsearchapp.ioc.modules

import android.content.Context
import com.coolhabit.filmsearchapp.FilmsSearchApp
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideContext(app: FilmsSearchApp): Context = app.applicationContext
}
