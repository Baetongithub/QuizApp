package com.geektech.less1quizappkt2.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.toast(txt: String) = Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
