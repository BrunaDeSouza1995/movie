package com.bruna.movie.feature.movie.ui.list

import android.view.View
import com.bruna.movie.feature.base.ui.list.BaseViewHolder
import com.bruna.movie.model.Movie
import com.bruna.movie.plugin.extension.createTicketViewSmall
import com.bruna.movie.plugin.extension.load
import kotlinx.android.synthetic.main.item_list_movie.view.*

class MovieViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private val card = itemView.itemMovieCard
    private val posterImage = itemView.itemMovieImage
    private val titleText = itemView.itemMovieTitleText
    private val releaseDateText = itemView.itemMovieReleaseDateText

    override fun bind(item: Any) {
        val (_, title, _, releaseDate, poster, _) = item as Movie
        titleText.text = title
        releaseDateText.text = releaseDate
        posterImage.load(poster)
        card.createTicketViewSmall()
    }
}
