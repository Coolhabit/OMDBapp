package com.coolhabit.filmsearchapp.films.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.coolhabit.filmsearchapp.baseUI.extensions.booleanCheck
import com.coolhabit.filmsearchapp.baseUI.extensions.load
import com.coolhabit.filmsearchapp.baseUI.extensions.withFirstLetterCapitalized
import com.coolhabit.filmsearchapp.baseUI.presentation.BaseFragment
import com.coolhabit.filmsearchapp.baseUI.presentation.BaseViewModel
import com.coolhabit.filmsearchapp.films.R
import com.coolhabit.filmsearchapp.films.databinding.FragmentFilmDetailsBinding
import com.coolhabit.filmsearchapp.films.databinding.RvMessageBinding
import com.coolhabit.filmsearchapp.films.presentation.FilmsListFragment.Companion.CONTENT_CHANGED_KEY

class FilmDetailsFragment : BaseFragment(R.layout.fragment_film_details) {

    private lateinit var binding: FragmentFilmDetailsBinding
    private val viewModel by viewModels<FilmDetailsViewModel>()
    private val args by navArgs<FilmDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initContent(args.movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
    }

    override fun withViewModel(): BaseViewModel = viewModel.apply {
        loadMovie.collectWithState { state ->
            state.isSuccessful { movie ->
                with(binding.movieInfo) {
                    addMessage(movie.commentsList, binding.messages)
                    filmName.text = movie.name
                    filmPoster.load(movie.poster)
                    filmType.text = movie.type.withFirstLetterCapitalized()
                    filmYear.text = movie.year
                    likeBtn.booleanCheck(movie.isFavorite, R.drawable.ic_liked, R.drawable.ic_like)

                    likeBtn.setOnClickListener {
                        setFragmentResult(
                            CONTENT_CHANGED_KEY,
                            Bundle()
                        )
                        viewModel.changeFavStatus(movie)
                    }

                    commentBtn.setOnClickListener {
                        viewModel.openImdbLink(
                            requireContext().resources.getString(
                                R.string.imdb_link_base,
                                movie.imdbId
                            )
                        )
                    }

                    binding.commentField.commentBtn.setOnClickListener {
                        val comment = binding.commentField.baseText.text
                        if (comment?.isNotBlank() == true) {
                            setFragmentResult(
                                CONTENT_CHANGED_KEY,
                                Bundle()
                            )
                            viewModel.addComment(comment.toString(), movie.commentsList, movie.imdbId)
                            comment.clear()
                        }
                    }
                }
            }
            binding.progressBar.isVisible = state.isLoading
        }
    }

    private fun addMessage(list: List<String>, container: LinearLayout) {
        container.removeAllViews()
        val inflater = LayoutInflater.from(container.context)
        for (i in list) {
            val newMessage = RvMessageBinding.inflate(inflater, container, false)
            newMessage.message.text = i
            container.addView(newMessage.root)
        }
    }
}
