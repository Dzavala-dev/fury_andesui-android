package com.mercadolibre.android.andesui.feedbackinapp.factory

import android.content.Context
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.feedbackinapp.align.AndesFeedbackInAppAlign


data class FeedbackInAppConfiguration(
        val feedbackInAppMessageText: String? = null,
        val showTopDivider: Boolean = false,
        val descriptionTextAlignment: AndesFeedbackInAppAlign = AndesFeedbackInAppAlign.CENTER,
        val deeplink: String?,
        val bodySize: Float,
        val buttonLabel: String? = null
)

@Suppress("TooManyFunctions")
internal object FeedbackInAppConfigurationFactory {

    fun create(context: Context, andesFeedbackInAppAttrs: FeedbackInAppAttrs): FeedbackInAppConfiguration {

        return with(andesFeedbackInAppAttrs) {
            FeedbackInAppConfiguration(

                    feedbackInAppMessageText = message,
                    showTopDivider = showTopDivider,
                    descriptionTextAlignment = descriptionTextAlignment,
                    deeplink = deeplink,
                    bodySize = resolveBodySize(context),
                    buttonLabel = buttonLabel
            )
        }
    }

    private fun resolveBodySize(context: Context) = context.resources.getDimension(R.dimen.andes_feedback_body)

}
