package com.example.myscheduleapp

import androidx.recyclerview.widget.RecyclerView

interface CallBackItemTouch {
    fun itemTouchOnMode (oldPosition: Int, newPosition: Int)
    fun onSwiped (viewHolder: RecyclerView.ViewHolder, position: Int)
}