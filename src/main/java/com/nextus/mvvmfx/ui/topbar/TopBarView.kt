package com.nextus.mvvmfx.ui.topbar

import com.nextus.mvvmfx.ui.base.BaseView
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane

class TopBarView : BaseView<TopBarViewModel>() {

    @FXML lateinit var root: AnchorPane
    @FXML lateinit var miniButton: ImageView
    @FXML lateinit var maxButton: ImageView
    @FXML lateinit var closeButton: ImageView

    override fun initialize() {
        maxButton.imageProperty().bindBidirectional(viewModel.restoreImage)
    }

    @FXML
    private fun minimizeButtonPressed() {
        stage.isIconified = true
    }

    @FXML
    private fun restoreAndMaximizeButtonPressed() {
        if(viewModel.screenScope.isMaximizeScreen()) {
            viewModel.screenScope.maximizeProperty.set(false)
        } else {
            viewModel.screenScope.maximizeProperty.set(true)
        }
    }

    @FXML
    private fun closeButtonPressed() {
        stage.close()
        Platform.exit()
    }
}