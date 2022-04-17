package com.mfathoer.sharecipe.presentation

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.mfathoer.sharecipe.R

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imgUri) {
            placeholder(R.drawable.bg_placeholder)
            error(R.drawable.ic_baseline_broken_image_24)
        }
    }
}