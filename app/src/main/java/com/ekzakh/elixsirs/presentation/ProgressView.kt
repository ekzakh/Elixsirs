package com.ekzakh.elixsirs.presentation

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class ProgressView : ProgressBar, MyView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}
