package com.bruna.movie.plugin.extension

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context?.getColorCompat(@ColorRes id: Int): Int? {
    return this?.let { ContextCompat.getColor(it, id) }
}
