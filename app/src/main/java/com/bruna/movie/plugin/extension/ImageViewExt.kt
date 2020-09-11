package com.bruna.movie.plugin.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bruna.movie.R

fun ImageView?.loadImage(uri: String?) {
    this?.load(uri) {
        placeholder(R.drawable.ic_movies_24dp)
        error(R.drawable.ic_error_24dp)
        RoundedCornersTransformation()
    }
}

@BindingAdapter("android:src")
fun setImageResource(view: ImageView, uri: String?) {
    view.loadImage(uri)
}
