package com.geektech.less1quizappkt2.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(res: Int): View {
    return LayoutInflater.from(this.context).inflate(res, this, false)
}