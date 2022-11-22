package com.coolhabit.filmsearchapp.ioc.modules

import com.coolhabit.filmsearchapp.data.network.FilmsApi
import com.coolhabit.filmsearchapp.data.network.services.FilmsApiService
import com.coolhabit.filmsearchapp.domain.api.IMoviesApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideMoviesApiService(api: FilmsApi): IMoviesApiService = FilmsApiService(api)
}
