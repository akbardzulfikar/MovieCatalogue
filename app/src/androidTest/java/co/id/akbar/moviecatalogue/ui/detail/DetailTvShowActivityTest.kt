package co.id.akbar.moviecatalogue.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import co.id.akbar.moviecatalogue.R
import co.id.akbar.moviecatalogue.utils.DataDummy
import co.id.akbar.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailTvShowActivityTest {

    var dummyMovie = DataDummy.generateDummyTvShow()[0]

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<DetailMovieActivity> =
        object : ActivityTestRule<DetailMovieActivity>(DetailMovieActivity::class.java) {

            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, DetailMovieActivity::class.java)
                result.putExtra("tvShowId", dummyMovie.id)
                return result
            }
        }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadMovie() {
        Espresso.onView(withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvTitle))
            .check(ViewAssertions.matches(withText(dummyMovie.title)))
        Espresso.onView(withId(R.id.tvDescription))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvDescription))
            .check(ViewAssertions.matches(withText(dummyMovie.description)))
        Espresso.onView(withId(R.id.imgPoster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.imgBackdrop))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}