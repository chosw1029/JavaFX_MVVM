package com.nextus.mvvmfx.ui.topbar

import com.nextus.mvvmfx.ui.base.BaseView
import com.nextus.mvvmfx.utils.WindowController
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane

class TopBarView : BaseView<TopBarViewModel>() {

    @FXML lateinit var root: AnchorPane
    @FXML lateinit var miniButton: ImageView
    @FXML lateinit var maxButton: ImageView
    @FXML lateinit var closeButton: ImageView

    override fun initialize() {
        root.apply {
            onMousePressed = EventHandler { event -> WindowController.setOnMousePressed(stage, event) }
            onMouseDragged = EventHandler { event -> WindowController.setOnMouseDragged(stage, event) }
            onMouseClicked = EventHandler { event ->
                if (event.clickCount == 2) {
                    restoreAndMaximizeButtonPressed()
                }
            }
        }

        maxButton.imageProperty().bindBidirectional(viewModel.restoreImage)
    }

    @FXML
    private fun minimizeButtonPressed() {
        stage.isIconified = true
    }

    @FXML
    private fun restoreAndMaximizeButtonPressed() {
        if (viewModel.screenScope.isFullScreen())
            WindowController.setRestoreMode(stage, viewModel.screenScope)
        else
            WindowController.setFullScreenMode(stage, viewModel.screenScope)
    }

    @FXML
    private fun closeButtonPressed() {
        stage.close()
        Platform.exit()
    }
}