package com.mercadolibre.android.andesui.feedbackinapp


import android.os.Build
import android.support.constraint.ConstraintLayout
import android.text.SpannableString
import android.text.style.ClickableSpan
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.listener.RequestLoggingListener
import com.facebook.soloader.SoLoader
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.message.AndesMessage
import com.mercadolibre.android.andesui.message.bodylinks.AndesBodyLink
import com.mercadolibre.android.andesui.message.bodylinks.AndesBodyLinks
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.type.AndesMessageType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesFeedbackInAppTest {
    private var context = RuntimeEnvironment.application
    private lateinit var andesButton: AndesFeedbackInApp
    private lateinit var andesMessage: AndesMessage

    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            SoLoader.setInTestMode()
        }
    }

    @Before
    fun setUp() {
        val requestListeners = setOf<RequestListener>(RequestLoggingListener())
        val config = ImagePipelineConfig.newBuilder(context)
                // other setters
                .setRequestListeners(requestListeners)
                .build()
        Fresco.initialize(context, config)
        FLog.setMinimumLoggingLevel(FLog.VERBOSE)
    }

    @Test
    fun `Hierarchy and size constructor`() {
        val textParams = andesButton.textComponent.layoutParams as ConstraintLayout.LayoutParams

        assertEquals(andesButton.textComponent.textSize, 14F)
        assertEquals(textParams.goneStartMargin, 0)
        assertEquals(textParams.goneEndMargin, 0)
        assertEquals(andesButton.paddingRight, 12)
        assertEquals(andesButton.paddingLeft, 12)

    }

    @Test
    fun `Hierarchy, size and icon constructor`() {
        val textParams = andesButton.textComponent.layoutParams as ConstraintLayout.LayoutParams


        assertEquals(andesButton.textComponent.textSize, 16F)
        assertEquals(textParams.marginStart, 12)
        assertEquals(textParams.goneEndMargin, 8)
        assertEquals(andesButton.paddingRight, 16)
        assertEquals(andesButton.paddingLeft, 16)

    }

    @Test
    fun `Hierarchy, size and right icon constructor`() {
        val textParams = andesButton.textComponent.layoutParams as ConstraintLayout.LayoutParams


        assertEquals(andesButton.textComponent.textSize, 16F)
        assertEquals(textParams.marginEnd, 12)
        assertEquals(textParams.goneStartMargin, 8)
        assertEquals(andesButton.paddingRight, 16)
        assertEquals(andesButton.paddingLeft, 16)

    }

    @Test
    fun `Body Links`() {
        var indexSelected = -1

        andesMessage = AndesMessage(context, AndesMessageHierarchy.LOUD, AndesMessageType.SUCCESS,
                "This is a body message", "Title", true, null)

        val links = listOf(AndesBodyLink(0, 10))
        andesMessage.bodyLinks = AndesBodyLinks(links, listener = {
            indexSelected = it
        })

        (andesMessage.bodyComponent.text as? SpannableString)?.let {
            it.getSpans(0, 10, ClickableSpan::class.java)[0].onClick(andesMessage.bodyComponent)
        }

        assertEquals(indexSelected, 0)
    }



}