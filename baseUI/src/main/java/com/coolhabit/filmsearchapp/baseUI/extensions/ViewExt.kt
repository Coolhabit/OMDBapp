package com.coolhabit.filmsearchapp.baseUI.extensions

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

fun View.backgroundColor(color: Int) {
    this.backgroundTintList = ColorStateList.valueOf(
        ContextCompat.getColor(
            this.context,
            color
        )
    )
}

fun TextView.textColor(color: Int) {
    this.setTextColor(
        ContextCompat.getColor(
            this.context,
            color
        )
    )
}

fun ImageView.booleanCheck(boolean: Boolean, trueImage: Int, falseImage: Int) {
    if (boolean) {
        this.setImageResource(trueImage)
    } else {
        this.setImageResource(falseImage)
    }
}
