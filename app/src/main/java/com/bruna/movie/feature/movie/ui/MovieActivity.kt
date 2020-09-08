package com.bruna.movie.feature.movie.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bruna.movie.R
import com.bruna.movie.feature.movie.gateway.MovieViewModel
import com.bruna.movie.feature.movie.ui.list.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie.*

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()
    private val adapter = MovieListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setUpList()
        setUpObservers()
    }

    private fun setUpList() {
        movieList.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.moviesLiveData.observe(this, adapter::submitList)
        viewModel.loadedLiveData.observe(this, adapter::submitNetwork)
    }
}


