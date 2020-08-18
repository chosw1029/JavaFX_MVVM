package com.nextus.mvvmfx.ui.custom

import com.nextus.mvvmfx.scope.FxScope
import com.nextus.mvvmfx.scope.ScreenScope
import com.nextus.mvvmfx.ui.custom.borderless.BorderlessView
import de.saxsys.mvvmfx.FluentViewLoader
import javafx.beans.property.BooleanProperty
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.stage.Stage
import javafx.stage.StageStyle

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
class BorderlessScene(val stage: Stage, stageStyle: StageStyle, sceneRoot: Parent, fxScope: FxScope, screenScope: ScreenScope) : Scene(Pane()) {

    private var root = FluentViewLoader.fxmlView(BorderlessView::class.java).providedScopes(fxScope, screenScope).load()

    /**
     * True if the stage is maximized or false if not
     *
     * @return True if the stage is maximized or false if not
     */
    val isMaximized: Boolean
        get() = root.viewModel.maximized.get()

    /**
     * True if the stage is resizable or false if not
     *
     * @return True if the stage is resizable or false if not
     */
    /**
     * Disable/enable the resizing of your stage. Enabled by default.
     *
     * @param bool false to disable, true to enable.
     */
    var isResizable: Boolean
        get() = root.viewModel.resizable.get()
        set(bool) {
            root.viewModel.resizable.set(bool)
        }

    /**
     * True if Aero Snap is enabled or false if not
     *
     * @return True if Aero Snap is enabled or false if not
     */
    /**
     * Disable/enable the Aero Snap of your stage. Enabled by default.
     *
     * @param bool false to disable, true to enable.
     */
    var isSnapEnabled: Boolean
        get() = root.viewModel.snap.get()
        set(bool) {
            root.viewModel.snap.set(bool)
        }

    /**
     * The constructor.
     *
     * @param stage your stage.
     * @param stageStyle **Undecorated** and **Transparent** StageStyles are accepted or else the Transparent StageStyle will be set.
     * @param sceneRoot The root of the Scene
     */
    init {
        // Set Scene root
        setRoot(root.view)
        setContent(sceneRoot)

        // Initialize the Controller
        root.viewModel.createTransparentWindow(stage)

        // StageStyle
        stage.initStyle(stageStyle)
        if (stageStyle == StageStyle.UTILITY) {
            isSnapEnabled = false
            isResizable = false
        }
    }

    /**
     * Change the content of the scene.
     *
     * @param content the root Parent of your new content.
     */
    private fun setContent(content: Parent?) {
        root.codeBehind.rootView.children.add(0, content)

        AnchorPane.setLeftAnchor(content, 0.0)
        AnchorPane.setTopAnchor(content, 0.0)
        AnchorPane.setRightAnchor(content, 0.0)
        AnchorPane.setBottomAnchor(content, 0.0)
    }

    /**
     * Set a node that can be pressed and dragged to move the application around.
     *
     * @param node the node.
     */
    fun setMoveControl(node: Node) {
        root.viewModel.setMoveControl(node)
    }

    /**
     * Toggle to maximize the application.
     */
    fun maximizeStage() {
        root.viewModel.maximize()
    }

    /**
     * Minimize the stage to the taskbar.
     */
    fun minimizeStage() {
        root.viewModel.minimize()
    }

    /**
     * Maximized property.
     *
     * @return Maximized property
     */
    fun maximizedProperty(): BooleanProperty {
        return root.viewModel.maximized
    }

    /**
     * Resizable property.
     *
     * @return Resizable property
     */
    fun resizableProperty(): BooleanProperty {
        return root.viewModel.resizable
    }

    /**
     * Aero Snap property.
     *
     * @return Aero Snap property
     */
    fun snapProperty(): BooleanProperty {
        return root.viewModel.snap
    }

    /**
     * Returns the width and height of the application when windowed.
     *
     * @return instance of Delta class. Delta.x = width, Delta.y = height.
     */
    val windowedSize: Delta
        get() {
            if (root.viewModel.prevSize.x == null) root.viewModel.prevSize.x = stage.width
            if (root.viewModel.prevSize.y == null) root.viewModel.prevSize.y = stage.height
            return root.viewModel.prevSize
        }

    /**
     * Returns the x and y position of the application when windowed.
     *
     * @return instance of Delta class. Use Delta.x and Delta.y.
     */
    val windowedPositon: Delta
        get() {
            if (root.viewModel.prevPos.x == null) root.viewModel.prevPos.x = stage.x
            if (root.viewModel.prevPos.y == null) root.viewModel.prevPos.y = stage.y
            return root.viewModel.prevPos
        }

    /**
     * Removes the default css style of the corners
     */
    fun removeDefaultCSS() {
        this.root.view.stylesheets.removeAt(0)
    }

    /**
     * The transparent window which allows the library to have aerosnap controls can be styled using this method .
     * It is nothing more than a StackPane in a transparent window , so for example you can change it's background color , borders , everything through this method :)
     *
     * @param style The style of the transparent window of the application
     */
    fun setTransparentWindowStyle(style: String?) {
        root.viewModel.transparentWindow.style = ""
        root.viewModel.transparentWindow.style = style
    }
}