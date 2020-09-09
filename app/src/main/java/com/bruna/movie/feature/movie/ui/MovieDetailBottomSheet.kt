package com.bruna.movie.feature.movie.ui

import android.graphics.Paint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bruna.movie.R
import com.bruna.movie.feature.movie.gateway.MovieViewModel
import com.bruna.movie.model.Movie
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
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

        val topCurvedEdgeTreatment = BottomAppBarTopEdgeTreatment(0F, 0F, 0F).apply {
            this.fabDiameter = 100F
        }

        val shapeAppearanceModel = ShapeAppearanceModel.Builder()
            .setRightEdge(topCurvedEdgeTreatment)
            .setLeftEdge(topCurvedEdgeTreatment)
            .build()

        val materialShapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            setTint(ContextCompat.getColor(requireContext(), R.color.colorPrimaryLight))
            paintStyle = Paint.Style.FILL_AND_STROKE
        }

        movieDetailCard.background = materialShapeDrawable
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.movieLiveData.observe(viewLifecycleOwner) {
            val (id, title, overview, releaseDate, poster, backdrop) = it as Movie
            movieDetailTitle.text = title
            movieDetailBackdrop.load(backdrop){
                placeholder(R.drawable.ic_movies_24dp)
                error(R.drawable.ic_error_24dp)
                transformations(RoundedCornersTransformation())
            }
            movieDetailOverview.text = overview
            movieDetailOverview.movementMethod = ScrollingMovementMethod()
        }
    }
}