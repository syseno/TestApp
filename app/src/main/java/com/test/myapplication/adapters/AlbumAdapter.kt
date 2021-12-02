package com.test.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.databinding.ItemAlbumBinding
import com.test.myapplication.models.Album
import com.test.myapplication.models.Photo

class AlbumAdapter(
    private val context: Context,
    private val albums: List<Album>,
    private val photos: List<Photo>
) :
    RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val itemBinding =
            ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        val album = albums[position]
        holder.bind(context, album, photos)
    }

    override fun getItemCount(): Int = albums.size

    class AlbumHolder(private val itemBinding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(context: Context, album: Album, photos: List<Photo>) {
            itemBinding.titleAlbum.text = album.title
            itemBinding.photoRecyclerView.apply {
                val listPhoto = photos.filter { it.albumId == album.id }
                val commentAdapter = PhotoAdapter(context, listPhoto)
                layoutManager = LinearLayoutManager(context)
                adapter = commentAdapter
            }
        }
    }
}