package com.mercadolibre.android.andesui.feedbackinapp.type

import android.content.Context
import com.mercadolibre.android.andesui.feedbackinapp.status.AndesActionStatus
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.hierarchy.BackgroundColorConfig
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor

internal interface AndesFIATypeInterface {
    fun borderColor(context: Context, status: AndesActionStatus): AndesColor
    fun iconColor(context: Context, status: AndesActionStatus): AndesColor
    fun backgroundColor(context: Context, status: AndesActionStatus): AndesColor
    fun textColor(context: Context): AndesColor
    fun linkActionColorConfig(): BackgroundColorConfig
}

internal object AndesFIATypeIdle : AndesFIATypeInterface {
    override fun borderColor(context: Context, status: AndesActionStatus): AndesColor {
        return if (status == AndesActionStatus.UNSELECTED) {
            R.color.andes_gray_250_solid.toAndesColor()
        } else {
            R.color.andes_blue_ml_500.toAndesColor()
        }
    }
    override fun iconColor(context: Context, status: AndesActionStatus) = R.color.andes_white.toAndesColor()
    override fun backgroundColor(context: Context, status: AndesActionStatus): AndesColor {
        return if (status == AndesActionStatus.UNSELECTED) {
            R.color.andes_white.toAndesColor()
        } else {
            R.color.andes_blue_ml_500.toAndesColor()
        }
    }
    override fun textColor(context: Context) = R.color.andes_gray_800_solid.toAndesColor()

    override fun linkActionColorConfig(): BackgroundColorConfig {
        return BackgroundColorConfig(
                enabledColor = R.color.andes_transparent.toAndesColor(),
                pressedColor = R.color.andes_transparent.toAndesColor(),
                focusedColor = R.color.andes_transparent.toAndesColor(),
                hoveredColor = R.color.andes_transparent.toAndesColor(),
                disabledColor = R.color.andes_transparent.toAndesColor(),
                otherColor = null)
    }
}

internal object AndesFIATypeDisabled : AndesFIATypeInterface {
    override fun borderColor(context: Context, status: AndesActionStatus) = R.color.andes_gray_100_solid.toAndesColor()
    override fun iconColor(context: Context, status: AndesActionStatus) = R.color.andes_gray_250_solid.toAndesColor()
    override fun backgroundColor(context: Context, status: AndesActionStatus): AndesColor {
        return if (status == AndesActionStatus.UNSELECTED) {
            R.color.andes_white.toAndesColor()
        } else {
            R.color.andes_gray_100_solid.toAndesColor()
        }
    }
    override fun textColor(context: Context) = R.color.andes_gray_250_solid.toAndesColor()

    override fun linkActionColorConfig(): BackgroundColorConfig {
        return BackgroundColorConfig(
                enabledColor = R.color.andes_transparent.toAndesColor(),
                pressedColor = R.color.andes_transparent.toAndesColor(),
                focusedColor = R.color.andes_transparent.toAndesColor(),
                hoveredColor = R.color.andes_transparent.toAndesColor(),
                disabledColor = R.color.andes_transparent.toAndesColor(),
                otherColor = null)
    }
}

internal object AndesFIATypeError : AndesFIATypeInterface {
    override fun borderColor(context: Context, status: AndesActionStatus) = R.color.andes_red_500.toAndesColor()
    override fun iconColor(context: Context, status: AndesActionStatus) = R.color.andes_white.toAndesColor()
    override fun backgroundColor(context: Context, status: AndesActionStatus) = R.color.andes_white.toAndesColor()
    override fun textColor(context: Context) = R.color.andes_gray_800_solid.toAndesColor()

    override fun linkActionColorConfig(): BackgroundColorConfig {
        return BackgroundColorConfig(
                enabledColor = R.color.andes_transparent.toAndesColor(),
                pressedColor = R.color.andes_transparent.toAndesColor(),
                focusedColor = R.color.andes_transparent.toAndesColor(),
                hoveredColor = R.color.andes_transparent.toAndesColor(),
                disabledColor = R.color.andes_transparent.toAndesColor(),
                otherColor = null)
    }
}




