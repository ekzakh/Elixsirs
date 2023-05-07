package com.ekzakh.elixsirs.presentation

import android.content.Context
import android.util.AttributeSet
import com.github.johnnysc.coremvvm.presentation.adapter.MyImageView

class IconView : MyImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    override fun handleClick(listener: OnClickListener) = setOnClickListener(listener)
}
