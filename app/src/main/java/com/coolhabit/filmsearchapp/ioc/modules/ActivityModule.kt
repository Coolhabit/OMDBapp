package com.coolhabit.filmsearchapp.ioc.modules

import com.coolhabit.filmsearchapp.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun mainActivity(): MainActivity
}
