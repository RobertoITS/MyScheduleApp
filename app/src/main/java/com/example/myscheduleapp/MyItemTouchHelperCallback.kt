package com.example.myscheduleapp

import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myscheduleapp.fragments.mytask.adapter.EventAdapter

class MyItemTouchHelperCallback(val callBackItemTouch: CallBackItemTouch) :
    ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        callBackItemTouch.itemTouchOnMode(viewHolder.adapterPosition
            , target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        callBackItemTouch.onSwiped(viewHolder, viewHolder.adapterPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG){
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        } else {
            val foregroundView: View = (viewHolder as EventAdapter.EventViewHolder).delete
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
        }
    }

    override fun onChildDrawOver(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder?,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState != ItemTouchHelper.ACTION_STATE_DRAG){
            val foregroundView: View = (viewHolder as EventAdapter.EventViewHolder).visibilityOnOff
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        val foregroundView: View = (viewHolder as EventAdapter.EventViewHolder).visibilityOnOff
        foregroundView.setBackgroundColor(ContextCompat.getColor(
            viewHolder.visibilityOnOff.context,
            R.color.white))
        getDefaultUIUtil().clearView(foregroundView)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (viewHolder != null){
            val foregroundView: View = (viewHolder as EventAdapter.EventViewHolder).visibilityOnOff
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG){
                foregroundView.setBackgroundColor(Color.LTGRAY)
            }
            getDefaultUIUtil().onSelected(foregroundView)
        }
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }
}