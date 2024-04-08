package com.example.testprime.presentation.common.custom_view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.dot.prime.presentation.common.dpToPx
import com.example.testprime.R

class CustomButton (
    context: Context,
    attrs: AttributeSet?
) : AppCompatButton(context, attrs) {

    private var isClick = true
    private val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton)
    private val enabled = typeArray.getBoolean(R.styleable.CustomButton_button_enabled, true)
    private var colorEnabled = "#A9D992"
    private var colorDisabled = "#DDDDDD"
    private var radius = 30f

    init {
        this.textSize = 16F
        this.isAllCaps = false
        this.stateListAnimator = null
    }

    override fun setEnabled(enabled: Boolean) {
        if (enabled) {
            isClick = true
            this.typeface = ResourcesCompat.getFont(context, R.font.object_sans_heavy)

            val shape = GradientDrawable()
            shape.shape = GradientDrawable.RECTANGLE

            shape.setColor(Color.parseColor(colorEnabled))
            shape.cornerRadius = radius
            val rippleColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_button_press))
            val rippleDrawable = RippleDrawable(rippleColor, shape, null)
            this.background = rippleDrawable
        } else {
            isClick = false
            this.typeface = ResourcesCompat.getFont(context, R.font.object_sans_regular)

            val shape = GradientDrawable()
            shape.shape = GradientDrawable.RECTANGLE
            shape.setColor(Color.parseColor(colorDisabled))
            shape.cornerRadius = radius
            val rippleColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_button_press))
            val rippleDrawable = RippleDrawable(rippleColor, shape, null)
            this.background = rippleDrawable
        }
    }

    override fun isEnabled(): Boolean {
        return isClick
    }

    fun setParams(colorEnabled: String, colorDisabled: String? = null, radius:Int, colorText: String){
        this.setTextColor(Color.parseColor(colorText))
        this.colorEnabled = colorEnabled
        if (colorDisabled != null){
            this.colorDisabled = colorDisabled
        }
        this.radius = radius.dpToPx(context).toFloat()
        this.isEnabled = enabled
    }

}