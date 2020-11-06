package co.id.akbar.moviecatalogue.data.source.local

import android.content.Context
import androidx.paging.DataSource
import co.id.akbar.moviecatalogue.data.source.local.dao.MovieDao
import co.id.akbar.moviecatalogue.data.source.local.dao.TvShowDao
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalRepository(context: Context) {

    private val movieDao: MovieDao
    private val tvShowDao: TvShowDao

    init {
        val db = AppDatabase.getDatabase(context)
        movieDao = db.movieDao()
        tvShowDao = db.tvShowDao()
    }

    companion object {
        fun getInstance(context: Context): LocalRepository {
            return LocalRepository(context)
        }
    }

    fun getFavoriteMoviesPaged(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.allAsPaged()
    }

    fun addFavoriteMovie(movie: MovieEntity) {
        GlobalScope.launch(Dispatchers.Main) { movieDao.insert(movie) }
    }

    fun removeFavoriteMovie(movie: MovieEntity) {
        GlobalScope.launch(Dispatchers.Main) { movieDao.delete(movie) }
    }

    fun isFavoritedMovie(movie: MovieEntity): Boolean {
        return movieDao.getById(movie.id) != null
    }

    fun getFavoriteTvShowPaged(): DataSource.Factory<Int, TvShow> {
        return tvShowDao.allAsPaged()
    }

    fun addFavoriteTvShow(tvShow: TvShow) {
        GlobalScope.launch(Dispatchers.Main) { tvShowDao.insert(tvShow) }
    }

    fun removeFavoriteTvShow(tvShow: TvShow) {
        GlobalScope.launch(Dispatchers.Main) { tvShowDao.delete(tvShow) }
    }

    fun isFavoritedTvShow(tvShow: TvShow): Boolean {
        return tvShowDao.getById(tvShow.id) != null
    }
}