package co.id.akbar.moviecatalogue.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import co.id.akbar.moviecatalogue.R

class FavoriteTabAdapter(private val ctx: Context, fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvshow)
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvFragment()
            else -> FavoriteMovieFragment()
        }
    }

    override fun getCount(): Int = TAB_TITLES.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> ctx.getString(R.string.movies)
            1 -> ctx.getString(R.string.tvshow)
            else -> null
        }
    }
}