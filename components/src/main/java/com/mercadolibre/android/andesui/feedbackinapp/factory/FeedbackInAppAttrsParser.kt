package com.mercadolibre.android.andesui.feedbackinapp.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.feedbackinapp.hierarchy.AndesFeedbackInAppHierarchy
import com.mercadolibre.android.andesui.button.size.AndesButtonSize
import com.mercadolibre.android.andesui.feedbackinapp.align.AndesFIAppAlign
import com.mercadolibre.android.andesui.message.bodylinks.AndesBodyLinks

/**
 * The data class that contains the public components of the button.
 */
internal data class FeedbackInAppAttrs(
        val andesFeedbackInAppHierarchy: AndesFeedbackInAppHierarchy,
        val descriptionText: String?,
        val showTopDivider: Boolean?,
        //val descriptionSize: AndesButtonSize,
        val descriptionTextAlignment: AndesFIAppAlign = AndesFIAppAlign.CENTER,
        //val setBackgroundColor: String?,
        val action: Boolean = true,
val linkText: String?
)

/**
 * This object parse the attribute set and return an instance of AndesButtonAttrs to be used by AndesButton
 */
internal object FeedbackInAppAttrsParser {

    private const val ANDES_FIA_HIERARCHY_LOUD = "100"
    private const val ANDES_FIA_HIERARCHY_QUIET = "101"
    private const val ANDES_FIA_HIERARCHY_TRANSPARENT = "102"

    private const val ANDES_FIA_SIZE_LARGE = "200"
    private const val ANDES_FIA_SIZE_MEDIUM = "201"
    private const val ANDES_FIA_SIZE_SMALL = "202"

    private const val ANDES_FIA_ALIGN_LEFT = "1000"
    private const val ANDES_FIA_ALIGN_RIGHT = "1001"
    private const val ANDES_FIA_ALIGN_CENTER = "1002"


    fun parse(context: Context, attr: AttributeSet?): FeedbackInAppAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesFeedbackInApp)

        val align = when (typedArray.getString(R.styleable.AndesFeedbackInApp_andesFeedbackInAppAlign)) {
            ANDES_FIA_ALIGN_LEFT -> AndesFIAppAlign.LEFT
            ANDES_FIA_ALIGN_RIGHT -> AndesFIAppAlign.RIGHT
            ANDES_FIA_ALIGN_CENTER -> AndesFIAppAlign.CENTER
            else -> AndesFIAppAlign.CENTER
        }

        val hierarchy = when (typedArray.getString(R.styleable.AndesFeedbackInApp_andesFeedbackInAppHierarchy)) {
            ANDES_FIA_HIERARCHY_LOUD -> AndesFeedbackInAppHierarchy.LOUD
            ANDES_FIA_HIERARCHY_QUIET -> AndesFeedbackInAppHierarchy.QUIET
            ANDES_FIA_HIERARCHY_TRANSPARENT -> AndesFeedbackInAppHierarchy.TRANSPARENT
            else -> AndesFeedbackInAppHierarchy.TRANSPARENT
        }

        val size = when (typedArray.getString(R.styleable.AndesButton_andesButtonSize)) {
            ANDES_FIA_SIZE_LARGE -> AndesButtonSize.LARGE
            ANDES_FIA_SIZE_MEDIUM -> AndesButtonSize.MEDIUM
            ANDES_FIA_SIZE_SMALL -> AndesButtonSize.SMALL
            else -> AndesButtonSize.MEDIUM
        }

        return FeedbackInAppAttrs(
                andesFeedbackInAppHierarchy = hierarchy,
                descriptionText = typedArray.getString(R.styleable.AndesFeedbackInApp_andesFeedbackInAppBodyText),
                //descriptionSize = size,
                action = typedArray.getBoolean(R.styleable.AndesButton_andesButtonEnabled, true),
                descriptionTextAlignment = align,
                showTopDivider = typedArray.getBoolean(R.styleable.AndesFeedbackInApp_andesFIAShowTopDivider, false),
                //setBackgroundColor: String?
                linkText = typedArray.getString(R.styleable.AndesFeedbackInApp_andesFeedbackInAppButtonText)
        ).also { typedArray.recycle() }
    }
}