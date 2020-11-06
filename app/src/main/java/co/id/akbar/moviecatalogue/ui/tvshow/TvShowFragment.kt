package co.id.akbar.moviecatalogue.ui.tvshow

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
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow
import co.id.akbar.moviecatalogue.ui.detail.DetailMovieActivity
import co.id.akbar.moviecatalogue.utils.InfiniteScrollListener
import co.id.akbar.moviecatalogue.utils.ViewModelFactory
import co.id.akbar.moviecatalogue.utils.hide
import co.id.akbar.moviecatalogue.utils.show
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.jetbrains.anko.support.v4.startActivity

class TvShowFragment : Fragment() {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter
    private var page = 1
    private var mShows: MutableList<TvShow> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    companion object {
        fun newInstance() = TvShowFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progress_bar.show()

        if(activity != null) {
            viewModel = obtainViewModel(requireActivity())
            adapter = TvShowAdapter(context!!) {
                startActivity<DetailMovieActivity>("tvShowId" to it.id)
            }

            loadShows()

            rv_tvshow.adapter = adapter
            rv_tvshow.layoutManager = LinearLayoutManager(context)
            rv_tvshow.setHasFixedSize(true)
        }

        rv_tvshow.addOnScrollListener(scrollData())
    }

    private fun obtainViewModel(activity: FragmentActivity): TvShowViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel::class.java)
    }

    private fun loadShows(){
        progress_bar.show()
        viewModel.page = page
        viewModel.getTvShows().observe(this, Observer { tvShows->
            mShows.addAll(tvShows)
            adapter.setTvShows(mShows)
            progress_bar.hide()
        })
    }

    private fun scrollData(): InfiniteScrollListener{
        return object : InfiniteScrollListener(){
            override fun onLoadMore() {
                page += 1
                loadShows()
            }
        }
    }

}