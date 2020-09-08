package com.bruna.movie.feature.movie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bruna.movie.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }
}
