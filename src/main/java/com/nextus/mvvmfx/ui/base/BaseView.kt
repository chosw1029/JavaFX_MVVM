package com.nextus.mvvmfx.ui.base

import com.nextus.mvvmfx.scope.FxScope
import com.nextus.mvvmfx.utils.CursorController.setMouseEnterExit
import com.nextus.mvvmfx.utils.WindowController
import de.saxsys.mvvmfx.FxmlView
import de.saxsys.mvvmfx.InjectViewModel
import de.saxsys.mvvmfx.ViewModel
import de.saxsys.mvvmfx.utils.notifications.NotificationObserver
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.stage.Stage
import java.net.URL
import java.util.*
import javax.inject.Inject

abstract class BaseView<T: ViewModel> : FxmlView<T>, Initializable {

    @FXML var systemBar: HBox? = null
    @FXML var progressView: BorderPane? = null

    @InjectViewModel
    lateinit var viewModel: T

    @Inject
    lateinit var stage: Stage

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        (viewModel as BaseViewModel).stage = stage
        progressView?.visibleProperty()?.bindBidirectional((viewModel as BaseViewModel).progressVisibility)
        (viewModel as BaseViewModel).fxScope.subscribe(FxScope.CLOSE_EVENT, NotificationObserver { key, payload -> (viewModel as BaseViewModel).close() })
        setupSystemBar()
        initialize()
    }

    abstract fun initialize()

    fun initCloseButton(closeImage: ImageView) {
        setMouseEnterExit(closeImage, "icons8_cancel_hover_24px.png", "icons8_cancel_24px.png")
    }

    private fun setupSystemBar() {
        systemBar?.let {
            it.onMousePressed = EventHandler { event -> WindowController.setOnMousePressed(it.scene.window as Stage, event) }
            it.onMouseDragged = EventHandler { event -> WindowController.setOnMouseDragged(it.scene.window as Stage, event) }
        }
    }
}