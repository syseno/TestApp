package com.test.myapplication.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

fun <T> ViewModel.mutableLiveDataOf() = MutableLiveData<T>()