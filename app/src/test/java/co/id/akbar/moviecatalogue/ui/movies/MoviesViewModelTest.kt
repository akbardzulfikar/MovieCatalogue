package co.id.akbar.moviecatalogue.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import co.id.akbar.moviecatalogue.data.source.CatalogueRepository
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.utils.DataDummy
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MoviesViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: MoviesViewModel
    private var repository = Mockito.mock(CatalogueRepository::class.java)

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun getMovies() {
        val page = 1
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies: MutableLiveData<List<MovieEntity>> = MutableLiveData()
        val observer = Mockito.mock(Observer::class.java) as Observer<List<MovieEntity>>

        movies.postValue(dummyMovies)

        Mockito.`when`(repository.getAllMovies(1)).thenReturn(movies)

        viewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }
}