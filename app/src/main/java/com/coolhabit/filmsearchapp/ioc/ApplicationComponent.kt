package com.coolhabit.filmsearchapp.ioc

import com.coolhabit.filmsearchapp.FilmsSearchApp
import com.coolhabit.filmsearchapp.data.ioc.RemoteModule
import com.coolhabit.filmsearchapp.ioc.modules.ActivityModule
import com.coolhabit.filmsearchapp.ioc.modules.ApiModule
import com.coolhabit.filmsearchapp.ioc.modules.ApplicationModule
import com.coolhabit.filmsearchapp.ioc.modules.FragmentsModule
import com.coolhabit.filmsearchapp.ioc.modules.NavigationRoutersModule
import com.coolhabit.filmsearchapp.ioc.modules.StoragesModule
import com.coolhabit.filmsearchapp.ioc.modules.UseCasesModule
import com.coolhabit.filmsearchapp.ioc.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        ApplicationModule::class,
        FragmentsModule::class,
        UseCasesModule::class,
        NavigationRoutersModule::class,
        ApiModule::class,
        RemoteModule::class,
        StoragesModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<FilmsSearchApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: FilmsSearchApp): ApplicationComponent
    }
}
