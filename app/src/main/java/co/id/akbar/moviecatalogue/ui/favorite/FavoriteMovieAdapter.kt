package co.id.akbar.moviecatalogue.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.id.akbar.moviecatalogue.R
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.items_movies.view.*

class FavoriteMovieAdapter(
    private val listener: (MovieEntity) -> Unit
): PagedListAdapter<MovieEntity, FavoriteMovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteMovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_movies, parent, false))

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val favorite: MovieEntity? = getItem(position)

        favorite?.let {
            holder.itemView.tv_item_title.text = it.title
            holder.itemView.tv_item_description.text = it.description
            holder.itemView.tv_item_date.text = it.releaseDate

            Glide.with(holder.itemView.context)
                .load(Constants.IMG_URL + it.poster)
                .into(holder.itemView.img_poster)

            holder.itemView.setOnClickListener { listener(favorite) }
        }

    }

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
                oldItem.id == newItem.id
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

}

class FavoriteMovieViewHolder(view: View): RecyclerView.ViewHolder(view)