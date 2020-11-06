package co.id.akbar.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.id.akbar.moviecatalogue.R
import co.id.akbar.moviecatalogue.ui.detail.DetailMovieActivity
import co.id.akbar.moviecatalogue.utils.ViewModelFactory
import co.id.akbar.moviecatalogue.utils.hide
import co.id.akbar.moviecatalogue.utils.show
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tvshow.*
import kotlinx.android.synthetic.main.fragment_tvshow.progress_bar
import org.jetbrains.anko.support.v4.startActivity

class FavoriteTvFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: FavoriteTvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        itemTouchHelper.attachToRecyclerView(rv_tvshow)

        progress_bar.show()

        if(activity != null) {
            viewModel = obtainViewModel(requireActivity())
            adapter = FavoriteTvShowAdapter{
                startActivity<DetailMovieActivity>("tvShowId" to it.id)
            }

            viewModel.getTvShows().observe(this, Observer {
                adapter.submitList(it)
                progress_bar.hide()
            })

            rv_tvshow.adapter = adapter
            rv_tvshow.layoutManager = LinearLayoutManager(context)
            rv_tvshow.setHasFixedSize(true)
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return  ViewModelProviders.of(activity, factory).get(FavoriteViewModel::class.java)
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvEntity = adapter.getSwipedData(swipedPosition)
                tvEntity?.let { viewModel.removeFavoriteTv(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    tvEntity?.let { viewModel.addFavoriteTv(it) }
                }
                snackbar.show()
            }
        }
    })
}
