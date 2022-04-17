package com.mfathoer.sharecipe.presentation.browse

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mfathoer.sharecipe.R
import com.mfathoer.sharecipe.data.LoadResult
import com.mfathoer.sharecipe.databinding.FragmentBrowseBinding
import com.mfathoer.sharecipe.domain.utils.hideKeyboard
import com.mfathoer.sharecipe.presentation.RecipesListAdapter
import com.mfathoer.sharecipe.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrowseFragment : BaseFragment<FragmentBrowseBinding>(R.layout.fragment_browse) {

    private val browseViewModel: BrowseViewModel by viewModels()
    private lateinit var recipesListAdapter: RecipesListAdapter

    override fun FragmentBrowseBinding.initialize() {
        binding.apply {
            viewModel = browseViewModel
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        save text query before configuration change
        outState.putString(KEY_TEXT_QUERY, binding.etSearch.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
//        restore text query after configuration change
        savedInstanceState?.let { bundle ->
            binding.etSearch.setText(bundle.getString(KEY_TEXT_QUERY, ""))
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        setEditorActionListenerOnSearchTextField()

        observeRecipesListData()
    }

    private fun initRecyclerView() {
        recipesListAdapter = RecipesListAdapter()
        binding.rvRecipes.adapter = recipesListAdapter

        recipesListAdapter.onItemRecipeClicked = { recipe ->
            val action = BrowseFragmentDirections.actionBrowseFragmentToDetailRecipeFragment(recipe.id)
            findNavController().navigate(action)
        }
    }

    private fun setEditorActionListenerOnSearchTextField() {
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val queryText = binding.etSearch.text.toString()

                if (isUserInputValidated(queryText)) {
                    browseViewModel.searchRecipes(queryText)
                }

                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun isUserInputValidated(queryText: String): Boolean {
        val validation = if (queryText.isBlank()) {
            binding.etSearch.error = getString(R.string.alert_empty_search_text)
            false
        } else {
            true
        }
        hideKeyboard()
        return validation
    }

    private fun observeRecipesListData() {
        browseViewModel.recipes.observe(viewLifecycleOwner) { loadResult ->
            when (loadResult) {
                is LoadResult.Success -> {
                    showLoadingBar(false)
                    recipesListAdapter.submitList(loadResult.data)
                }
                is LoadResult.Loading -> {
                    showLoadingBar(true)
                }
                is LoadResult.Error -> {
                    showLoadingBar(false)
                }
            }
        }
    }

    private fun showLoadingBar(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        private const val KEY_TEXT_QUERY = "key_text_query"
    }
}