package com.nextus.mvvmfx.scope

import de.saxsys.mvvmfx.Scope
import javafx.beans.property.SimpleBooleanProperty

class ScreenScope : Scope {

    val maximizeIconProperty = SimpleBooleanProperty(false)
    val maximizeProperty = SimpleBooleanProperty(false)

    fun isMaximizeScreen(): Boolean = maximizeProperty.get()
    fun isMaximizeIcon(): Boolean = maximizeIconProperty.get()
}
