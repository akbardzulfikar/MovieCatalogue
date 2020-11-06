package co.id.akbar.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import co.id.akbar.moviecatalogue.data.source.CatalogueRepository
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DetailMovieViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: DetailMovieViewModel
    private var repository = Mockito.mock(CatalogueRepository::class.java)
    private var dummyMovie = DataDummy.generateDummyMovies()[0]
    private var movieId = dummyMovie.id

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(repository)
        viewModel.movieId = movieId
    }

    @Test
    fun getMovie() {
        val movie: MutableLiveData<MovieEntity> = MutableLiveData()
        movie.postValue(dummyMovie)

        Mockito.`when`(repository.getMovie(movieId)).thenReturn(movie)

        val observer = Mockito.mock(Observer::class.java) as Observer<MovieEntity>

        viewModel.getMovie().observeForever(observer)

        Mockito.verify(observer).onChanged(dummyMovie)
    }
}