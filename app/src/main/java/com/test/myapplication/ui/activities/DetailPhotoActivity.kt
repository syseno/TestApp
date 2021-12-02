package com.test.myapplication.ui.activities

import com.bumptech.glide.Glide
import com.test.myapplication.base.BaseActivity
import com.test.myapplication.databinding.ActivityDetailPhotoBinding
import com.test.myapplication.viewmodels.DetailPhotoViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DetailPhotoActivity : BaseActivity<ActivityDetailPhotoBinding, DetailPhotoViewModel>() {
    override fun getViewBinding() = ActivityDetailPhotoBinding.inflate(layoutInflater)

    override fun getInjectViewModel(): DetailPhotoViewModel = getViewModel()

    override fun setupViews() {
        viewModel.processIntent(intent)

        binding.photoTitle.text = viewModel.photoTitle
        Glide.with(this)
            .load(viewModel.photoUrl)
            .into(binding.photoImage)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        const val EXTRA_PHOTO_URL = "photo_url"
        const val EXTRA_PHOTO_TITLE = "photo_title"
    }
}