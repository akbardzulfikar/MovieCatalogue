package co.id.akbar.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow

interface CatalogueDataSource {

    fun getAllMovies(page: Int): LiveData<List<MovieEntity>>

    fun getMovie(id: Int): LiveData<MovieEntity>

    fun getAllTvShow(page: Int): LiveData<List<TvShow>>

    fun getTvShow(id: Int): LiveData<TvShow>

    fun getAllFavoriteMovies(
    ): LiveData<PagedList<MovieEntity>>

    fun addFavoriteMovie(movie: MovieEntity)

    fun removeFavoriteMovie(movie: MovieEntity)

    fun isFavoritedMovie(movie: MovieEntity): Boolean

    fun getAllFavoriteTvShows(): LiveData<PagedList<TvShow>>

    fun addFavoriteTvShow(tvShow: TvShow)

    fun removeFavoriteTvShow(tvShow: TvShow)

    fun isFavoriteTvShow(tvShow: TvShow): Boolean
}