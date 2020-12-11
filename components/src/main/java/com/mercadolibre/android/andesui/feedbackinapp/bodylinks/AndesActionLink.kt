package com.mercadolibre.android.andesui.feedbackinapp.bodylinks

import android.text.SpannableString

class AndesActionLink(val startIndex: Int, val endIndex: Int) {
    fun isValidRange(text: SpannableString): Boolean {
        return (startIndex >= 0 &&
                endIndex >= 0 &&
                startIndex <= endIndex &&
                endIndex <= text.length)
    }
}