package com.test.myapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<B : ViewBinding, VM : ViewModel> : AppCompatActivity() {
    lateinit var binding: B
    lateinit var viewModel: VM

    abstract fun getViewBinding(): B
    abstract fun getInjectViewModel(): VM

    abstract fun setupViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getInjectViewModel()
        binding = getViewBinding()
        setContentView(binding.root)
        setupViews()
    }
}