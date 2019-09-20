package com.jesuraj.java.myfirebase

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String)=
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()