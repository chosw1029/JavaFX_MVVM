package com.nextus.mvvmfx.ui.main

import com.nextus.mvvmfx.ui.base.BaseView
import javafx.fxml.FXML

class MainView : BaseView<MainViewModel>() {

    override fun initialize() {

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