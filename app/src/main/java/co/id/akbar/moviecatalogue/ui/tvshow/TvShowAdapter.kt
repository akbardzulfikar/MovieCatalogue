package co.id.akbar.moviecatalogue.ui.tvshow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.id.akbar.moviecatalogue.R
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow
import co.id.akbar.moviecatalogue.utils.Constants
import co.id.akbar.moviecatalogue.utils.year
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.items_tvshow.view.*


class TvShowAdapter(
    private val context: Context,
    private val listener: (TvShow) -> Unit): RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private var mTvShows: MutableList<TvShow> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.items_tvshow, parent, false))

    override fun getItemCount()= mTvShows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(mTvShows[position], listener)
    }

    fun setTvShows(tvShows: List<TvShow>) {
        mTvShows.clear()
        mTvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindView(tv: TvShow, listener: (TvShow) -> Unit) {
            Glide.with(itemView.context)
                .load(Constants.IMG_URL + tv.poster)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(itemView.img_poster)

            itemView.tv_item_title.text = tv.title
            itemView.tv_item_date.text = tv.firstAirDate.year()
            itemView.tv_item_description.text = tv.description

            itemView.setOnClickListener { listener(tv) }
        }

    }
}