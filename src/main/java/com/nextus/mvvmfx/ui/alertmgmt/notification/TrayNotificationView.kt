package com.nextus.mvvmfx.ui.alertmgmt.notification

import com.nextus.mvvmfx.ui.alertmgmt.animations.Animation
import com.nextus.mvvmfx.ui.alertmgmt.animations.PopupAnimation
import com.nextus.mvvmfx.ui.base.BaseView
import com.nextus.mvvmfx.utils.AlertManager
import com.nextus.mvvmfx.utils.CursorController
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import javafx.util.Duration

class TrayNotificationView : BaseView<TrayNotificationViewModel>() {

    @FXML lateinit var lblTitle: Label
    @FXML lateinit var rootNode: AnchorPane
    @FXML lateinit var imageIcon: ImageView
    @FXML lateinit var closeButton: ImageView

    var animation: Animation? = null
    private val onDismissedCallBack: EventHandler<ActionEvent>? = null
    private val onShownCallback: EventHandler<ActionEvent>? = null

    override fun initialize() {
        CursorController.setMouseEnterExitOnlyCursor(closeButton)

        lblTitle.textProperty().bindBidirectional(viewModel.msgProperty)
        lblTitle.styleProperty().bindBidirectional(viewModel.msgStyleProperty)
        rootNode.styleProperty().bindBidirectional(viewModel.rootPaneStyleProperty)
        imageIcon.imageProperty().bindBidirectional(viewModel.iconImageProperty)
    }

    private fun initAnimation(stage: Stage) {
        animation = PopupAnimation(stage)
    }

    fun showAndDismiss(dismissDelay: Duration?, stage: Stage) {
        initAnimation(stage)
        animation?.let {
            AlertManager.animationList.add(0, it)
            if(!it.isShowing) {
                stage.show()

                for((index, ani) in AlertManager.animationList.withIndex()) {
                    ani.move(index)
                }

                onShown()

                dismissDelay?.let { duration ->
                    it.playSequential(duration)
                }

            } else {
                dismiss()
            }

            onDismissed()
        }
    }

    /**
     * Dismisses the notifcation tray
     */
    @FXML
    private fun dismiss() {
        if (animation!!.isShowing) {
            animation?.playDismissAnimation()
            onDismissed()
        } else {
            (closeButton.scene.window as Stage).close()
        }
    }

    private fun onShown() {
        onShownCallback?.handle(ActionEvent())
    }

    private fun onDismissed() {
        onDismissedCallBack?.handle(ActionEvent())
    }


}