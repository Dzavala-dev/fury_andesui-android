package com.mercadolibre.android.andesui.feedbackinapp.factory

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.hierarchy.BackgroundColorConfig
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.feedbackinapp.hierarchy.AndesFIAppHierarchyInterface
import com.mercadolibre.android.andesui.feedbackinapp.type.AndesFIATypeInterface

internal data class FeedbackInAppConfiguration(
        val backgroundColor: AndesColor,
        val textColor: AndesColor,
        val descriptionText: String? = null,
        val andesFIASize: Float,
        val bodyTypeface: Typeface?,
        val isDismissable: Boolean,
        val dismissableIcon: Drawable?,
        val dismissableIconColor: AndesColor?,
        val linkActionText: String?,
        val linkActionBackgroundColor: BackgroundColorConfig,
        val linkActionTextColor: AndesColor,


        val bodyLinkIsUnderline: Boolean,
        val bodyLinkTextColor: AndesColor
)

@Suppress("TooManyFunctions")
internal object FeedbackInAppConfigurationFactory {

    fun create(context: Context, andesFIAppAttrs: FeedbackInAppAttrs): FeedbackInAppConfiguration {
        return with(andesFIAppAttrs) {
            FeedbackInAppConfiguration(

                    backgroundColor = resolveBackgroundColor(andesFIAHierarchy.hierarchy, andesFIAType.type),
                    descriptionText = descriptionText,
                    textColor = resolveTextColor(andesFIAHierarchy.hierarchy),
                    andesFIASize = resolveBodySize(context),
                    bodyTypeface = resolveBodyTypeface(andesFIAHierarchy.hierarchy, context),
                    isDismissable = showTopDivider,
                    dismissableIcon = resolveDismissableIcon(andesFIAHierarchy.hierarchy, context),
                    dismissableIconColor = resolveDismissableIconColor(andesFIAHierarchy.hierarchy),
                    linkActionText = null,

                    linkActionBackgroundColor = resolveLinkActionBackgroundColor(
                            andesFIAHierarchy.hierarchy,
                            andesFIAType.type
                    ),

                    linkActionTextColor = resolveLinkActionTextColor(
                            andesFIAHierarchy.hierarchy,
                            andesFIAType.type
                    ),

                    bodyLinkIsUnderline = resolveBodyLinkIsUnderline(andesFIAHierarchy.hierarchy, andesFIAType.type),
                    bodyLinkTextColor = resolveBodyLinkTextColor(andesFIAHierarchy.hierarchy, andesFIAType.type)
            )
        }
    }

    private fun resolveTextColor(hierarchy: AndesFIAppHierarchyInterface) = hierarchy.textColor()
    private fun resolveBackgroundColor(hierarchy: AndesFIAppHierarchyInterface, type: AndesFIATypeInterface) = hierarchy.backgroundColor(type)
    private fun resolveBodySize(context: Context) = context.resources.getDimension(R.dimen.andes_message_body)
    private fun resolveBodyTypeface(hierarchy: AndesFIAppHierarchyInterface, context: Context) = hierarchy.bodyTypeface(context)

    private fun resolveDismissableIcon(hierarchy: AndesFIAppHierarchyInterface, context: Context) = hierarchy.dismissableIcon(hierarchy, context)
    private fun resolveDismissableIconColor(hierarchy: AndesFIAppHierarchyInterface) = hierarchy.dismissableIconColor()

    private fun resolveLinkActionBackgroundColor(
            hierarchy: AndesFIAppHierarchyInterface,
            type: AndesFIATypeInterface
    ) = hierarchy.linkActionBackgroundColor(type)

    private fun resolveLinkActionTextColor(
            hierarchy: AndesFIAppHierarchyInterface,
            type: AndesFIATypeInterface
    ) = hierarchy.linkActionTextColor(type)

    private fun resolveBodyLinkIsUnderline(
            hierarchy: AndesFIAppHierarchyInterface,
            type: AndesFIATypeInterface
    ) = hierarchy.bodyLinkIsUnderLine(type)

    private fun resolveBodyLinkTextColor(
            hierarchy: AndesFIAppHierarchyInterface,
            type: AndesFIATypeInterface
    ) = hierarchy.bodyLinkTextColor(type)
}
