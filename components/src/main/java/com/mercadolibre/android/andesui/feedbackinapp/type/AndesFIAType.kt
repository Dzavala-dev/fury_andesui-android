package com.mercadolibre.android.andesui.feedbackinapp.type

enum class AndesFIAType {
    IDLE,
    DISABLED,
    ERROR;

    companion object {
        fun fromString(value: String): AndesFIAType = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesFIAType()

    private fun getAndesFIAType(): AndesFIATypeInterface {
        return when (this) {
            IDLE -> AndesFIATypeIdle
            DISABLED -> AndesFIATypeDisabled
            ERROR -> AndesFIATypeError
        }
    }
}
