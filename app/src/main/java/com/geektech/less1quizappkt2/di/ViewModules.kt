package com.geektech.less1quizappkt2.di

import com.geektech.less1quizappkt2.ui.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module { viewModel { ViewModel(get()) } }