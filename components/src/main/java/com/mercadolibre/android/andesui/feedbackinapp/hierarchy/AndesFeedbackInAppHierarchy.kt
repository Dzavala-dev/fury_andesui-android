package com.mercadolibre.android.andesui.feedbackinapp.hierarchy

import com.mercadolibre.android.andesui.feedbackinapp.AndesFeedbackInApp

/**
 * Utility class that does two things: Defines the possible hierarchies an [AndesFeedbackInApp] can take because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 *
 * @property hierarchy Possible hierarchies that an [AndesFeedbackInApp] may take.
 */
enum class AndesFeedbackInAppHierarchy {
    TRANSPARENT,
    QUIET,
    LOUD;

    companion object {
        fun fromString(value: String): AndesFeedbackInAppHierarchy = valueOf(value.toUpperCase())
    }

    internal val hierarchy get() = getAndesFeedbackInAppHierarchy()

    private fun getAndesFeedbackInAppHierarchy(): AndesFeedbackInAppHierarchyInterface {
        return when (this) {
            TRANSPARENT -> AndesTransparentFeedbackInAppHierarchy
            QUIET -> AndesQuietFeedbackInAppHierarchy
            LOUD -> AndesLoudFeedbackInAppHierarchy
        }
    }
}