package com.mfathoer.sharecipe.presentation.bookmarks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mfathoer.sharecipe.R
import com.mfathoer.sharecipe.data.LoadResult
import com.mfathoer.sharecipe.databinding.FragmentBookmarksBinding
import com.mfathoer.sharecipe.presentation.RecipesListAdapter
import com.mfathoer.sharecipe.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarksFragment : BaseFragment<FragmentBookmarksBinding>(R.layout.fragment_bookmarks) {

    private val bookmarksViewModel: BookmarksViewModel by viewModels()

    private lateinit var recipesListAdapter: RecipesListAdapter

    override fun FragmentBookmarksBinding.initialize() {
        binding.apply {
            viewModel = bookmarksViewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeBookmarkedRecipesData()
    }

    private fun initRecyclerView() {
        recipesListAdapter = RecipesListAdapter()
        binding.rvBookmarkedRecipes.adapter = recipesListAdapter

        recipesListAdapter.onItemRecipeClicked = {
            val action =
                BookmarksFragmentDirections.actionBookmarksFragmentToDetailRecipeFragment(it.id)
            findNavController().navigate(action)
        }
    }

    private fun observeBookmarkedRecipesData() {
        bookmarksViewModel.recipes.observe(viewLifecycleOwner) { loadResult ->
            when (loadResult) {
                is LoadResult.Success -> {
                    showLoadingBar(false)
                    if (loadResult.data.isNullOrEmpty()) {
                        showEmptyState(true)
                    } else {
                        showEmptyState(false)
                    }
                    recipesListAdapter.submitList(loadResult.data)
                }
                is LoadResult.Error -> {
                    showLoadingBar(false)
                }
                is LoadResult.Loading -> {
                    showLoadingBar(true)
                }
            }
        }
    }

    private fun showEmptyState(state: Boolean) {
        if (state) {
            binding.emptyState.visibility = View.VISIBLE
            binding.emptyStateAnimation.playAnimation()
        } else {
            binding.emptyState.visibility = View.GONE
            binding.emptyStateAnimation.cancelAnimation()
        }
    }


    private fun showLoadingBar(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}