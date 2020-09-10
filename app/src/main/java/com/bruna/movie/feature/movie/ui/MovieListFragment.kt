package com.bruna.movie.feature.movie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bruna.movie.R
import com.bruna.movie.plugin.extension.navigateByAction
import com.bruna.movie.feature.movie.gateway.MovieViewModel
import com.bruna.movie.feature.movie.ui.list.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()
    private val adapter by lazy { MovieListAdapter(viewModel::navigateToMovieDetail) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpList()
        setUpObservers()
    }

    private fun setUpList() {
        movieList.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.loadedLiveData.observe(viewLifecycleOwner, adapter::submitNetwork)
        viewModel.moviesLiveData.observe(viewLifecycleOwner, adapter::submitList)
        viewModel.movieLiveData.observe(viewLifecycleOwner) { findNavController().navigateByAction(R.id.action_movieListFragment_to_movieDetailBottomSheet) }
    }
}