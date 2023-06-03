package test.coding.parkee.helper

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.run {
            if (parent.getChildAdapterPosition(view) == 0) {
                left = spaceSize
            }
            top = spaceSize
            right = spaceSize
            bottom = spaceSize
        }
    }
}