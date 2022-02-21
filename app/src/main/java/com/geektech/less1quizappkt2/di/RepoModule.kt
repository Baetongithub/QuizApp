package com.geektech.less1quizappkt2.di

import com.geektech.less1quizappkt2.ui.main.Repository
import org.koin.dsl.module

val repoModule = module { single { Repository(get()) } }