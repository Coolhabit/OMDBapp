package com.coolhabit.filmsearchapp.baseUI.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.coolhabit.filmsearchapp.baseUI.R
import com.coolhabit.filmsearchapp.baseUI.model.NavCommand
import java.util.*

const val NULL_SEARCH_ERROR = "Parameter specified as non-null is null: method kotlin.jvm.internal.Intrinsics.checkNotNullParameter, parameter <this>"
const val PAGE_REGEX = "([1-9]|[1-9][0-9]|100)"

fun String.withFirstLetterCapitalized(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun String.openImdbLink(): NavCommand {
    return NavCommand
        .Intent(
            Intent(Intent.ACTION_VIEW).let {
                it.data = Uri.parse(this)
                it
            }
        )
}

fun String.parseError(context: Context): String {
    return when (this) {
        NULL_SEARCH_ERROR -> context.resources.getString(R.string.null_search_error)
        else -> context.resources.getString(R.string.other_error)
    }
}
