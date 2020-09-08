package com.bruna.movie.feature.movie.ui.list

import android.view.View
import com.bruna.movie.data.FailedState
import com.bruna.movie.data.LoadedState
import com.bruna.movie.data.LoadingState
import com.bruna.movie.data.State
import com.bruna.movie.drivers.extension.gone
import com.bruna.movie.drivers.extension.visible
import com.bruna.movie.feature.base.ui.list.BaseViewHolder
import kotlinx.android.synthetic.main.item_list_state.view.*

class NetworkViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private val progress = itemView.movieProgress
    private val errorText = itemView.movieErrorText

    override fun bind(item: Any) {
        when (val state = item as State) {
            LoadingState -> handleLoadingState()
            LoadedState -> handleLoadedState()
            is FailedState -> handleFailedState(state)
        }
    }

    private fun handleLoadingState() {
        progress.visible()
        errorText.gone()
    }

    private fun handleLoadedState() {
        progress.gone()
        errorText.gone()
    }

    private fun handleFailedState(state: FailedState) {
        progress.gone()
        errorText.visible()
        errorText.text = state.message
    }
}