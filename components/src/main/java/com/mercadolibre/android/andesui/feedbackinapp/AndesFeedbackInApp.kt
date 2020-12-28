package com.mercadolibre.android.andesui.feedbackinapp

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.feedbackinapp.align.AndesFeedbackInAppAlign
import com.mercadolibre.android.andesui.feedbackinapp.factory.FeedbackInAppAttrs
import com.mercadolibre.android.andesui.feedbackinapp.factory.FeedbackInAppAttrsParser
import com.mercadolibre.android.andesui.feedbackinapp.factory.FeedbackInAppConfiguration
import com.mercadolibre.android.andesui.feedbackinapp.factory.FeedbackInAppConfigurationFactory


@Suppress("TooManyFunctions")
class AndesFeedbackInApp : CardView {

    /**
     * Getter and setter for [message].
     */
    var message: String?
        get() = andesFeedbackInAppAttrs.message
        set(value) {
            andesFeedbackInAppAttrs = andesFeedbackInAppAttrs.copy(message = value)
            setupBodyComponent(createConfig())
        }

    /**
     * Getter and setter for [buttonLabel].
     */
    var buttonLabel: String?
        get() = andesFeedbackInAppAttrs.buttonLabel
        set(value) {
            andesFeedbackInAppAttrs = andesFeedbackInAppAttrs.copy(buttonLabel = value)
            setupLinkAction(createConfig())
        }

    private lateinit var feedbackContainer: ConstraintLayout
    lateinit var feedbackinappMessage: TextView
    private lateinit var feedbackdivider: View
    private lateinit var andesFeedbackInAppAttrs: FeedbackInAppAttrs
    private lateinit var linkAction: AndesButton


    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        throw IllegalStateException(
                "Constructor without parameters in Andes Message is not allowed. You must provide some attributes."
        )
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    @Suppress("unused", "LongParameterList")
    constructor(
            context: Context,
            message: String,
            showTopDivider: Boolean = ShowTopDivider_DEFAULT,
            descriptionTextAlignment: AndesFeedbackInAppAlign,
            feedbackInAppBackgroundColor: String,
            deeplink: String?,
            buttonLabel: String?

    ) : super(context) {
        initAttrs(message, showTopDivider, descriptionTextAlignment, feedbackInAppBackgroundColor, deeplink, buttonLabel)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesFeedbackInAppAttrs = FeedbackInAppAttrsParser.parse(context, attrs)
        setupComponents()
    }

    @Suppress("LongParameterList")
    private fun initAttrs(
            message: String,
            showTopDivider: Boolean = ShowTopDivider_DEFAULT,
            descriptionTextAlignment: AndesFeedbackInAppAlign,
            feedbackInAppBackgroundColor: String,
            deeplink: String?,
            buttonLabel: String?

    ) {
        andesFeedbackInAppAttrs = FeedbackInAppAttrs(message, showTopDivider, descriptionTextAlignment, feedbackInAppBackgroundColor, deeplink, buttonLabel)
        val config = FeedbackInAppConfigurationFactory.create(context, andesFeedbackInAppAttrs)
        setupComponents()
    }

    /**
     * Responsible for setting up all properties of each component that is part of this message.
     * Is like a choreographer ;)
     *
     */
    private fun setupComponents() {

        initComponents()
        setupViewId()
    }

    /**
     * Creates all the views that are part of this message.
     * After a view is created then a view id is added to it.
     *
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_feedbackinapp,
                this, true)

        feedbackContainer = container.findViewById(R.id.andes_feedback_container)
        feedbackinappMessage = container.findViewById(R.id.andes_feedbackinapp_message)
        feedbackdivider = container.findViewById(R.id.andes_feedback_divider)
        linkAction = container.findViewById(R.id.andes_feedback_button)
    }

    /**
     * Sets a view id to this message.
     *
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    /**
     * Gets data from the config and sets to the text component of this button.
     *
     */
    private fun setupBodyComponent(config: FeedbackInAppConfiguration) {

        feedbackinappMessage.text = config.feedbackInAppMessageText

    }

    fun setupLinkAction(config: FeedbackInAppConfiguration) {

        linkAction.text = config.buttonLabel

    }

    private fun createConfig() = FeedbackInAppConfigurationFactory.create(context, andesFeedbackInAppAttrs)

    companion object {

        private const val ShowTopDivider_DEFAULT = false
    }
}