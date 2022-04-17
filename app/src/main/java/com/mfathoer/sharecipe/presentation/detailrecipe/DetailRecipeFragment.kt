package com.mfathoer.sharecipe.presentation.detailrecipe

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mfathoer.sharecipe.R
import com.mfathoer.sharecipe.data.LoadResult
import com.mfathoer.sharecipe.databinding.FragmentDetailRecipeBinding
import com.mfathoer.sharecipe.domain.model.Recipe
import com.mfathoer.sharecipe.domain.utils.Helpers
import com.mfathoer.sharecipe.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRecipeFragment :
    BaseFragment<FragmentDetailRecipeBinding>(R.layout.fragment_detail_recipe) {

    private val args: DetailRecipeFragmentArgs by navArgs()

    private val detailRecipeViewModel: DetailRecipeViewModel by viewModels()

    override fun FragmentDetailRecipeBinding.initialize() {
        binding.apply {
            viewModel = detailRecipeViewModel
            detailRecipeFragment = this@DetailRecipeFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isFromBookmarksFragment()) {
//            get data from local
            detailRecipeViewModel.getDetailBookmarkedRecipeById(args.recipeId)
        } else {
//            get data from network
            detailRecipeViewModel.getDetailRecipe(args.recipeId)
        }
        observeDetailRecipeData()
    }

    private fun isFromBookmarksFragment(): Boolean {
        return findNavController().previousBackStackEntry?.destination?.id == R.id.bookmarksFragment
    }

    private fun observeDetailRecipeData() {
        detailRecipeViewModel.recipe.observe(viewLifecycleOwner) { loadResult ->
            when (loadResult) {
                is LoadResult.Success -> {
                    showLoadingBar(false)
                    loadResult.data?.let { recipe ->
                        populateData(recipe)
                    }
                }
                is LoadResult.Error -> {
                    showLoadingBar(false)
                }
                is LoadResult.Loading -> {
                    showLoadingBar(true)
                }
            }
        }

        detailRecipeViewModel.bookmarkStatus.observe(viewLifecycleOwner) { status ->
            if (status) {
                binding.btnBookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
            } else {
                binding.btnBookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
            }
        }
    }

    private fun populateData(recipe: Recipe) {
        binding.apply {
            this.recipe =
                recipe.copy(summary = Helpers.convertHtmlFormattedTextToString(recipe.summary))
        }
    }


    private fun showLoadingBar(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
            binding.content.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.content.visibility = View.VISIBLE
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun openRecipeUrlInBrowser(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(Intent.createChooser(intent, getString(R.string.browse_with)))
        }
    }

    fun shareRecipe(url: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_recipe, url))
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    fun backToPreviousScreen() {
        activity?.onBackPressed()
    }


}