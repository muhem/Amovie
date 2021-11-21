package com.erwin2687.amovie.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erwin2687.amovie.BuildConfig.IMAGE_URL
import com.erwin2687.amovie.R
import com.erwin2687.amovie.model.Movie
import com.erwin2687.amovie.view.DetailMovieActivity
import com.erwin2687.amovie.view.MainActivity
import kotlinx.android.synthetic.main.inflater_film.view.*

class MovieAdapter internal constructor(private var movieList: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

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
        fun bind(movie: Movie) {
            with(itemView) {
                tv_film_title.text = movie.title
                Glide.with(itemView).load(IMAGE_URL + movie.poster)
                    .into(iv_film_poster)
            }
            itemView.setOnClickListener {
                val mIntent = Intent(itemView.context, DetailMovieActivity::class.java)
                mIntent.putExtra(MainActivity.DATA_EXTRA, movie.id)
                itemView.context.startActivity(mIntent)
            }
        }
    }
}