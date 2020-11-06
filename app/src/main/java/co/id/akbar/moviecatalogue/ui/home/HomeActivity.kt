package co.id.akbar.moviecatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import co.id.akbar.moviecatalogue.R
import co.id.akbar.moviecatalogue.ui.favorite.FavoriteFragment
import co.id.akbar.moviecatalogue.ui.movies.MoviesFragment
import co.id.akbar.moviecatalogue.ui.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_home.*

const val SELECTED_MENU = "selected_menu"

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        nav_view.setOnNavigationItemSelectedListener {
            val fragment: Fragment? = when (it.itemId) {
                R.id.action_movie -> MoviesFragment.newInstance()
                R.id.action_tv -> TvShowFragment.newInstance()
                R.id.action_favorite -> FavoriteFragment.newInstance()
                else -> null
            }

            if(fragment != null) {
                supportFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment)
                    .commit()

            }

            true
        }

        if (savedInstanceState != null) {
            savedInstanceState.getInt(SELECTED_MENU)
        } else {
            nav_view.selectedItemId = R.id.action_movie
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt(SELECTED_MENU, nav_view.selectedItemId)
    }
}
