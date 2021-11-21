package com.erwin2687.amovie.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erwin2687.amovie.BuildConfig.IMAGE_URL
import com.erwin2687.amovie.R
import com.erwin2687.amovie.model.TvShow
import com.erwin2687.amovie.view.DetailTvActivity
import com.erwin2687.amovie.view.MainActivity
import kotlinx.android.synthetic.main.inflater_film.view.*

class TvShowAdapter internal constructor(private var movieList: ArrayList<TvShow>) :
    RecyclerView.Adapter<TvShowAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.inflater_film, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tv: TvShow) {
            with(itemView) {
                tv_film_title.text = tv.title
                Glide.with(itemView).load(IMAGE_URL + tv.poster)
                    .into(iv_film_poster)
            }
            itemView.setOnClickListener {
                val mIntent = Intent(itemView.context, DetailTvActivity::class.java)
                mIntent.putExtra(MainActivity.DATA_EXTRA, tv.id)
                itemView.context.startActivity(mIntent)
            }
        }
    }
}