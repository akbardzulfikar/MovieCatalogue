package co.id.akbar.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import co.id.akbar.moviecatalogue.data.source.local.LocalRepository
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow
import co.id.akbar.moviecatalogue.data.source.remote.RemoteRepository

class CatalogueRepository(private val remoteRepository: RemoteRepository,
                          private val localRepository: LocalRepository
): CatalogueDataSource {

    companion object {
        fun getInstance(remoteRepository: RemoteRepository, localRepository: LocalRepository): CatalogueRepository {
            return CatalogueRepository(remoteRepository, localRepository)
        }
    }

    override fun getAllMovies(page: Int): LiveData<List<MovieEntity>> {
        return remoteRepository.getMovies(page)
    }

    override fun getMovie(id: Int): LiveData<MovieEntity> {
        return remoteRepository.getMovie(id)
    }

    override fun getAllTvShow(page: Int): LiveData<List<TvShow>> {
        return remoteRepository.getTvShows(page)
    }

    override fun getTvShow(id: Int): LiveData<TvShow> {
        return remoteRepository.getTvShow(id)
    }

    override fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        return LivePagedListBuilder(localRepository.getFavoriteMoviesPaged(), 10).build()
    }

    override fun addFavoriteMovie(movie: MovieEntity) {
        localRepository.addFavoriteMovie(movie)
    }

    override fun removeFavoriteMovie(movie: MovieEntity) {
        localRepository.removeFavoriteMovie(movie)
    }

    override fun isFavoritedMovie(movie: MovieEntity) =
        localRepository.isFavoritedMovie(movie)

    override fun getAllFavoriteTvShows(): LiveData<PagedList<TvShow>>{
        return LivePagedListBuilder(localRepository.getFavoriteTvShowPaged(), 10).build()
    }

    override fun addFavoriteTvShow(tvShow: TvShow) {
        localRepository.addFavoriteTvShow(tvShow)
    }

    override fun removeFavoriteTvShow(tvShow: TvShow) {
        localRepository.removeFavoriteTvShow(tvShow)
    }

    override fun isFavoriteTvShow(tvShow: TvShow) =
        localRepository.isFavoritedTvShow(tvShow)

}