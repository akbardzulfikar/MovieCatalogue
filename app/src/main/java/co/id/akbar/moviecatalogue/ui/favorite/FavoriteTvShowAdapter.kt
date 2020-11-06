package co.id.akbar.moviecatalogue.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.id.akbar.moviecatalogue.R
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow
import co.id.akbar.moviecatalogue.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.items_tvshow.view.*

class FavoriteTvShowAdapter(
    private val listener: (TvShow) -> Unit
): PagedListAdapter<TvShow, FavoriteTvShowViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteTvShowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_tvshow, parent, false))

    override fun onBindViewHolder(holder: FavoriteTvShowViewHolder, position: Int) {
        val favorite: TvShow? = getItem(position)

        favorite?.let {
            holder.itemView.tv_item_title.text = it.title
            holder.itemView.tv_item_description.text = it.description
            holder.itemView.tv_item_date.text = it.firstAirDate

            Glide.with(holder.itemView.context)
                .load(Constants.IMG_URL + it.poster)
                .into(holder.itemView.img_poster)

            holder.itemView.setOnClickListener { listener(favorite) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow) =
                oldItem.id == newItem.id
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShow? = getItem(swipedPosition)
}

class FavoriteTvShowViewHolder(view: View): RecyclerView.ViewHolder(view)