package com.bruna.movie.feature.movie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bruna.movie.R
import com.bruna.movie.feature.movie.gateway.MovieViewModel
import com.bruna.movie.model.Movie
import com.bruna.movie.plugin.extension.createTicketViewLarge
import com.bruna.movie.plugin.extension.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_movie_detail.*

class MovieDetailBottomSheet : BottomSheetDialogFragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailCard.createTicketViewLarge()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.movieLiveData.observe(viewLifecycleOwner) {
            val (id, title, overview, releaseDate, poster, backdrop) = it as Movie
            movieDetailTitle.text = title
            movieDetailBackdrop.load(backdrop)
            movieDetailOverview.text = overview
        }
    }
}