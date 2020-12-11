package com.mercadolibre.android.andesui.feedbackinapp.hierarchy

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.hierarchy.BackgroundColorConfig
import com.mercadolibre.android.andesui.button.hierarchy.createBackgroundColorConfigLoud
import com.mercadolibre.android.andesui.button.hierarchy.createBackgroundColorConfigTransparent
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.icons.IconProvider
import com.mercadolibre.android.andesui.feedbackinapp.type.AndesFIATypeInterface
import com.mercadolibre.android.andesui.typeface.getFontOrDefault
import com.mercadolibre.android.andesui.utils.buildColoredAndesBitmapDrawable
import com.mercadolibre.android.andesui.feedbackinapp.AndesFeedbackInApp
import com.mercadolibre.android.andesui.typeface.getFontOrDefault

/**
 * Defines all style related properties that an [AndesFeedbackInApp] needs to be drawn properly.
 * Those properties change depending on the style of the message.
 *
 */
@Suppress("TooManyFunctions")
internal sealed class AndesFIAppHierarchyInterface {
    /**
     * Returns a [Drawable] that contains the color data for the message background.
     * This includes color of the bg for normal, pressed, hover and focused states.
     *
     * @return a [Drawable] that contains the color data for the message background.
     */
    abstract fun backgroundColor(): AndesColor

    abstract fun backgroundColor(type: AndesFIATypeInterface): AndesColor

    /**
     * Returns a [ColorStateList] that contains the data for the text color.
     * We are using [ColorStateList] because text color depends on the message type. E.g. text color for enabled type is different
     * than for the disabled type.
     *
     * @return a [ColorStateList] that contains the data for the text color.
     */
    abstract fun textColor(): AndesColor

    /**
     * Returns an [Int] representing a @ColorInt that will be used when tinting the icon.
     *
     * @return an [Int] representing a @ColorInt that will be used when tinting the icon.
     */
    abstract fun dismissableIconColor(): AndesColor

    /**
     * Returns the [Drawable] that will be used as the dismiss icon.
     *
     * @param hierarchy needed to obtain the color of the dismiss icon.
     * @param context needed for accessing some resources.
     */
    fun dismissableIcon(hierarchy: AndesFIAppHierarchyInterface, context: Context) =
            buildColoredAndesBitmapDrawable(
                    IconProvider(context).loadIcon("andes_ui_close_20") as BitmapDrawable,
                    context,
                    color = hierarchy.dismissableIconColor()
            )

    /**
     * Returns the [Typeface] that should be used for the text inside the [AndesFeedbackInApp].
     *
     * @param context needed for accessing some resources. In this case, for accessing the kotlin extension defines for the context.
     * @return the [Typeface] that should be used for the text inside the [AndesFeedbackInApp].
     */
    fun bodyTypeface(context: Context): Typeface = context.getFontOrDefault(R.font.andes_font_regular)

    /**
     * Returns the background color that the icon will have. It's the color of the container in which the icon will live.
     *
     * @param type needed because the background color is intimately related to the type of the Message.
     * @return the background color that the icon will have.
     */

    abstract fun linkActionBackgroundColor(type: AndesFIATypeInterface): BackgroundColorConfig

    abstract fun linkActionTextColor(type: AndesFIATypeInterface): AndesColor

    abstract fun bodyLinkIsUnderLine(type: AndesFIATypeInterface): Boolean

    abstract fun bodyLinkTextColor(type: AndesFIATypeInterface): AndesColor

}

@Suppress("TooManyFunctions")
internal object AndesLoudFIAHierarchy : AndesFIAppHierarchyInterface() {
    override fun backgroundColor() = throw IllegalStateException("Loud message cannot be colored without an AndesMessageType")
    override fun backgroundColor(type: AndesFIATypeInterface) = backgroundColor()
    override fun textColor() = R.color.andes_white.toAndesColor()
    override fun dismissableIconColor() = R.color.andes_white.toAndesColor()

    override fun linkActionBackgroundColor(type: AndesFIATypeInterface) = type.linkActionColorConfig()

    override fun linkActionTextColor(type: AndesFIATypeInterface) = R.color.andes_white.toAndesColor() // TODO CHECK THIS

    override fun bodyLinkIsUnderLine(type: AndesFIATypeInterface) = true

    override fun bodyLinkTextColor(type: AndesFIATypeInterface) = R.color.andes_white.toAndesColor()
}

@Suppress("TooManyFunctions")
internal object AndesQuietFIAHierarchy : AndesFIAppHierarchyInterface() {
    override fun backgroundColor() = R.color.andes_gray_040.toAndesColor()
    override fun backgroundColor(type: AndesFIATypeInterface) = backgroundColor()
    override fun textColor() = R.color.andes_gray_800.toAndesColor()
    override fun dismissableIconColor() = R.color.andes_gray_450.toAndesColor()

    override fun linkActionBackgroundColor(type: AndesFIATypeInterface) = type.linkActionColorConfig()

    override fun linkActionTextColor(type: AndesFIATypeInterface) = R.color.andes_accent_color_500.toAndesColor()

    override fun bodyLinkIsUnderLine(type: AndesFIATypeInterface) = false

    override fun bodyLinkTextColor(type: AndesFIATypeInterface) = R.color.andes_accent_color_500.toAndesColor()
}

@Suppress("TooManyFunctions")
internal object AndesTransparentFIAHierarchy : AndesFIAppHierarchyInterface() {
    override fun backgroundColor() = R.color.andes_gray_040.toAndesColor()
    override fun backgroundColor(type: AndesFIATypeInterface) = backgroundColor()
    override fun textColor() = R.color.andes_gray_800.toAndesColor()
    override fun dismissableIconColor() = R.color.andes_gray_450.toAndesColor()

    override fun linkActionBackgroundColor(type: AndesFIATypeInterface) = type.linkActionColorConfig()

    override fun linkActionTextColor(type: AndesFIATypeInterface) = R.color.andes_accent_color_500.toAndesColor()

    override fun bodyLinkIsUnderLine(type: AndesFIATypeInterface) = false

    override fun bodyLinkTextColor(type: AndesFIATypeInterface) = R.color.andes_accent_color_500.toAndesColor()
}