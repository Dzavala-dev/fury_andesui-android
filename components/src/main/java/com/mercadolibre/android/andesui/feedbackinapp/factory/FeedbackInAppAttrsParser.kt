package com.mercadolibre.android.andesui.feedbackinapp.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.feedbackinapp.hierarchy.AndesFeedbackInAppHierarchy
import com.mercadolibre.android.andesui.button.size.AndesButtonSize
import com.mercadolibre.android.andesui.feedbackinapp.align.AndesFeedbackInAppAlign

/**
 * The data class that contains the public components of the button.
 */
internal data class FeedbackInAppAttrs(
        val message: String?,
        val showTopDivider: Boolean = false,
        val descriptionTextAlignment: AndesFeedbackInAppAlign = AndesFeedbackInAppAlign.CENTER,
        val feedbackInAppBackgroundColor: String,
        val deeplink: String?,
        val buttonLabel: String?
)

/**
 * This object parse the attribute set and return an instance of AndesButtonAttrs to be used by AndesButton
 */
internal object FeedbackInAppAttrsParser {

    private const val ANDES_FeedbackInApp_HIERARCHY_LOUD = "100"
    private const val ANDES_FeedbackInApp_HIERARCHY_QUIET = "101"
    private const val ANDES_FeedbackInApp_HIERARCHY_TRANSPARENT = "102"

    private const val ANDES_FeedbackInApp_SIZE_LARGE = "200"
    private const val ANDES_FeedbackInApp_SIZE_MEDIUM = "201"
    private const val ANDES_FeedbackInApp_SIZE_SMALL = "202"

    private const val ANDES_FeedbackInApp_ALIGN_LEFT = "1000"
    private const val ANDES_FeedbackInApp_ALIGN_RIGHT = "1001"
    private const val ANDES_FeedbackInApp_ALIGN_CENTER = "1002"


    fun parse(context: Context, attr: AttributeSet?): FeedbackInAppAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesFeedbackInApp)

        val align = when (typedArray.getString(R.styleable.AndesFeedbackInApp_andesFeedbackInAppAlign)) {
            ANDES_FeedbackInApp_ALIGN_LEFT -> AndesFeedbackInAppAlign.LEFT
            ANDES_FeedbackInApp_ALIGN_RIGHT -> AndesFeedbackInAppAlign.RIGHT
            ANDES_FeedbackInApp_ALIGN_CENTER -> AndesFeedbackInAppAlign.CENTER
            else -> AndesFeedbackInAppAlign.CENTER
        }

        val hierarchy = when (typedArray.getString(R.styleable.AndesFeedbackInApp_andesFeedbackInAppHierarchy)) {
            ANDES_FeedbackInApp_HIERARCHY_LOUD -> AndesFeedbackInAppHierarchy.LOUD
            ANDES_FeedbackInApp_HIERARCHY_QUIET -> AndesFeedbackInAppHierarchy.QUIET
            ANDES_FeedbackInApp_HIERARCHY_TRANSPARENT -> AndesFeedbackInAppHierarchy.TRANSPARENT
            else -> AndesFeedbackInAppHierarchy.TRANSPARENT
        }

        val size = when (typedArray.getString(R.styleable.AndesButton_andesButtonSize)) {
            ANDES_FeedbackInApp_SIZE_LARGE -> AndesButtonSize.LARGE
            ANDES_FeedbackInApp_SIZE_MEDIUM -> AndesButtonSize.MEDIUM
            ANDES_FeedbackInApp_SIZE_SMALL -> AndesButtonSize.SMALL
            else -> AndesButtonSize.MEDIUM
        }

        return FeedbackInAppAttrs(
                message = typedArray.getString(R.styleable.AndesFeedbackInApp_andesFeedbackInAppBodyText),
                showTopDivider = typedArray.getBoolean(R.styleable.AndesFeedbackInApp_andesFeedbackInAppShowTopDivider, false),
                descriptionTextAlignment = align,
                feedbackInAppBackgroundColor = "",
                deeplink = "",
                buttonLabel = typedArray.getString(R.styleable.AndesFeedbackInApp_andesFeedbackInAppButtonText)
        ).also { typedArray.recycle() }
    }
}