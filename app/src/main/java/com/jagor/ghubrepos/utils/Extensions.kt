package com.jagor.ghubrepos.utils

import android.view.MotionEvent
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jagor.ghubrepos.R

fun EditText.onDrawableClick(onClicked: (view: EditText) -> Unit) {
    this.setOnTouchListener { v, event ->
        var clicked = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingRight) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                clicked = true
            }
        }
        clicked
    }
}

fun ImageView.loadImage(url: String) {
    val options = RequestOptions()
        .placeholder(R.drawable.ic_owner_avatar_placeholder)
        .error(R.mipmap.ic_launcher)
    Glide.with(this.context).setDefaultRequestOptions(options).load(url).into(this)
}