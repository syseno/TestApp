package com.test.myapplication.ui.activities

import android.content.Intent
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.myapplication.R
import com.test.myapplication.adapters.PostAdapter
import com.test.myapplication.base.BaseActivity
import com.test.myapplication.databinding.ActivityMainBinding
import com.test.myapplication.enums.UIViewState
import com.test.myapplication.models.PostEntity
import com.test.myapplication.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun getInjectViewModel(): MainViewModel = getViewModel()

    override fun setupViews() {
        registerObserver()
        viewModel.getUsers()
    }

    private fun registerObserver() {
        viewModel.uiState.observe(this, Observer {
            when (it) {
                UIViewState.LOADING -> {
                    binding.mainRecyclerView.isVisible = false
                    binding.uiStateView.apply {
                        isVisible = true
                        setAnimation(R.raw.loading_state_animation)
                    }
                }
                UIViewState.SUCCESS -> {
                    binding.mainRecyclerView.isVisible = true
                    binding.uiStateView.isVisible = false
                }
                UIViewState.ERROR -> {
                    binding.mainRecyclerView.isVisible = false
                    binding.uiStateView.apply {
                        isVisible = true
                        setAnimation(R.raw.empty_state_animation)
                    }
                }
            }

        })

        viewModel.users.observe(this, Observer {
            if (it.isNotEmpty()) {
                viewModel.getPosts()
            }
        })

        viewModel.postEntities.observe(this, Observer {
            val onItemClick = { post: PostEntity ->
                val intent = Intent(this@MainActivity, DetailPostActivity::class.java)
                intent.putExtra(EXTRA_POST_ID, post.id)
                intent.putExtra(EXTRA_USER_NAME, post.username)
                intent.putExtra(UserDetailActivity.EXTRA_USER_ID, post.userId)
                startActivity(intent)
            }
            binding.mainRecyclerView.apply {
                val postAdapter = PostAdapter(this@MainActivity, it.toList(), onItemClick)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = postAdapter
            }
        })
    }

    companion object {
        const val EXTRA_POST_ID = "post_id"
        const val EXTRA_USER_NAME = "user_name"
    }
}