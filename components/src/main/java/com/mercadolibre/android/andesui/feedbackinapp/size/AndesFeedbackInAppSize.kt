package com.mercadolibre.android.andesui.feedbackinapp.size

import com.mercadolibre.android.andesui.feedbackinapp.AndesFeedbackInApp

/**
 * Utility class that does two things: Defines the possible sizes an [AndesFeedbackInApp] can take because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * You ask me with, let's say 'SMALL', and then I'll give you a proper implementation of that size.
 *
 * @property size Possible sizes that an [AndesFeedbackInApp] may take.
 */
enum class AndesFeedbackInAppSize {
    SMALL,
    MEDIUM,
    LARGE;

    companion object {
        fun fromString(value: String): AndesFeedbackInAppSize = valueOf(value.toUpperCase())
    }

    internal val size get() = getAndesFeedbackInAppSize()

    private fun getAndesFeedbackInAppSize(): AndesFeedbackInAppSizeInterface {
        return when (this) {
            SMALL -> AndesSmallButtonSize()
            MEDIUM -> AndesMediumButtonSize()
            LARGE -> AndesLargeButtonSize()
        }
    }
}