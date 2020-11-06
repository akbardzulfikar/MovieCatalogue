package co.id.akbar.moviecatalogue.ui.favorite

import androidx.lifecycle.ViewModel
import co.id.akbar.moviecatalogue.data.source.CatalogueRepository
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow

class FavoriteViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {
    fun getMovies() = catalogueRepository.getAllFavoriteMovies()
    fun getTvShows() = catalogueRepository.getAllFavoriteTvShows()

    fun addFavorite(movie: MovieEntity) {
        catalogueRepository.addFavoriteMovie(movie)
    }

    fun removeFavorite(movie: MovieEntity) {
        catalogueRepository.removeFavoriteMovie(movie)
    }


    fun addFavoriteTv(tv: TvShow) {
        catalogueRepository.addFavoriteTvShow(tv)
    }

    fun removeFavoriteTv(tv: TvShow) {
        catalogueRepository.removeFavoriteTvShow(tv)
    }

}