package com.mercadolibre.android.andesui.feedbackinapp.hierarchy

import com.mercadolibre.android.andesui.feedbackinapp.AndesFeedbackInApp

/**
 * Utility class that does two things: Defines the possible hierarchies an [AndesFeedbackInApp] can take because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * You ask me with, let's say 'QUIET', and then I'll give you a proper implementation of that hierarchy.
 *
 * @property hierarchy Possible hierarchies that an [AndesFeedbackInApp] may take.
 */
enum class AndesFIAppHierarchy {
    QUIET,
    LOUD,
    TRANSPARENT;

    companion object {
        fun fromString(value: String): AndesFIAppHierarchy = valueOf(value.toUpperCase())
    }

    internal val hierarchy get() = getAndesFIAppHierarchy()

    private fun getAndesFIAppHierarchy(): AndesFIAppHierarchyInterface {
        return when (this) {
            QUIET -> AndesQuietFIAHierarchy
            LOUD -> AndesLoudFIAHierarchy
            TRANSPARENT -> AndesTransparentFIAHierarchy
        }
    }
}