package com.test.myapplication.viewmodels

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.test.myapplication.ui.activities.DetailPhotoActivity

class DetailPhotoViewModel : ViewModel() {
    var photoUrl: String? = null
    var photoTitle: String? = null

    fun processIntent(intent: Intent) {
        photoUrl = intent.getStringExtra(DetailPhotoActivity.EXTRA_PHOTO_TITLE)
        photoTitle = intent.getStringExtra(DetailPhotoActivity.EXTRA_PHOTO_URL)
    }
}