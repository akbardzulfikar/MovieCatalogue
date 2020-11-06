package co.id.akbar.moviecatalogue.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.id.akbar.moviecatalogue.data.source.CatalogueRepository
import co.id.akbar.moviecatalogue.di.Injection
import co.id.akbar.moviecatalogue.ui.detail.DetailMovieViewModel
import co.id.akbar.moviecatalogue.ui.favorite.FavoriteViewModel
import co.id.akbar.moviecatalogue.ui.movies.MoviesViewModel
import co.id.akbar.moviecatalogue.ui.tvshow.TvShowViewModel

class ViewModelFactory(private val catalogueRepository: CatalogueRepository):
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {
            if(INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if(INSTANCE == null) {
                        INSTANCE = ViewModelFactory(Injection.provideRepository(application))
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> MoviesViewModel(catalogueRepository) as (T)
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> DetailMovieViewModel(catalogueRepository) as (T)
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> TvShowViewModel(catalogueRepository) as (T)
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(catalogueRepository) as (T)
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

    }
}