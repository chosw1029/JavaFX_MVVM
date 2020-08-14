package com.nextus.mvvmfx.scope

import com.nextus.mvvmfx.ui.base.BaseViewModel
import de.saxsys.mvvmfx.Scope
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javax.inject.Singleton

@Singleton
class FxScope : Scope {

    val progressMsg = SimpleStringProperty("데이터를 가져오는 중입니다.")
    val currentViewModel = SimpleObjectProperty<BaseViewModel>()

    companion object {
        const val SHOW_NOTIFICATION = "SHOW_NOTIFICATION"
        const val SHOW_NOTIFICATION_AT_POPUP = "SHOW_NOTIFICATION_AT_POPUP"
        const val CLOSE_EVENT = "CLOSE_EVENT"
        const val LOG_MESSAGE = "LOG_MESSAGE"
    }
}