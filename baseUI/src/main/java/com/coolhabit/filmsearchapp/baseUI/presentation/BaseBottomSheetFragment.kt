package com.coolhabit.filmsearchapp.baseUI.presentation

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.coolhabit.filmsearchapp.baseUI.R
import com.coolhabit.filmsearchapp.baseUI.databinding.FragmentBottomSheetBaseBinding
import com.coolhabit.filmsearchapp.baseUI.extensions.viewBinding
import com.coolhabit.filmsearchapp.baseUI.model.NavCommand
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseBottomSheetFragment() : BottomSheetDialogFragment() {

    protected val baseBinding by viewBinding(FragmentBottomSheetBaseBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CustomBottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val cloneInContext = inflater
            .cloneInContext(ContextThemeWrapper(activity, R.style.Theme_FilmSearchApp))
        return cloneInContext
            .inflate(R.layout.fragment_bottom_sheet_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateChildLayout()
        withViewModel()
            .apply {
                onNavigationCommands()
            }
    }

    private fun inflateChildLayout() {
        val baseLayout = baseBinding.bottomSheetBase
        val child: View = layoutInflater.inflate(provideContent(), null)
        baseLayout.apply {
            baseBinding.contentContainer.addView(child)
        }
    }

    abstract fun provideContent(): Int

    abstract fun withViewModel(): BaseViewModel

    fun closeDialog() {
        dialog?.dismiss()
    }

    private fun BaseViewModel.onNavigationCommands() {

        this.navigationCommand.collectWithState { action ->
            when (action) {
                is NavCommand.Navigate -> findNavController().navigate(
                    action.directions,
                )
                is NavCommand.Intent -> kotlin.runCatching { requireContext().startActivity(action.intent) }
                is NavCommand.Deeplink -> {
                    if (action.backTo > 0) {
                        findNavController().popBackStack(action.backTo, true)
                    }
                    findNavController().navigate(action.deeplinkRequest)
                }
                else -> {}
            }
        }
    }

    protected fun <T> Flow<T>.collectWithState(get: (T) -> Unit) {
        val flow = this
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect {
                    get(it)
                }
            }
        }
    }

    @MainThread
    inline fun <reified VM : ViewModel> Fragment.viewModels() =
        createViewModelLazy(VM::class, { this.viewModelStore }, { viewModelFactory })
}
