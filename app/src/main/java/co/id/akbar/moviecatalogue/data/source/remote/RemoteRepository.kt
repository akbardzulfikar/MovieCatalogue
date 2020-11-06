package co.id.akbar.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow
import co.id.akbar.moviecatalogue.data.source.remote.network.ApiClient
import co.id.akbar.moviecatalogue.data.source.remote.network.ApiInterface
import co.id.akbar.moviecatalogue.data.source.remote.network.response.MovieResponse
import co.id.akbar.moviecatalogue.data.source.remote.network.response.TvResponse
import co.id.akbar.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    private val apiClient = ApiClient.getClient().create(ApiInterface::class.java)

    companion object {
        fun getInstance(): RemoteRepository {
            return RemoteRepository()
        }
        private const val TAG = "RemoteRepository"
    }

    fun getMovies(page: Int): LiveData<List<MovieEntity>>{
        val movies: MutableLiveData<List<MovieEntity>> = MutableLiveData()

        EspressoIdlingResource.increment()

        apiClient.movies(page).enqueue(
            object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d(TAG, t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    response.body()?.let { movies.postValue(it.results) }
                    EspressoIdlingResource.decrement()
                }

            }
        )
        return movies
    }

    fun getMovie(id: Int): LiveData<MovieEntity>{
        val movie: MutableLiveData<MovieEntity> = MutableLiveData()

        EspressoIdlingResource.increment()

        apiClient.movie(id).enqueue(
            object : Callback<MovieEntity> {
                override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                    Log.d(TAG, t.localizedMessage)
                }

                override fun onResponse(call: Call<MovieEntity>, response: Response<MovieEntity>) {
                    movie.postValue(response.body())
                    EspressoIdlingResource.decrement()
                }

            }
        )
        return  movie
    }

    fun getTvShows(page: Int): LiveData<List<TvShow>> {
        val tvShows: MutableLiveData<List<TvShow>> = MutableLiveData()

        EspressoIdlingResource.increment()

        apiClient.tvShows(page).enqueue(
            object : Callback<TvResponse> {
                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    Log.d(TAG, t.localizedMessage)
                }

                override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                    response.body()?.let { tvShows.postValue(it.results) }
                    EspressoIdlingResource.decrement()
                }

            }
        )
        return  tvShows
    }

    fun getTvShow(id: Int): LiveData<TvShow> {
        val tvShow: MutableLiveData<TvShow> = MutableLiveData()

        EspressoIdlingResource.increment()

        apiClient.tvShow(id).enqueue(
            object : Callback<TvShow> {
                override fun onFailure(call: Call<TvShow>, t: Throwable) {
                    Log.d(TAG, t.localizedMessage)
                }

                override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                    tvShow.postValue(response.body())
                    EspressoIdlingResource.decrement()
                }
            }
        )
        return tvShow
    }
}