package co.id.akbar.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.id.akbar.moviecatalogue.R
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow
import co.id.akbar.moviecatalogue.utils.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private var movieId: Int = 0
    private lateinit var mMovie: MovieEntity
    private var tvShowId: Int = 0
    private lateinit var mTvShow: TvShow
    private lateinit var viewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        progressBar.show()

        movieId = intent.getIntExtra("movieId", 0)
        if (movieId != 0) {
            viewModel = obtainViewModel(this)
            viewModel.movieId = movieId

            viewModel.getMovie().observe(this, Observer { movie ->

                mMovie = movie

                tvTitle.text = movie.title
                tvYear.text = movie.releaseDate.year()
                tvDescription.text = movie.description
                ratingBar.rating = (movie.vote / 2)

                Glide.with(this)
                    .load(Constants.IMG_URL + movie.backdrop)
                    .into(imgBackdrop)


                Glide.with(this)
                    .load(Constants.IMG_URL + movie.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)

                progressBar.hide()

                favoriteState()
            })
            fabFavorite.setOnClickListener { fabOnClick() }
        }
        tvShowId = intent.getIntExtra("tvShowId", 0)
        if (tvShowId != 0) {
            viewModel = obtainViewModel(this)
            viewModel.tvShowId = tvShowId
            viewModel.getTvShow().observe(this, Observer { tvShow ->

                mTvShow = tvShow

                tvTitle.text = tvShow.title
                tvYear.text = tvShow.firstAirDate.year()
                tvDescription.text = tvShow.description
                ratingBar.rating = (tvShow.rating / 2)

                Glide.with(this)
                    .load(Constants.IMG_URL + tvShow.backdrop)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgBackdrop)


                Glide.with(this)
                    .load(Constants.IMG_URL + tvShow.poster)
                    .into(imgPoster)

                progressBar.hide()

                favoriteStateTv()
            })
            fabFavorite.setOnClickListener { fabOnClickTv() }
        }

    }

    private fun obtainViewModel(activity: DetailMovieActivity): DetailMovieViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailMovieViewModel::class.java)
    }

    private fun fabOnClick() {
        if (viewModel.isFavorited(mMovie)) {
            viewModel.removeFavorite(mMovie)
            Snackbar.make(scrollView, getString(R.string.unfavorited, mMovie.title), Snackbar.LENGTH_SHORT).show()
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_disable))
        } else {
            viewModel.addFavorite(mMovie)
            Snackbar.make(scrollView, getString(R.string.favorited, mMovie.title), Snackbar.LENGTH_SHORT).show()
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_black))
        }
    }

    private fun favoriteState(){
        if (viewModel.isFavorited(mMovie)) {
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_black))
        } else {
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_disable))
        }
    }

    private fun fabOnClickTv() {
        if (viewModel.isFavoritedTv(mTvShow)) {
            viewModel.removeFavoriteTv(mTvShow)
            Snackbar.make(scrollView, getString(R.string.unfavorited, mTvShow.title), Snackbar.LENGTH_SHORT).show()
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_disable))
        } else {
            viewModel.addFavoriteTv(mTvShow)
            Snackbar.make(scrollView, getString(R.string.favorited, mTvShow.title), Snackbar.LENGTH_SHORT).show()
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_black))
        }
    }

    private fun favoriteStateTv(){
        if (viewModel.isFavoritedTv(mTvShow)) {
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_black))
        } else {
            fabFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_disable))
        }
    }
}
