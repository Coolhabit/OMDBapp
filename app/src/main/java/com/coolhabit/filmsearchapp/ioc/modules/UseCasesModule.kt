package com.coolhabit.filmsearchapp.ioc.modules

import com.coolhabit.filmsearchapp.domain.api.IDatabaseStorage
import com.coolhabit.filmsearchapp.domain.api.IMoviesApiService
import com.coolhabit.filmsearchapp.domain.usecases.MovieUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Provides
    @Singleton
    fun provideMoviesUseCase(
        api: IMoviesApiService,
        database: IDatabaseStorage,
    ) = MovieUseCase(api, database)
}
