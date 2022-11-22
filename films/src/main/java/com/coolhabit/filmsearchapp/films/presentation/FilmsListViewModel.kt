package com.coolhabit.filmsearchapp.films.presentation

import androidx.lifecycle.viewModelScope
import com.coolhabit.filmsearchapp.baseUI.model.StatefulData
import com.coolhabit.filmsearchapp.baseUI.presentation.BaseViewModel
import com.coolhabit.filmsearchapp.domain.entities.Movie
import com.coolhabit.filmsearchapp.domain.usecases.MovieUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmsListViewModel @Inject constructor(
    private val useCase: MovieUseCase,
    private val router: IFilmsRouter,
) : BaseViewModel() {

    private val _loadMovies = statefulSharedFlow<List<Movie>?>()
    val loadMovies: Flow<StatefulData<List<Movie>?>>
        get() = _loadMovies

    private var savedQuery: String? = null

    fun initContent(query: String?) {
        _loadMovies.fetch {
            savedQuery = query
            useCase.loadMoviesList(query)
        }
    }

    fun performSearch(query: String?) {
        _loadMovies.fetch {
            useCase.loadMoviesList(query)
        }
    }

    fun navigateToDetails(movieId: String) {
        navigateTo(router.navigateToFilmById(movieId))
    }

    fun changeFavStatus(movie: Movie) {
        viewModelScope.launch {
            if (movie.isFavorite) {
                useCase.removeMovieFromFav(movie)
                initContent(savedQuery)
            } else {
                useCase.addMovieToFav(movie)
                initContent(savedQuery)
            }
        }
    }
}
