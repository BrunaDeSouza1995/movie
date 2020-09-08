package com.bruna.movie.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bruna.movie.plugin.network.FILE_BASE_URL
import com.bruna.movie.plugin.network.response.MovieDiscoverItemResponse

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val poster: String,
    val backdrop: String
) {
    companion object {

        val MovieDiffItemCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }

        fun converterByMovieDiscoverResponse(response: MovieDiscoverItemResponse): Movie {
            return Movie(
                response.id,
                response.title,
                response.overview,
                response.release_date,
                FILE_BASE_URL + response.poster_path.orEmpty(),
                FILE_BASE_URL + response.backdrop_path.orEmpty()
            )
        }
    }
}