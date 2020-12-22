package com.mercadolibre.android.andesui.feedbackinapp

import android.content.ClipDescription
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.feedbackinapp.align.AndesFIAppAlign
import com.mercadolibre.android.andesui.feedbackinapp.factory.FeedbackInAppAttrs
import com.mercadolibre.android.andesui.feedbackinapp.hierarchy.AndesFeedbackInAppHierarchy
import com.mercadolibre.android.andesui.feedbackinapp.factory.FeedbackInAppAttrsParser
import com.mercadolibre.android.andesui.feedbackinapp.factory.FeedbackInAppConfiguration
import com.mercadolibre.android.andesui.feedbackinapp.factory.FeedbackInAppConfigurationFactory
import com.mercadolibre.android.andesui.message.factory.AndesMessageConfiguration
import com.mercadolibre.android.andesui.typeface.getFontOrDefault


@Suppress("TooManyFunctions")
class AndesFeedbackInApp : CardView {

    /**
     * Getter and setter for [hierarchy].
     */
    var hierarchy: AndesFeedbackInAppHierarchy
        get() = andesFeedbackInAppAttrs.andesFeedbackInAppHierarchy
        set(value) {
            andesFeedbackInAppAttrs = andesFeedbackInAppAttrs.copy(andesFeedbackInAppHierarchy = value)
        }

    /**
     * Getter and setter for [body].
     */
    var body: String?
        get() = andesFeedbackInAppAttrs.descriptionText
        set(value) {
            andesFeedbackInAppAttrs = andesFeedbackInAppAttrs.copy(descriptionText = value)
            setupBodyComponent(createConfig())
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

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs) {
        initAttrs(attrs)
    }

    @Suppress("unused", "LongParameterList")
    constructor(
            context: Context,
            hierarchy: AndesFeedbackInAppHierarchy = HIERARCHY_DEFAULT,
            body: String,
            showTopDivider: Boolean = ShowTopDivider_DEFAULT,
            descriptionTextAlignment: AndesFIAppAlign,
            action: Boolean = Action_DEFAULT,
            linkText: String

    ) : super(context) {
        initAttrs(hierarchy, body, showTopDivider,  descriptionTextAlignment, action, linkText)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesFeedbackInAppAttrs = FeedbackInAppAttrsParser.parse(context, attrs)
        val config = FeedbackInAppConfigurationFactory.create(context, andesFeedbackInAppAttrs)
        setupComponents(config)
    }

    @Suppress("LongParameterList")
    private fun initAttrs(
            hierarchy: AndesFeedbackInAppHierarchy,
            body: String,
            showTopDivider: Boolean = ShowTopDivider_DEFAULT,
            descriptionTextAlignment: AndesFIAppAlign,
            action: Boolean = Action_DEFAULT,
            linkText: String

    ) {
        andesFeedbackInAppAttrs = FeedbackInAppAttrs(hierarchy, body, showTopDivider, descriptionTextAlignment, action, linkText)
        val config = FeedbackInAppConfigurationFactory.create(context, andesFeedbackInAppAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this message.
     * Is like a choreographer ;)
     *
     */
    private fun setupComponents(config: FeedbackInAppConfiguration) {

        initComponents()
        setupViewId()

        setupColorComponents(config)
    }

    private fun setupColorComponents(config: FeedbackInAppConfiguration) {
        setupBodyComponent(config)
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
        feedbackdivider= container.findViewById(R.id.andes_feedback_divider)
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
        if (config.descriptionText.isNullOrEmpty()) {
            feedbackContainer.visibility = View.GONE
            Log.e("Body", "Message cannot be visualized with null or empty body")
        } else {
            feedbackContainer.visibility = View.VISIBLE
            feedbackinappMessage.text = getBodyText(config.descriptionText, config)
            feedbackinappMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.descriptionSize)

            linkAction.text = config.linkText

        }
    }

    private fun getBodyText(text: String, config: FeedbackInAppConfiguration): SpannableString {
        val spannableString = SpannableString(text)

        feedbackinappMessage.movementMethod = LinkMovementMethod.getInstance()

        return spannableString
    }


    private fun createConfig() = FeedbackInAppConfigurationFactory.create(context, andesFeedbackInAppAttrs)

    companion object {
        private val HIERARCHY_DEFAULT = AndesFeedbackInAppHierarchy.TRANSPARENT
        private const val ShowTopDivider_DEFAULT = false
        private const val Action_DEFAULT = false
    }
}