/*
 *
 */
package com.nextus.mvvmfx.ui.custom.borderless

import com.nextus.mvvmfx.ui.base.BaseView
import com.nextus.mvvmfx.ui.custom.Delta
import com.nextus.mvvmfx.ui.custom.TransparentWindow
import javafx.beans.property.SimpleBooleanProperty
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.geometry.Rectangle2D
import javafx.scene.Node
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.stage.Screen
import javafx.stage.Stage

/**
 * Undecorated JavaFX Scene with implemented move, resize, minimize, maximize and Aero Snap.
 *
 *
 * Usage:
 *
 * <pre>
 * {
 * &#64;code
 * //add the code here
 * }
</pre> *
 *
 * @author Nicolas Senet-Larson
 * @author GOXR3PLUS STUDIO
 * @version 1.0
 */
class BorderlessView : BaseView<BorderlessViewModel>() {

    /** The left pane.  */
    @FXML lateinit var leftPane: Pane

    /** The right pane.  */
    @FXML lateinit var rightPane: Pane

    /** The top pane.  */
    @FXML lateinit var topPane: Pane

    /** The bottom pane.  */
    @FXML lateinit var bottomPane: Pane

    /** The top left pane.  */
    @FXML lateinit var topLeftPane: Pane

    /** The top right pane.  */
    @FXML lateinit var topRightPane: Pane

    /** The bottom left pane.  */
    @FXML lateinit var bottomLeftPane: Pane

    /** The bottom right pane.  */
    @FXML lateinit var bottomRightPane: Pane

    @FXML
    lateinit var rootView: AnchorPane

    override fun initialize() {
        viewModel.setResizeControl(leftPane, "left")
        viewModel.setResizeControl(rightPane, "right")
        viewModel.setResizeControl(topPane, "top")
        viewModel.setResizeControl(bottomPane, viewModel.bottom)
        viewModel.setResizeControl(topLeftPane, "top-left")
        viewModel.setResizeControl(topRightPane, "top-right")
        viewModel.setResizeControl(bottomLeftPane, "${viewModel.bottom}-left")
        viewModel.setResizeControl(bottomRightPane, "${viewModel.bottom}-right")

        leftPane.disableProperty().bind(viewModel.negateOfResizable)
        rightPane.disableProperty().bind(viewModel.negateOfResizable)
        topPane.disableProperty().bind(viewModel.negateOfResizable)
        bottomPane.disableProperty().bind(viewModel.negateOfResizable)
        topLeftPane.disableProperty().bind(viewModel.negateOfResizable)
        topRightPane.disableProperty().bind(viewModel.negateOfResizable)
        bottomLeftPane.disableProperty().bind(viewModel.negateOfResizable)
        bottomRightPane.disableProperty().bind(viewModel.negateOfResizable)
    }

}