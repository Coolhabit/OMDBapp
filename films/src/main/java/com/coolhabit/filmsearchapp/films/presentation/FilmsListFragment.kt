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
import com.coolhabit.filmsearchapp.baseUI.presentation.BaseFragment
import com.coolhabit.filmsearchapp.baseUI.presentation.BaseViewModel
import com.coolhabit.filmsearchapp.films.R
import com.coolhabit.filmsearchapp.films.databinding.FragmentFilmsListBinding
import com.coolhabit.filmsearchapp.films.presentation.adapter.MovieAdapter
import javax.inject.Inject

class FilmsListFragment : BaseFragment(R.layout.fragment_films_list) {

    companion object {
        const val FAV_REQUEST_KEY = "fav_request_key"
        const val FAV_DATA_KEY = "fav_data_key"
    }

    private lateinit var binding: FragmentFilmsListBinding
    private val viewModel by viewModels<FilmsListViewModel>()

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initContent("Sherlock")
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
        setFragmentResultListener(FAV_REQUEST_KEY) { _, _ ->
            viewModel.initContent("Sherlock")
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
                Toast.makeText(requireContext(), "Search", Toast.LENGTH_SHORT).show()
            }

            movieAdapter.onCardClick = { id ->
                viewModel.navigateToDetails(id)
            }
            movieAdapter.onFavClick = {
                viewModel.changeFavStatus(it)
            }
            movieAdapter.onCommentClick = {
                Toast.makeText(requireContext(), "to comments", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun withViewModel(): BaseViewModel = viewModel.apply {
        loadMovies.collectWithState { state ->
            state.isSuccessful { list ->
                movieAdapter.submitList(list)
            }
            state.isError { error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                println(error)
            }
            binding.progressBar.isVisible = state.isLoading
        }
    }
}
