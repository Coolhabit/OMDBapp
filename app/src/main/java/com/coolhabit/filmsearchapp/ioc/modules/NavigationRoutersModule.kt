package com.coolhabit.filmsearchapp.ioc.modules

import com.coolhabit.filmsearchapp.films.presentation.IFilmsRouter
import com.coolhabit.filmsearchapp.navigation.FilmsRouterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationRoutersModule {

    @Provides
    @Singleton
    fun provideFilmsRouter(): IFilmsRouter = FilmsRouterImpl()
}
