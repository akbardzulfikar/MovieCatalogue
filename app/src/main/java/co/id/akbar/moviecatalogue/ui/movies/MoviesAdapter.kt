package co.id.akbar.moviecatalogue.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.id.akbar.moviecatalogue.R
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.utils.Constants
import co.id.akbar.moviecatalogue.utils.year
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.items_movies.view.*


class MoviesAdapter(
    private val context: Context,
    private val listener: (MovieEntity) -> Unit
): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var mMovies: MutableList<MovieEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.items_movies, parent, false))

    fun setMovies(movies: List<MovieEntity>) {
        mMovies.clear()
        mMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun getItemCount() = mMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(mMovies[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindItem(movie: MovieEntity, listener: (MovieEntity) -> Unit) {
            Glide.with(itemView.context)
                .load(Constants.IMG_URL + movie.poster)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(itemView.img_poster)

            itemView.tv_item_title.text = movie.title
            itemView.tv_item_date.text = movie.releaseDate.year()
            itemView.tv_item_description.text = movie.description

            itemView.setOnClickListener { listener(movie) }
        }
    }
}