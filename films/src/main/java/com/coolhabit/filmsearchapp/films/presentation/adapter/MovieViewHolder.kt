package com.coolhabit.filmsearchapp.films.presentation.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coolhabit.filmsearchapp.baseUI.extensions.booleanCheck
import com.coolhabit.filmsearchapp.baseUI.extensions.load
import com.coolhabit.filmsearchapp.baseUI.extensions.withFirstLetterCapitalized
import com.coolhabit.filmsearchapp.domain.entities.Movie
import com.coolhabit.filmsearchapp.films.R
import com.coolhabit.filmsearchapp.films.databinding.RvFilmListItemBinding

class MovieViewHolder(
    private val binding: RvFilmListItemBinding,
    private val onCommentClick: (String) -> Unit,
    private val onFavClick: (Movie) -> Unit,
    private val onCardClick: (Int) -> Unit,
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onCardClick.invoke(bindingAdapterPosition)
        }
    }

    fun bind(item: Movie) {
        with(binding) {
            filmPoster.load(item.poster)
            filmName.text = item.name
            filmType.text = item.type.withFirstLetterCapitalized()
            filmYear.text = item.year
            likeBtn.booleanCheck(item.isFavorite, R.drawable.ic_liked, R.drawable.ic_like)
            commentsCheck(item.commentsList, commentsCounter)
            commentBtn.setOnClickListener {
                onCommentClick.invoke(item.imdbId)
            }
            likeBtn.setOnClickListener {
                onFavClick.invoke(item)
            }
        }
    }

    private fun commentsCheck(list: List<String>, textView: TextView) {
        if (list.isNotEmpty()) {
            textView.text = list.size.toString()
        }
    }
}
