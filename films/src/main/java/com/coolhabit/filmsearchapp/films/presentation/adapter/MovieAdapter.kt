package com.coolhabit.filmsearchapp.films.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.coolhabit.filmsearchapp.domain.entities.Movie
import com.coolhabit.filmsearchapp.films.databinding.RvFilmListItemBinding
import javax.inject.Inject

class MovieAdapter @Inject constructor() : ListAdapter<Movie, MovieViewHolder>(MovieDiffUtils()) {

    var onCardClick: (String) -> Unit = {}
    var onFavClick: (Movie) -> Unit = {}
    var onCommentClick: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvFilmListItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding, onCommentClick, onFavClick) { position ->
            onCardClick(getItem(position).imdbId)
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieDiffUtils : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.imdbId == newItem.imdbId
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
