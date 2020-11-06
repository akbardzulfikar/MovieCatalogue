package co.id.akbar.moviecatalogue.utils

import android.util.Log

import co.id.akbar.moviecatalogue.data.source.remote.network.response.MovieResponse
import co.id.akbar.moviecatalogue.data.source.remote.network.response.TvResponse
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

object DataDummy {

    private var gson = Gson()

    fun generateDummyMovies() =
        gson.fromJson(loadJSON("movies.json"), MovieResponse::class.java).results

    fun generateDummyTvShow() =
        gson.fromJson(loadJSON("tv.json"), TvResponse::class.java).results

    private fun loadJSON(fileSource: String): String? {
        var json: String? = null
        try {
            val input: InputStream = this.javaClass.classLoader!!.getResourceAsStream(fileSource) //context.assets.open(fileSource)
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = String(buffer, charset("UTF-8"))

        } catch (ex: IOException) {
            Log.e("Dummy", ex.localizedMessage)
        }

        return json
    }
}