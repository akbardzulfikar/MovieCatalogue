package co.id.akbar.moviecatalogue.data.source.remote.network.response

import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results: MutableList<MovieEntity>
)