package co.id.akbar.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.id.akbar.moviecatalogue.data.source.CatalogueRepository
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow

class TvShowViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {
    var page = 1
    fun getTvShows(): LiveData<List<TvShow>> {
        return catalogueRepository.getAllTvShow(page)
    }
}