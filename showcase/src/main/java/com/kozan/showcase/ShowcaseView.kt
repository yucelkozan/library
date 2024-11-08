package com.kozan.showcase

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class ShowcaseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var targetView: View? = null

    fun setTarget(view: View) {
        targetView = view
        invalidate()  // View'i yeniden çizmek için
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        targetView?.let {
            // Öne çıkarılan view'in etrafında bir highlight çizin
            val paint = Paint().apply {
                color = Color.RED
                style = Paint.Style.STROKE
                strokeWidth = 10f
            }
            val rect = Rect()
            it.getGlobalVisibleRect(rect)
            canvas.drawRect(rect, paint)
        }
    }
}
