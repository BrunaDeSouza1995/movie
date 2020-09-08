package com.bruna.movie.data

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
    val genre: List<String>
) {
    companion object {
        fun converterByMovieDiscoverResponse(response: MovieDiscoverItemResponse): Movie {
            return Movie(
                response.id,
                response.title,
                response.overview,
                response.release_date,
                response.poster_path.orEmpty(),
                response.genre_ids.map { toString() }
            )
        }
    }
}
