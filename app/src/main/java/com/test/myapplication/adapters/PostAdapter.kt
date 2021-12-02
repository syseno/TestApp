package com.test.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.R
import com.test.myapplication.databinding.ItemPostBinding
import com.test.myapplication.models.PostEntity

class PostAdapter(
    private val context: Context,
    private val posts: List<PostEntity>,
    val onItemClick: (PostEntity) -> Unit
) :
    RecyclerView.Adapter<PostAdapter.PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val itemBinding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val post = posts[position]
        holder.bind(context, post, onItemClick)
    }

    override fun getItemCount(): Int = posts.size

    class PostHolder(private val itemBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(context: Context, post: PostEntity, onItemClick: (PostEntity) -> Unit) {
            itemBinding.titlePost.text = post.title
            itemBinding.descriptionPost.text = post.body
            itemBinding.createdPost.text = String.format(
                context.getString(R.string.label_post_created_by_with_company_name),
                post.username,
                post.company?.name
            )
            itemBinding.postView.setOnClickListener {
                onItemClick.invoke(post)
            }
        }
    }
}