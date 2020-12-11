package com.mercadolibre.android.andesui.feedbackinapp.status


enum class AndesActionStatus {
    SELECTED,
    UNSELECTED,
    UNDEFINED;

    companion object {
        fun fromString(value: String): AndesActionStatus = valueOf(value.toUpperCase())
    }

    internal val status get() = getAndesActionStatus()

    private fun getAndesActionStatus(): AndesActionStatusInterfaceInterface {
        return when (this) {
            SELECTED -> AndesActionStatusSelected
            UNSELECTED -> AndesActionStatusUnselected
            UNDEFINED -> AndesActionStatusUndefined
        }
    }
}
