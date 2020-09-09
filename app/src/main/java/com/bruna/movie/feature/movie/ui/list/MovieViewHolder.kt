package com.bruna.movie.feature.movie.ui.list

import android.graphics.Paint
import android.view.View
import androidx.core.content.ContextCompat
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bruna.movie.R
import com.bruna.movie.model.Movie
import com.bruna.movie.feature.base.ui.list.BaseViewHolder
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import kotlinx.android.synthetic.main.item_list_movie.view.*


class MovieViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private val card = itemView.itemMovieCard
    private val posterImage = itemView.itemMovieImage
    private val titleText = itemView.itemMovieTitleText
    private val releaseDateText = itemView.itemMovieReleaseDateText

    override fun bind(item: Any) {
        setUpCard()

        val (id, title, overview, releaseDate, poster, genre) = item as Movie

        titleText.text = title
        releaseDateText.text = releaseDate
        posterImage.load(poster) {
            placeholder(R.drawable.ic_movies_24dp)
            error(R.drawable.ic_error_24dp)
            transformations(RoundedCornersTransformation())
        }
    }

    private fun setUpCard() {

        val  topCurvedEdgeTreatment = BottomAppBarTopEdgeTreatment(0F, 0F, 0F).apply {
            this.fabDiameter = 100F
        }

        val shapeAppearanceModel = ShapeAppearanceModel.Builder()
//            .setTopEdge(topCurvedEdgeTreatment)
//            .setBottomEdge(topCurvedEdgeTreatment)
            .setRightEdge(topCurvedEdgeTreatment)
//            .setAllEdges(topCurvedEdgeTreatment)
            .build()

        val materialShapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            setTint(ContextCompat.getColor(itemView.context, R.color.colorPrimaryLight))
            paintStyle = Paint.Style.FILL_AND_STROKE
        }

        card.background = materialShapeDrawable
    }
}
