package co.id.akbar.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.id.akbar.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment() {

    private lateinit var tabAdapter: FavoriteTabAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabAdapter = FavoriteTabAdapter(view.context, activity!!.supportFragmentManager)
        view.favorite_view_pager.adapter = tabAdapter
        view.favorite_tab_layout.setupWithViewPager(view.favorite_view_pager)
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }

}