package com.coolhabit.filmsearchapp.films.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.coolhabit.filmsearchapp.baseUI.adapter.ItemDecoration
import com.coolhabit.filmsearchapp.baseUI.extensions.parseError
import com.coolhabit.filmsearchapp.baseUI.presentation.BaseFragment
import com.coolhabit.filmsearchapp.baseUI.presentation.BaseViewModel
import com.coolhabit.filmsearchapp.films.R
import com.coolhabit.filmsearchapp.films.databinding.FragmentFilmsListBinding
import com.coolhabit.filmsearchapp.films.presentation.adapter.MovieAdapter
import javax.inject.Inject

class FilmsListFragment : BaseFragment(R.layout.fragment_films_list) {

    companion object {
        const val INITIAL_SEARCH = "Sherlock"
        const val FAV_REQUEST_KEY = "fav_request_key"
        const val SEARCH_REQUEST_KEY = "search_request_key"
        const val SEARCH_NAME_DATA_KEY = "search_name_data_key"
        const val SEARCH_PAGES_DATA_KEY = "search_pages_data_key"
    }

    private lateinit var binding: FragmentFilmsListBinding
    private val viewModel by viewModels<FilmsListViewModel>()

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initContent(INITIAL_SEARCH, null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.isVisible = false
        setFragmentResultListener(FAV_REQUEST_KEY) { _, _ ->
            viewModel.initContent(INITIAL_SEARCH, null)
        }
        setFragmentResultListener(SEARCH_REQUEST_KEY) { _, bundle ->
            val nameContent = bundle.getString(SEARCH_NAME_DATA_KEY)
            val pagesContent = bundle.getInt(SEARCH_PAGES_DATA_KEY)
            viewModel.initContent(nameContent, pagesContent)
        }

        with(binding) {
            rvFilms.apply {
                adapter = movieAdapter
                itemAnimator = null
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                addItemDecoration(
                    ItemDecoration(
                        context,
                        com.coolhabit.filmsearchapp.baseUI.R.dimen.size_6,
                        com.coolhabit.filmsearchapp.baseUI.R.dimen.size_6,
                        com.coolhabit.filmsearchapp.baseUI.R.dimen.size_10,
                        com.coolhabit.filmsearchapp.baseUI.R.dimen.size_6,
                    )
                )
            }

            toolbar.setNavigationOnClickListener {
                viewModel.openSearchBottom()
            }

            movieAdapter.onCardClick = { id ->
                viewModel.navigateToDetails(id)
            }
            movieAdapter.onFavClick = {
                viewModel.changeFavStatus(it)
            }
            movieAdapter.onCommentClick = {
                Toast.makeText(
                    requireContext(),
                    requireContext().resources.getString(R.string.go_to_comments),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun withViewModel(): BaseViewModel = viewModel.apply {
        loadMovies.collectWithState { state ->
            state.isSuccessful { list ->
                movieAdapter.submitList(list)
            }
            state.isError { error ->
                Toast.makeText(
                    requireContext(),
                    error.parseError(requireContext()),
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.progressBar.isVisible = state.isLoading
        }
    }
}
