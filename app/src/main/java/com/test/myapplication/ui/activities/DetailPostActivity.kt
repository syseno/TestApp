package com.test.myapplication.ui.activities

import android.content.Intent
import android.graphics.Paint
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.myapplication.R
import com.test.myapplication.adapters.CommentAdapter
import com.test.myapplication.base.BaseActivity
import com.test.myapplication.databinding.ActivityDetailPostBinding
import com.test.myapplication.enums.UIViewState
import com.test.myapplication.viewmodels.DetailPostViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DetailPostActivity : BaseActivity<ActivityDetailPostBinding, DetailPostViewModel>() {
    override fun getViewBinding() = ActivityDetailPostBinding.inflate(layoutInflater)

    override fun getInjectViewModel(): DetailPostViewModel = getViewModel()

    override fun setupViews() {
        viewModel.processIntent(intent)
        setupCreatedPostView()
        registerObserver()
        viewModel.getPost()
    }

    private fun setupCreatedPostView() {
        binding.createdPost.paintFlags = binding.createdPost.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.createdPost.setOnClickListener {
            val intent = Intent(this, UserDetailActivity::class.java)
            intent.putExtra(UserDetailActivity.EXTRA_USER_ID, viewModel.userId)
            startActivity(intent)
        }
    }
    private fun registerObserver() {
        viewModel.uiState.observe(this, {
            when (it) {
                UIViewState.LOADING -> {
                    binding.uiStateView.apply {
                        isVisible = true
                        setAnimation(R.raw.loading_state_animation)
                    }
                }
                UIViewState.SUCCESS -> {
                    binding.uiStateView.isVisible = false
                }
                UIViewState.ERROR -> {
                    binding.uiStateView.apply {
                        isVisible = true
                        setAnimation(R.raw.empty_state_animation)
                    }
                }
            }

        })

        viewModel.post.observe(this, {
            binding.titlePost.text = it.title
            binding.descriptionPost.text = it.body
            binding.createdPost.text = String.format(
                getString(R.string.label_post_created_by),
                viewModel.userName
            )
            binding.titleComment.text = getString(R.string.title_comment_section)
            viewModel.getComments()
        })

        viewModel.comments.observe(this, {
            binding.commentRecyclerView.apply {
                val commentAdapter = CommentAdapter(it)
                layoutManager = LinearLayoutManager(this@DetailPostActivity)
                adapter = commentAdapter
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}