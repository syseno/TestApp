package com.test.myapplication.viewmodels

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailPostViewModel(get()) }
    viewModel { UserDetailViewModel(get()) }
    viewModel {DetailPhotoViewModel() }
}