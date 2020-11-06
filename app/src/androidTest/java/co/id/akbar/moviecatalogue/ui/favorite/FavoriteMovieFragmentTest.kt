package co.id.akbar.moviecatalogue.ui.favorite

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.id.akbar.moviecatalogue.R
import co.id.akbar.moviecatalogue.testing.SingleFragmentActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteMovieFragmentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<SingleFragmentActivity>(SingleFragmentActivity::class.java)
    private var fragment = FavoriteMovieFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(fragment)
    }

    @Test
    fun loadFavoriteMovies() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<FavoriteMovieViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.fabFavorite))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}