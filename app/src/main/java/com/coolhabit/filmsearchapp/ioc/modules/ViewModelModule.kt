package com.coolhabit.filmsearchapp.ioc.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coolhabit.filmsearchapp.films.presentation.FilmsListViewModel
import com.coolhabit.filmsearchapp.films.presentation.bottomSearch.SearchBottomSheetViewModel
import com.coolhabit.filmsearchapp.films.presentation.details.FilmDetailsViewModel
import com.coolhabit.filmsearchapp.ioc.utils.ViewModelFactory
import com.coolhabit.filmsearchapp.ioc.utils.ViewModelKey
import com.coolhabit.filmsearchapp.presentation.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun mainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilmsListViewModel::class)
    abstract fun filmsListViewModel(viewModel: FilmsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilmDetailsViewModel::class)
    abstract fun filmDetailsViewModel(viewModel: FilmDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchBottomSheetViewModel::class)
    abstract fun searchBottomViewModel(viewModel: SearchBottomSheetViewModel): ViewModel
}
