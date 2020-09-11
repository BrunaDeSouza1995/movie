package com.bruna.movie.feature.movie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bruna.movie.databinding.BottomSheetMovieDetailBinding
import com.bruna.movie.feature.movie.gateway.MovieViewModel
import com.bruna.movie.plugin.extension.createTicketViewLarge
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_movie_detail.*

class MovieDetailBottomSheet : BottomSheetDialogFragment() {

    private val viewModel: MovieViewModel by activityViewModels()
    private var binding: BottomSheetMovieDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetMovieDetailBinding.inflate(inflater, container, false)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailCard.createTicketViewLarge()
    }
}