package com.mercadolibre.android.andesui.feedbackinapp.hierarchy

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.hierarchy.*
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.feedbackinapp.AndesFeedbackInApp
import com.mercadolibre.android.andesui.typeface.getFontOrDefault


/**
 * Defines all hierarchy related properties that an [AndesFeedbackInApp] needs to be drawn properly.
 * Those properties change depending on the hierarchy of the button.
 *
 */
internal sealed class AndesFeedbackInAppHierarchyInterface {
    /**
     * Returns a [Drawable] that contains the color data for the button background.
     * This includes color of the bg for normal, pressed, hover and focused states.
     *
     * @param context needed for accessing some resources.
     * @param cornerRadius radius of the button corner. This is needed because it depends on the button size.
     * @return a [Drawable] that contains the color data for the button background.
     */
    abstract fun background(context: Context, cornerRadius: Float): Drawable

    /**
     * Returns a [ColorStateList] that contains the data for the text color.
     * We are using [ColorStateList] because text color depends on the button type. E.g. text color for enabled type is different
     * than for the disabled type.
     *
     * @param context needed for accessing some resources.
     * @return a [ColorStateList] that contains the data for the text color.
     */
    abstract fun textColor(context: Context): ColorStateList

}

/**
 * Implementation of [AndesFeedbackInAppHierarchyInterface] that returns the required data but personalized for the Loud Hierarchy,
 * according to Andes specifications.
 *
 */
internal object AndesLoudFeedbackInAppHierarchy : AndesFeedbackInAppHierarchyInterface() {
    override fun background(context: Context, cornerRadius: Float) = getConfiguredBackground(context, cornerRadius, createBackgroundColorConfigLoud())
    override fun textColor(context: Context) = getConfiguredTextColor(context, createTextColorConfigLoud())
}

/**
 * Implementation of [AndesFeedbackInAppHierarchyInterface] that returns the required data but personalized for the Quiet Hierarchy,
 * according to Andes specifications.
 *
 */
internal object AndesQuietFeedbackInAppHierarchy : AndesFeedbackInAppHierarchyInterface() {
    override fun background(context: Context, cornerRadius: Float): Drawable {
        return getConfiguredBackground(context, cornerRadius, createBackgroundColorConfigQuiet())
    }
    override fun textColor(context: Context) = getConfiguredTextColor(context, createTextColorConfigQuiet())
}

/**
 * Implementation of [AndesFeedbackInAppHierarchyInterface] that returns the required data but personalized for the Transparent Hierarchy,
 * according to Andes specifications.
 *
 */
internal object AndesTransparentFeedbackInAppHierarchy : AndesFeedbackInAppHierarchyInterface() {
    override fun background(context: Context, cornerRadius: Float): Drawable {
        return getConfiguredBackground(context, cornerRadius, createBackgroundColorConfigTransparent())
    }
    override fun textColor(context: Context) = getConfiguredTextColor(context, createTextColorConfigTransparent())
}
