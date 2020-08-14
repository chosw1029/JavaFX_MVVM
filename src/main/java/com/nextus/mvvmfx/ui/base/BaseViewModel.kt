package com.nextus.mvvmfx.ui.base

import com.nextus.mvvmfx.scope.FxScope
import com.nextus.mvvmfx.ui.alertmgmt.notification.Notifications
import com.nextus.mvvmfx.utils.AlertManager
import de.saxsys.mvvmfx.InjectScope
import de.saxsys.mvvmfx.ViewModel
import javafx.beans.property.SimpleBooleanProperty
import javafx.stage.Stage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel, CoroutineScope {

    @InjectScope
    lateinit var fxScope: FxScope

    var tabID = "" // Tab ID
    lateinit var stage: Stage

    val progressVisibility = SimpleBooleanProperty(false)

    private var isPopupMode = false

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    abstract fun initialize()

    fun getViewModelStage(): Stage = stage

    fun setPopupMode(stage: Stage) {
        isPopupMode = true
        this.stage = stage
    }

    fun setCurrentViewModel() {
        fxScope.currentViewModel.set(this)
    }

    fun showLoadingProgress(msg: String) {
        progressVisibility.set(true)
    }

    fun hideLoadingProgress() {
        progressVisibility.set(false)
    }

    fun showSuccessNotification(msg: String) {
        fxScope.publish(if(isPopupMode) FxScope.SHOW_NOTIFICATION_AT_POPUP else FxScope.SHOW_NOTIFICATION, msg, "success-notification")
    }

    fun showSuccessNotificationBottomRight(msg: String) {
        launch (Dispatchers.JavaFx) {
            AlertManager.showNotification(msg, stage!!, Notifications.SUCCESS, 3.5, fxScope)
        }
    }

    fun showWarnNotification(msg: String) {
        fxScope.publish(if(isPopupMode) FxScope.SHOW_NOTIFICATION_AT_POPUP else FxScope.SHOW_NOTIFICATION, msg, "warn-notification")
    }

    fun showWarnNotificationBottomRight(msg: String) {
        launch (Dispatchers.JavaFx) {
            AlertManager.showNotification(msg, stage!!, Notifications.WARNING, 3.5, fxScope)
        }
    }

    fun showErrorNotification(msg: String) {
        fxScope.publish(if(isPopupMode) FxScope.SHOW_NOTIFICATION_AT_POPUP else FxScope.SHOW_NOTIFICATION, msg, "error-notification")
    }

    fun showErrorNotificationBottomRight(msg: String) {
        launch (Dispatchers.JavaFx) {
            AlertManager.showNotification(msg, stage!!, Notifications.ERROR, 3.5, fxScope)
        }
    }

    fun showInfoNotificationBottomRight(msg: String) {
        launch (Dispatchers.JavaFx) {
            AlertManager.showNotification(msg, stage!!, Notifications.INFORMATION, 3.5, fxScope)
        }
    }

    fun selectViewModel() {
        fxScope.currentViewModel.set(this)
    }

    open fun close() {

    }
}