package com.mercadolibre.android.andesui.feedbackinapp.align

enum class AndesFIAppAlign {
    LEFT,
    RIGHT,
    CENTER;

    companion object {
        fun fromString(value: String): AndesFIAppAlign = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesFIAppAlign()

    private fun getAndesFIAppAlign(): AndesFIAppAlignInterface {
        return when (this) {
            LEFT -> AndesFIALeft
            RIGHT -> AndesFIARight
            CENTER -> AndesFIACenter
        }
    }
}
