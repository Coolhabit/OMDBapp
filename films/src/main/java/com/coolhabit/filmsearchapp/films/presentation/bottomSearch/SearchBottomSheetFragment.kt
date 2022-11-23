package com.coolhabit.filmsearchapp.films.presentation.bottomSearch

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import com.coolhabit.filmsearchapp.baseUI.extensions.PAGE_REGEX
import com.coolhabit.filmsearchapp.baseUI.extensions.viewBinding
import com.coolhabit.filmsearchapp.baseUI.presentation.BaseBottomSheetFragment
import com.coolhabit.filmsearchapp.baseUI.presentation.BaseViewModel
import com.coolhabit.filmsearchapp.films.R
import com.coolhabit.filmsearchapp.films.databinding.FragmentSearchBottomSheetBinding
import com.coolhabit.filmsearchapp.films.presentation.FilmsListFragment.Companion.SEARCH_NAME_DATA_KEY
import com.coolhabit.filmsearchapp.films.presentation.FilmsListFragment.Companion.SEARCH_PAGES_DATA_KEY
import com.coolhabit.filmsearchapp.films.presentation.FilmsListFragment.Companion.SEARCH_REQUEST_KEY

class SearchBottomSheetFragment :
    BaseBottomSheetFragment() {

    private val binding by viewBinding(FragmentSearchBottomSheetBinding::bind)
    private val viewModel by viewModels<SearchBottomSheetViewModel>()

    override fun provideContent(): Int = R.layout.fragment_search_bottom_sheet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseBinding.closeButton.setOnClickListener {
            closeDialog()
        }

        binding.searchBtn.setOnClickListener {
            val nameValue = binding.searchNameInput.text
            val pageValue = binding.searchPageInput.text
            if (nameValue.isNullOrEmpty() || pageValue.isNullOrEmpty()) {
                Toast.makeText(
                    requireContext(),
                    requireContext().resources.getString(R.string.enter_something_search),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!pageValue.matches(PAGE_REGEX.toRegex())) {
                Toast.makeText(
                    requireContext(),
                    requireContext().resources.getString(R.string.page_not_in_range),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                setFragmentResult(
                    SEARCH_REQUEST_KEY,
                    Bundle().apply {
                        putString(SEARCH_NAME_DATA_KEY, nameValue.toString())
                        putInt(SEARCH_PAGES_DATA_KEY, pageValue.toString().toInt())
                    }
                )
                closeDialog()
            }
        }
    }

    override fun withViewModel(): BaseViewModel = viewModel.apply {
    }
}
