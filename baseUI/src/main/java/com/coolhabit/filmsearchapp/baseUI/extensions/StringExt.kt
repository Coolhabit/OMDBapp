package com.coolhabit.filmsearchapp.baseUI.extensions

import java.util.*

fun String.withFirstLetterCapitalized(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}
