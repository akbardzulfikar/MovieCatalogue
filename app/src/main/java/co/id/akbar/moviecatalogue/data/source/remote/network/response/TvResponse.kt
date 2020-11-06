package co.id.akbar.moviecatalogue.data.source.remote.network.response

import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow

data class TvResponse(
    val results: MutableList<TvShow>
)