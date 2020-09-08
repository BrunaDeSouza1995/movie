package com.bruna.movie.feature.movie.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.bruna.movie.R
import com.bruna.movie.data.LoadedState
import com.bruna.movie.data.Movie
import com.bruna.movie.data.Movie.Companion.MovieDiffItemCallback
import com.bruna.movie.data.State
import com.bruna.movie.feature.base.ui.list.BaseViewHolder

class MovieListAdapter : PagedListAdapter<Movie, BaseViewHolder>(MovieDiffItemCallback) {

    private var state: State? = null

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) R.layout.item_list_state
        else R.layout.item_list_movie
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent, false)

        return when (viewType) {
            R.layout.item_list_movie -> MovieViewHolder(view)
            R.layout.item_list_state -> NetworkViewHolder(view)
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_list_movie -> getItem(position)?.let((holder as MovieViewHolder)::bind)
            R.layout.item_list_state -> state?.let((holder as NetworkViewHolder)::bind)
        }
    }

    fun submitNetwork(newState: State) {
        val oldState = this.state
        val oldExtraRow = hasExtraRow()
        this.state = newState
        val newExtraRow = hasExtraRow()

        when {
            oldExtraRow != newExtraRow -> {
                if (oldExtraRow) notifyItemRemoved(itemCount)
                else notifyItemInserted(itemCount)
            }
            newExtraRow && oldState != newState -> {
                notifyItemChanged(itemCount - 1)
            }
        }
    }

    private fun hasExtraRow(): Boolean = state != LoadedState

}