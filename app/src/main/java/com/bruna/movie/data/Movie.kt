package com.bruna.movie.data

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bruna.movie.drivers.network.response.MovieDiscoverItemResponse

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val poster: String,
    val genre: String
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
                "https://image.tmdb.org/t/p/original${response.poster_path.orEmpty()}",
                response.genre_ids.joinToString()
            )
        }
    }
}