package com.test.myapplication.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.myapplication.databinding.ItemPhotoBinding
import com.test.myapplication.models.Photo
import com.test.myapplication.ui.activities.DetailPhotoActivity

class PhotoAdapter(
    private val context: Context,
    private val photos: List<Photo>
) :
    RecyclerView.Adapter<PhotoAdapter.AlbumHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val itemBinding =
            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        val photo = photos[position]
        holder.bind(context, photo)
    }

    override fun getItemCount(): Int = photos.size

    class AlbumHolder(private val itemBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(context: Context, photo: Photo) {
            Glide.with(context)
                .load(photo.thumbnailUrl + ".jpg")
                .into(itemBinding.imageAlbum)

            itemBinding.photoView.setOnClickListener {
                val intent = Intent(context, DetailPhotoActivity::class.java)
                intent.putExtra(DetailPhotoActivity.EXTRA_PHOTO_URL, photo.title)
                intent.putExtra(DetailPhotoActivity.EXTRA_PHOTO_URL, photo.url + ".png")
                context.startActivity(intent)
            }
        }
    }
}