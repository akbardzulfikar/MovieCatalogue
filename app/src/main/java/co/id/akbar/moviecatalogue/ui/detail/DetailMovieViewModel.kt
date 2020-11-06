package co.id.akbar.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.id.akbar.moviecatalogue.data.source.CatalogueRepository
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow

class DetailMovieViewModel(
    private val catalogueRepository: CatalogueRepository
): ViewModel() {

    var movieId: Int = 0
    var tvShowId: Int = 0

    fun getMovie(): LiveData<MovieEntity> {
        return catalogueRepository.getMovie(movieId)
    }

    fun addFavorite(movie: MovieEntity) {
        catalogueRepository.addFavoriteMovie(movie)
    }

    fun removeFavorite(movie: MovieEntity) {
        catalogueRepository.removeFavoriteMovie(movie)
    }

    fun isFavorited(movie: MovieEntity): Boolean {
        return catalogueRepository.isFavoritedMovie(movie)
    }

    fun getTvShow(): LiveData<TvShow> {
        return catalogueRepository.getTvShow(tvShowId)
    }

    fun addFavoriteTv(tvShow: TvShow) {
        catalogueRepository.addFavoriteTvShow(tvShow)
    }

    fun removeFavoriteTv(tvShow: TvShow) {
        catalogueRepository.removeFavoriteTvShow(tvShow)
    }

    fun isFavoritedTv(tvShow: TvShow) = catalogueRepository.isFavoriteTvShow(tvShow)
}