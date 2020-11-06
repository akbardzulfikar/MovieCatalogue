package co.id.akbar.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import co.id.akbar.moviecatalogue.data.source.CatalogueRepository
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FavoriteViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: FavoriteViewModel
    private var catalogueRepository = Mockito.mock(CatalogueRepository::class.java)

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(catalogueRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = MutableLiveData<PagedList<MovieEntity>>()
        val pagedList: PagedList<MovieEntity> = Mockito.mock(PagedList::class.java) as PagedList<MovieEntity>
        dummyMovies.value = pagedList

        Mockito.`when`(catalogueRepository.getAllFavoriteMovies()).thenReturn(dummyMovies)
        val observer = Mockito.mock(Observer::class.java) as Observer<PagedList<MovieEntity>>

        viewModel.getMovies().observeForever(observer)

        Mockito.verify(observer).onChanged(pagedList)
    }

    @Test
    fun getFavoriteTvShows() {
        val dummyShows = MutableLiveData<PagedList<TvShow>>()
        val pagedList: PagedList<TvShow> = Mockito.mock(PagedList::class.java) as PagedList<TvShow>
        dummyShows.value = pagedList

        Mockito.`when`(catalogueRepository.getAllFavoriteTvShows()).thenReturn(dummyShows)
        val observer = Mockito.mock(Observer::class.java) as Observer<PagedList<TvShow>>

        viewModel.getTvShows().observeForever(observer)

        Mockito.verify(observer).onChanged(pagedList)
    }
}