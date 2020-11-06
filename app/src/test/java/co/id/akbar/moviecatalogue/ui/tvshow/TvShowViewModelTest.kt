package co.id.akbar.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import co.id.akbar.moviecatalogue.data.source.CatalogueRepository
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow
import co.id.akbar.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TvShowViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: TvShowViewModel
    private var catalogueRepository = Mockito.mock(CatalogueRepository::class.java)

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvShows() {
        val page = 1
        val dummyTvShows = DataDummy.generateDummyTvShow()

        val tvShows: MutableLiveData<List<TvShow>> = MutableLiveData()
        tvShows.postValue(dummyTvShows)

        Mockito.`when`(catalogueRepository.getAllTvShow(page)).thenReturn(tvShows)
        val observer = Mockito.mock(Observer::class.java) as Observer<List<TvShow>>

        viewModel.getTvShows().observeForever(observer)

        Mockito.verify(observer).onChanged(dummyTvShows)
    }
}