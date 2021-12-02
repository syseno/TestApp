package com.test.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.databinding.ItemCommentBinding
import com.test.myapplication.models.Comment

class CommentAdapter(private val posts: List<Comment>) :
    RecyclerView.Adapter<CommentAdapter.CommentHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val itemBinding =
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val comment = posts[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int = posts.size

    class CommentHolder(private val itemBinding: ItemCommentBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(comment: Comment) {
            itemBinding.bodyComment.text = comment.body
            itemBinding.createdComment.text = comment.name
        }
    }
}