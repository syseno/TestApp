package com.test.myapplication.ui.activities

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.myapplication.R
import com.test.myapplication.adapters.AlbumAdapter
import com.test.myapplication.base.BaseActivity
import com.test.myapplication.databinding.ActivityUserDetailBinding
import com.test.myapplication.enums.UIViewState
import com.test.myapplication.viewmodels.UserDetailViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class UserDetailActivity : BaseActivity<ActivityUserDetailBinding, UserDetailViewModel>() {
    override fun getViewBinding() = ActivityUserDetailBinding.inflate(layoutInflater)

    override fun getInjectViewModel(): UserDetailViewModel = getViewModel()

    override fun setupViews() {
        viewModel.processIntent(intent)
        registerObserver()
        viewModel.getPhotos()
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

        viewModel.photos.observe(this, {
            viewModel.getUser()
        })

        viewModel.user.observe(this, {
            binding.titleName.text = it.name
            binding.addressText.text = String.format(
                getString(R.string.label_address_of_user),
                it.address?.street,
                it.address?.city
            )
            binding.emailText.text = it.email
            binding.companyText.text = it.company?.name
            binding.albumsSectionTitle.text = getString(R.string.title_albums_section)

            viewModel.getAlbumsByUser()
        })

        viewModel.albums.observe(this, {
            binding.albumRecyclerView.apply {
                val photoAdapter =
                    viewModel.photos.value?.let { it1 -> AlbumAdapter(context, it.toList(), it1.toList()) }
                layoutManager = LinearLayoutManager(this@UserDetailActivity)
                adapter = photoAdapter
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        const val EXTRA_USER_ID = "user_name"
    }
}