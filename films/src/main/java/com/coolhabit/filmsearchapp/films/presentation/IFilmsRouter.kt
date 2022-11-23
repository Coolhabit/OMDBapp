package com.coolhabit.filmsearchapp.films.presentation

import com.coolhabit.filmsearchapp.baseUI.model.NavCommand

interface IFilmsRouter {

    fun navigateToFilmById(movieId: String): NavCommand

    fun openImdbLink(link: String): NavCommand

    fun openSearchBottom(): NavCommand
}
