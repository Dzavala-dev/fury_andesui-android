package com.mercadolibre.android.andesui.feedbackinapp.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.feedbackinapp.size.AndesFeedbackInAppSize
import com.mercadolibre.android.andesui.feedbackinapp.hierarchy.AndesFIAppHierarchy
import com.mercadolibre.android.andesui.feedbackinapp.align.AndesFIAppAlign
import com.mercadolibre.android.andesui.feedbackinapp.status.AndesActionStatus
import com.mercadolibre.android.andesui.feedbackinapp.type.AndesFIAType
import com.mercadolibre.android.andesui.feedbackinapp.bodylinks.AndesActionLinks

/**
 * The data class that contains the public components of the button.
 */
internal data class FeedbackInAppAttrs(
        val andesFIAHierarchy: AndesFIAppHierarchy,
        val andesFIAType: AndesFIAType,
        val descriptionText: String?,
        val descriptionTextAlign: AndesFIAppAlign,
        val andesActionStatus: AndesActionStatus,
        val andesFIASize: AndesFeedbackInAppSize,
        val showTopDivider: Boolean,
        val bodyLinks: AndesActionLinks?

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

    private const val ANDES_FIA_STATUS_SELECTED = "2000"
    private const val ANDES_FIA_STATUS_UNSELECTED = "2001"
    private const val ANDES_FIA_STATUS_UNDEFINED = "2002"

    private const val ANDES_FIA_TYPE_IDLE = "3000"
    private const val ANDES_FIA_TYPE_DISABLED = "3001"
    private const val ANDES_FIA_TYPE_ERROR = "3002"


    fun parse(context: Context, attr: AttributeSet?): FeedbackInAppAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesFIA)

        val align = when (typedArray.getString(R.styleable.AndesFIA_bodyAlign)) {
            ANDES_FIA_ALIGN_LEFT -> AndesFIAppAlign.LEFT
            ANDES_FIA_ALIGN_RIGHT -> AndesFIAppAlign.RIGHT
            ANDES_FIA_ALIGN_CENTER -> AndesFIAppAlign.CENTER
            else -> AndesFIAppAlign.CENTER
        }

        val hierarchy = when (typedArray.getString(R.styleable.AndesFIA_andesFIAHierarchy)) {
            ANDES_FIA_HIERARCHY_LOUD -> AndesFIAppHierarchy.LOUD
            ANDES_FIA_HIERARCHY_QUIET -> AndesFIAppHierarchy.QUIET
            ANDES_FIA_HIERARCHY_TRANSPARENT -> AndesFIAppHierarchy.TRANSPARENT
            else -> AndesFIAppHierarchy.LOUD
        }

        val status = when (typedArray.getString(R.styleable.AndesFIA_andesActionStatus)) {
            ANDES_FIA_STATUS_SELECTED -> AndesActionStatus.SELECTED
            ANDES_FIA_STATUS_UNSELECTED -> AndesActionStatus.UNSELECTED
            ANDES_FIA_STATUS_UNDEFINED -> AndesActionStatus.UNDEFINED
            else -> AndesActionStatus.UNSELECTED
        }

        val size = when (typedArray.getString(R.styleable.AndesFIA_andesFIASize)) {
            ANDES_FIA_SIZE_LARGE -> AndesFeedbackInAppSize.LARGE
            ANDES_FIA_SIZE_MEDIUM -> AndesFeedbackInAppSize.MEDIUM
            ANDES_FIA_SIZE_SMALL -> AndesFeedbackInAppSize.SMALL
            else -> AndesFeedbackInAppSize.MEDIUM
        }

        val type = when (typedArray.getString(R.styleable.AndesFIA_andesFIAType)) {
            ANDES_FIA_TYPE_IDLE -> AndesFIAType.IDLE
            ANDES_FIA_TYPE_DISABLED -> AndesFIAType.DISABLED
            ANDES_FIA_TYPE_ERROR -> AndesFIAType.ERROR
            else -> AndesFIAType.IDLE
        }

        val validatedStatus = if (type == AndesFIAType.ERROR) {
            AndesActionStatus.UNSELECTED
        } else {
            status
        }

        return FeedbackInAppAttrs(
                andesFIAHierarchy = AndesFIAppHierarchy.TRANSPARENT,
                andesFIAType = type,
                descriptionText = typedArray.getString(R.styleable.AndesFIA_andesFIABodyText),
                descriptionTextAlign = align,
                andesActionStatus = validatedStatus,
                andesFIASize = size,
                showTopDivider = typedArray.getBoolean(R.styleable.AndesFIA_andesFIAShowTopDivider, false),
                bodyLinks = null
        ).also { typedArray.recycle() }
    }
}