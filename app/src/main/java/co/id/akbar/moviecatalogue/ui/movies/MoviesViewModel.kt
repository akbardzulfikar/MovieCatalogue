package co.id.akbar.moviecatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.id.akbar.moviecatalogue.data.source.CatalogueRepository
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity

class MoviesViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {

    var page = 1

    fun getMovies(): LiveData<List<MovieEntity>> {
        return catalogueRepository.getAllMovies(page)
    }
}