package com.coolhabit.filmsearchapp.ioc.modules

import com.coolhabit.filmsearchapp.films.presentation.FilmsListFragment
import com.coolhabit.filmsearchapp.films.presentation.details.FilmDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class, ActivityModule::class])
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun provideFilmsListFragment(): FilmsListFragment

    @ContributesAndroidInjector
    abstract fun provideFilmDetailsFragment(): FilmDetailsFragment
}
