package com.coolhabit.filmsearchapp.ioc.modules

import android.content.Context
import com.coolhabit.filmsearchapp.data.db.DatabaseStorageImpl
import com.coolhabit.filmsearchapp.domain.api.IDatabaseStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StoragesModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): IDatabaseStorage = DatabaseStorageImpl(context)
}
