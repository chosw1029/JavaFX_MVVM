package com.nextus.mvvmfx.ui.main

import com.nextus.mvvmfx.ui.base.BaseView
import com.nextus.mvvmfx.ui.custom.BorderlessScene
import com.nextus.mvvmfx.ui.topbar.TopBarView
import javafx.fxml.FXML
import javafx.scene.layout.BorderPane

class MainView : BaseView<MainViewModel>() {

    @FXML
    lateinit var topBarViewController: TopBarView

    @FXML lateinit var root: BorderPane

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