package com.bruna.movie.plugin.extension

import androidx.annotation.IdRes
import androidx.navigation.NavController

fun NavController?.navigateByAction(@IdRes action: Int) {
    this?.currentDestination?.getAction(action)?.let { navigate(action) }
}