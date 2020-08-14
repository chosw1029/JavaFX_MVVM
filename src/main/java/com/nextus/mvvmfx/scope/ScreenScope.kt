package com.nextus.mvvmfx.scope

import de.saxsys.mvvmfx.Scope
import javafx.beans.property.SimpleBooleanProperty

class ScreenScope : Scope {

    val fullScreenProperty = SimpleBooleanProperty(true)
    val maximizeProperty = SimpleBooleanProperty(true)

    fun isFullScreen(): Boolean {
        return fullScreenProperty.get()
    }

    fun isMaximizeScreen(): Boolean = maximizeProperty.get()
}