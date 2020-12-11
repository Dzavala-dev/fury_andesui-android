package com.mercadolibre.android.andesui.feedbackinapp.size

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.feedbackinapp.AndesFeedbackInApp

/**
 * Defines all size related properties that an [AndesFeedbackInApp] needs to be drawn properly.
 * Those properties change depending on the size of the button.
 *
 */
@Suppress("TooManyFunctions")
internal interface AndesFeedbackInAppSizeInterface {
    /**
     * Returns a [Float] representing the text size to be used.
     *
     * @param context needed for accessing some resources.
     * @return a [Float] representing the text size to be used.
     */
    fun textSize(context: Context): Float

    /**
     * Returns a [Float] representing the height size to be used.
     *
     * @param context needed for accessing some resources.
     * @return a [Float] representing the height size to be used.
     */
    fun height(context: Context): Float

    /**
     * Returns an [Int] representing the left margin to be used.
     *
     * @param context needed for accessing some resources.
     * @return an [Int] representing the left margin to be used.
     */
    fun textLeftMargin(context: Context): Int

    /**
     * Returns an [Int] representing the right margin to be used.
     *
     * @param context needed for accessing some resources.
     * @return an [Int] representing the right margin to be used.
     */
    fun textRightMargin(context: Context): Int

    fun lateralPadding(context: Context): Int

    /**
     * Returns a [Float] representing the corner radius to be used.
     *
     * @param context needed for accessing some resources.
     * @return a [Float] representing the corner radius to be used.
     */
    fun cornerRadius(context: Context): Float


}

/**
 * Implementation of [AndesFeedbackInAppSizeInterface] that returns the required data but personalized for the Large Size,
 * according to Andes specifications.
 *
 */
@Suppress("TooManyFunctions")
internal class AndesLargeButtonSize : AndesFeedbackInAppSizeInterface {

    override fun textSize(context: Context) =
            context.resources.getDimension(R.dimen.andes_text_size_button_large)
    override fun height(context: Context) =
            context.resources.getDimension(R.dimen.andes_button_height_large)
    override fun textLeftMargin(context: Context) =
            context.resources.getDimension(R.dimen.andes_button_margin_large).toInt()
    override fun textRightMargin(context: Context) =
            context.resources.getDimension(R.dimen.andes_button_margin_large).toInt()
    override fun lateralPadding(context: Context) =
            context.resources.getDimension(R.dimen.andes_button_lateral_padding_large).toInt()
    override fun cornerRadius(context: Context) =
            context.resources.getDimension(R.dimen.andes_button_border_radius_large)

}
/**
 * Implementation of [AndesFeedbackInAppSizeInterface] that returns the required data
 * but personalized for the Medium Size,
 * according to Andes specifications.
 *
 */
@Suppress("TooManyFunctions")
internal class AndesMediumButtonSize : AndesFeedbackInAppSizeInterface {
    override fun textSize(context: Context) =
            context.resources.getDimension(R.dimen.andes_text_size_button_medium)
    override fun height(context: Context) =
            context.resources.getDimension(R.dimen.andes_button_height_medium)
    override fun textLeftMargin(context: Context) = 0
    override fun textRightMargin(context: Context) = 0
    override fun lateralPadding(context: Context) =
            context.resources.getDimension(R.dimen.andes_button_lateral_padding_medium).toInt()
    override fun cornerRadius(context: Context) =
            context.resources.getDimension(R.dimen.andes_button_border_radius_medium)
}

/**
 * Implementation of [AndesFeedbackInAppSizeInterface] that returns the required data but personalized for the Small Size,
 * according to Andes specifications.
 *
 */
@Suppress("TooManyFunctions")
    internal class AndesSmallButtonSize : AndesFeedbackInAppSizeInterface {
        override fun textSize(context: Context) =
                context.resources.getDimension(R.dimen.andes_text_size_button_small)
        override fun height(context: Context) =
                context.resources.getDimension(R.dimen.andes_button_height_small)
        override fun textLeftMargin(context: Context) = 0
        override fun textRightMargin(context: Context) = 0
        override fun lateralPadding(context: Context) = context.resources.getDimension(R.dimen.andes_button_lateral_padding_small).toInt()
        override fun cornerRadius(context: Context) =
                context.resources.getDimension(R.dimen.andes_button_border_radius_small)

    }