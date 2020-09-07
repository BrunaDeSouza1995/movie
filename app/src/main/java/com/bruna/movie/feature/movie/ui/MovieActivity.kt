package com.bruna.movie.feature.movie.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bruna.movie.R
import com.bruna.movie.feature.movie.gateway.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie.*

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

//        val build = ShapeAppearanceModel().toBuilder()
////            .setAllCorners(InnerCutCornerTreatment(20F))
//            .setLeftEdge(CurvedEdgeTreatment(75F))
//            .setRightEdge(CurvedEdgeTreatment(75F))
//            .build()
//
//
//        val shapeDrawable = MaterialShapeDrawable()
//        shapeDrawable.fillColor = ColorStateList.valueOf(
//            ContextCompat.getColor(
//                this,
//                R.color.colorAccent
//            )
//        )
//        shapeDrawable.shapeAppearanceModel = build
//        card.background = shapeDrawable

        viewModel.teste()

        viewModel.teste.observe(this) { testText.text = it }
    }
}


