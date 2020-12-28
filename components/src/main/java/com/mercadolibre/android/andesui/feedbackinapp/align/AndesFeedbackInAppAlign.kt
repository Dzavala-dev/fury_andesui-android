package com.mercadolibre.android.andesui.feedbackinapp.align

enum class AndesFeedbackInAppAlign {
    LEFT,
    RIGHT,
    CENTER;

    companion object {
        fun fromString(value: String): AndesFeedbackInAppAlign = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesFIAppAlign()

    private fun getAndesFIAppAlign(): AndesFeedbackInAppAlignInterface {
        return when (this) {
            LEFT -> AndesFeedbackInAppLeft
            RIGHT -> AndesFeedbackInAppRight
            CENTER -> AndesFeedbackInAppCenter
        }
    }
}
