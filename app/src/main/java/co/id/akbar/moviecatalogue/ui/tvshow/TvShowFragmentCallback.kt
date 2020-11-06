package co.id.akbar.moviecatalogue.ui.tvshow

import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow

interface TvShowFragmentCallback {
    fun onShareClick(tv: TvShow)
}
