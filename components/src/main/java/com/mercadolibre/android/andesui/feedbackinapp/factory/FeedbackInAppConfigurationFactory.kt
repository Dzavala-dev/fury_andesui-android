package com.mercadolibre.android.andesui.feedbackinapp.factory

import android.content.Context
import android.text.style.BackgroundColorSpan
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.feedbackinapp.align.AndesFIAppAlign


internal data class FeedbackInAppConfiguration(
        val showTopDivider: Boolean?,
        val descriptionText: String? = null,
        val descriptionSize: Float,
        val descriptionTextAlignment: AndesFIAppAlign = AndesFIAppAlign.CENTER,
        val action: Boolean = true,
        val linkText: String? = null
)

@Suppress("TooManyFunctions")
internal object FeedbackInAppConfigurationFactory {

    fun create(context: Context, andesFeedbackInAppAttrs: FeedbackInAppAttrs): FeedbackInAppConfiguration {


        return with(andesFeedbackInAppAttrs) {
            FeedbackInAppConfiguration(

                    showTopDivider = showTopDivider,
                    descriptionText = descriptionText,
                    descriptionSize = resolveBodySize(context),
                    descriptionTextAlignment = descriptionTextAlignment,
                    action = andesFeedbackInAppAttrs.action,
                    linkText = linkText
            )
        }
    }

    private fun resolveBodySize(context: Context) = context.resources.getDimension(R.dimen.andes_message_body)

}
