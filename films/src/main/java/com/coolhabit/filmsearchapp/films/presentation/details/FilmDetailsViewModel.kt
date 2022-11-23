package com.coolhabit.filmsearchapp.films.presentation.details

import androidx.lifecycle.viewModelScope
import com.coolhabit.filmsearchapp.baseUI.model.StatefulData
import com.coolhabit.filmsearchapp.baseUI.presentation.BaseViewModel
import com.coolhabit.filmsearchapp.domain.entities.Movie
import com.coolhabit.filmsearchapp.domain.usecases.MovieUseCase
import com.coolhabit.filmsearchapp.films.presentation.IFilmsRouter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmDetailsViewModel @Inject constructor(
    private val useCase: MovieUseCase,
    private val router: IFilmsRouter,
) : BaseViewModel() {

    private val _loadMovie = statefulSharedFlow<Movie>()
    val loadMovie: Flow<StatefulData<Movie>>
        get() = _loadMovie

    var savedId: String? = null

    fun initContent(movieId: String?) {
        _loadMovie.fetch {
            savedId = movieId
            useCase.getMovieById(movieId)
        }
    }

    fun changeFavStatus(movie: Movie) {
        viewModelScope.launch {
            if (movie.isFavorite) {
                useCase.removeMovieFromFav(movie)
                initContent(savedId)
            } else {
                useCase.addMovieToFav(movie)
                initContent(savedId)
            }
        }
    }

    fun openImdbLink(link: String) {
        navigateTo(router.openImdbLink(link))
    }
}
