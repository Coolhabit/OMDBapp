package com.coolhabit.filmsearchapp.navigation

import com.coolhabit.filmsearchapp.baseUI.extensions.openImdbLink
import com.coolhabit.filmsearchapp.baseUI.model.NavCommand
import com.coolhabit.filmsearchapp.films.presentation.FilmsListFragmentDirections
import com.coolhabit.filmsearchapp.films.presentation.IFilmsRouter

class FilmsRouterImpl : IFilmsRouter {

    override fun navigateToFilmById(movieId: String): NavCommand {
        return NavCommand.Navigate(FilmsListFragmentDirections.openFilmDetails(movieId))
    }

    override fun openImdbLink(link: String): NavCommand = link.openImdbLink()

    override fun openSearchBottom(): NavCommand {
        return NavCommand.Navigate(
            FilmsListFragmentDirections.openSearchBottom(
            )
        )
    }
}
