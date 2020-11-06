package co.id.akbar.moviecatalogue.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.akbar.moviecatalogue.R
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.ui.detail.DetailMovieActivity
import co.id.akbar.moviecatalogue.utils.InfiniteScrollListener
import co.id.akbar.moviecatalogue.utils.ViewModelFactory
import co.id.akbar.moviecatalogue.utils.hide
import co.id.akbar.moviecatalogue.utils.show
import kotlinx.android.synthetic.main.fragment_movies.*
import org.jetbrains.anko.support.v4.startActivity

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapter: MoviesAdapter
    private var page = 1
    private var mMovies: MutableList<MovieEntity> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    companion object {
        fun newInstance(): Fragment {
            return MoviesFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity != null) {
            viewModel = obtainViewModel(requireActivity())
            adapter = MoviesAdapter(context!!) {
                startActivity<DetailMovieActivity>("movieId" to it.id)
            }

            loadMovies()

            rv_movie.adapter = adapter
            rv_movie.layoutManager = LinearLayoutManager(context)
            rv_movie.setHasFixedSize(true)
        }

        rv_movie.addOnScrollListener(scrollData())
    }

    private fun obtainViewModel(activity: FragmentActivity): MoviesViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MoviesViewModel::class.java)
    }

    private fun loadMovies(){
        progress_bar.show()
        viewModel.page = page
        viewModel.getMovies().observe(this, Observer { movies ->
            mMovies.addAll(movies)
            adapter.setMovies(mMovies)
            progress_bar.hide()
        })
    }

    private fun scrollData(): InfiniteScrollListener {
        return object : InfiniteScrollListener() {
            override fun onLoadMore() {
                page += 1
                loadMovies()
            }
        }
    }
}