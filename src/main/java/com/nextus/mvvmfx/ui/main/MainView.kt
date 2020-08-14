package com.nextus.mvvmfx.ui.main

import com.nextus.mvvmfx.ui.base.BaseView
import com.nextus.mvvmfx.ui.custom.BorderlessScene
import com.nextus.mvvmfx.ui.topbar.TopBarView
import javafx.fxml.FXML

class MainView : BaseView<MainViewModel>() {

    @FXML
    lateinit var topBarViewController: TopBarView

    override fun initialize() {
        //(stage.scene as BorderlessScene).setMoveControl(topBarViewController.root)
    }

    @FXML
    private fun showSuccessBottomRightAlert() {
        viewModel.showSuccessNotificationBottomRight("Notification Test")
    }

    @FXML
    private fun showInfoBottomRightAlert() {
        viewModel.showInfoNotificationBottomRight("Notification Test")
    }

    @FXML
    private fun showWarningBottomRightAlert() {
        viewModel.showWarnNotificationBottomRight("Notification Test")
    }
}