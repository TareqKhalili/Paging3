package com.example.paging.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.paging.R
import com.example.paging.data.TmdbMovie
import com.example.paging.databinding.ListItemTmdbMovieBinding

class TmdbMovieAdapter :
    PagingDataAdapter<TmdbMovie, TmdbMovieAdapter.MovieViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ListItemTmdbMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }

    class MovieViewHolder(private val binding: ListItemTmdbMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: TmdbMovie) {
            binding.apply {
                Glide.with(itemView)
                    .load(movie.backdropUrl)
                    .fitCenter()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)

                movieViewTitle.text = movie.title
                rating.text = movie.vote_average
                review.text = movie.overview
            }
        }

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<TmdbMovie>() {
            override fun areItemsTheSame(oldItem: TmdbMovie, newItem: TmdbMovie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TmdbMovie, newItem: TmdbMovie): Boolean =
                oldItem == newItem
        }
    }
}