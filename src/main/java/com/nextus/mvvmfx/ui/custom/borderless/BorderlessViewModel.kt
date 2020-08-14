package com.nextus.mvvmfx.ui.custom.borderless

import com.nextus.mvvmfx.scope.FxScope
import com.nextus.mvvmfx.scope.ScreenScope
import com.nextus.mvvmfx.ui.base.BaseViewModel
import com.nextus.mvvmfx.ui.custom.Delta
import com.nextus.mvvmfx.ui.custom.TransparentWindow
import de.saxsys.mvvmfx.InjectScope
import de.saxsys.mvvmfx.ScopeProvider
import javafx.beans.property.SimpleBooleanProperty
import javafx.event.EventHandler
import javafx.geometry.Rectangle2D
import javafx.scene.Node
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.stage.Screen
import javafx.stage.Stage
import kotlin.math.max

class BorderlessViewModel : BaseViewModel() {

    val prevSize = Delta()
    val prevPos = Delta()

    val snap = SimpleBooleanProperty(true)
    val maximized = SimpleBooleanProperty(false)
    val resizable = SimpleBooleanProperty(false)

    /** The snapped.  */
    private var snapped = false

    /** The bottom.  */
    var bottom = "bottom"

    val negateOfResizable = resizable.not()

    @InjectScope
    lateinit var screenScope: ScreenScope

    /**
     * Transparent Window used to show how the window will be resized
     */
    lateinit var transparentWindow: TransparentWindow

    override fun initialize() {
        screenScope.maximizeProperty.bindBidirectional(maximized)
    }

    /**
     * Minimize the application.
     */
    fun minimize() {
        stage.isIconified = true
    }

    /**
     * Set a node that can be pressed and dragged to move the application around.
     *
     * @param node the node.
     */
    fun setMoveControl(node: Node) {
        val delta = Delta()
        val eventSource = Delta()

        // Record drag deltas on press.
        node.onMousePressed = EventHandler { m: MouseEvent ->
            if (m.isPrimaryButtonDown) {
                delta.x = m.sceneX //getX()
                delta.y = m.sceneY //getY()
                if (maximized.get() || snapped) {
                    delta.x = prevSize.x * (m.sceneX / stage.width) //(m.getX() / stage.getWidth())
                    delta.y = prevSize.y * (m.sceneY / stage.height) //(m.getY() / stage.getHeight())
                } else {
                    prevSize.x = stage.width
                    prevSize.y = stage.height
                    prevPos.x = stage.x
                    prevPos.y = stage.y
                }
                eventSource.x = m.screenX
                eventSource.y = node.prefHeight(stage.height)
            }
        }

        // Dragging moves the application around.
        node.onMouseDragged = EventHandler { m: MouseEvent ->
            if (m.isPrimaryButtonDown) {

                // Move x axis.
                stage.x = m.screenX - delta.x
                if (snapped) {
                    if (m.screenY > eventSource.y) {
                        snapOff()
                    } else {
                        val screen = Screen.getScreensForRectangle(m.screenX, m.screenY, 1.0, 1.0)[0].visualBounds
                        stage.height = screen.height
                    }
                } else {
                    // Move y axis.
                    stage.y = m.screenY - delta.y
                }

                // Aero Snap off.
                if (maximized.get()) {
                    stage.width = prevSize.x
                    stage.height = prevSize.y
                    setMaximized(false)
                }
                var toCloseWindow = false
                if (!snap.get()) {
                    toCloseWindow = true
                } else {
                    //--------------------------Check here for Transparent Window--------------------------
                    //Rectangle2D wholeScreen = Screen.getScreensForRectangle(m.getScreenX(), m.getScreenY(), 1, 1).get(0).getBounds()
                    val screens = Screen.getScreensForRectangle(m.screenX, m.screenY, 1.0, 1.0)
                    if (screens.isEmpty()) {
                        return@EventHandler
                    }
                    val screen = screens[0].visualBounds

                    //----------TO BE ADDED IN FUTURE RELEASE , GAVE ME CANCER implementing them ..----------------

                    //				// Aero Snap Top Right Corner
                    //				if (m.getScreenY() <= screen.getMinY() && m.getScreenX() >= screen.getMaxX() - 1) {
                    //					double difference;
                    //
                    //					//Fix the positioning
                    //					if (wholeScreen.getMaxX() > screen.getMaxX())
                    //						difference = - ( wholeScreen.getWidth() - screen.getWidth() );
                    //					else
                    //						difference =  (wholeScreen.getWidth() - screen.getWidth()-15);
                    //
                    //					System.out.println(difference);
                    //
                    //					transparentWindow.getWindow().setX(wholeScreen.getWidth() / 2 + difference);
                    //					transparentWindow.getWindow().setY(screen.getMinY());
                    //					transparentWindow.getWindow().setWidth(screen.getWidth() / 2);
                    //					transparentWindow.getWindow().setHeight(screen.getHeight() / 2);
                    //
                    //					transparentWindow.show();
                    //				}
                    //
                    //				// Aero Snap Top Left Corner
                    //				else if (m.getScreenY() <= screen.getMinY() && m.getScreenX() <= screen.getMinX()) {
                    //
                    //					transparentWindow.getWindow().setX(screen.getMinX());
                    //					transparentWindow.getWindow().setY(screen.getMinY());
                    //					transparentWindow.getWindow().setWidth(screen.getWidth() / 2);
                    //					transparentWindow.getWindow().setHeight(screen.getHeight() / 2);
                    //
                    //					transparentWindow.show();
                    //				}
                    //
                    //				// Aero Snap Bottom Right Corner
                    //				else if (m.getScreenY() >= screen.getMaxY() - 1 && m.getScreenX() >= screen.getMaxY()) {
                    //
                    //					transparentWindow.getWindow().setX(wholeScreen.getWidth() / 2 - ( wholeScreen.getWidth() - screen.getWidth() ));
                    //					transparentWindow.getWindow().setY(wholeScreen.getHeight() / 2 - ( wholeScreen.getHeight() - screen.getHeight() ));
                    //					transparentWindow.getWindow().setWidth(wholeScreen.getWidth() / 2);
                    //					transparentWindow.getWindow().setHeight(wholeScreen.getHeight() / 2);
                    //
                    //					transparentWindow.show();
                    //				}
                    //
                    //				// Aero Snap Bottom Left Corner
                    //				else if (m.getScreenY() >= screen.getMaxY() - 1 && m.getScreenX() <= screen.getMinX()) {
                    //
                    //					transparentWindow.getWindow().setX(screen.getMinX());
                    //					transparentWindow.getWindow().setY(wholeScreen.getHeight() / 2 - ( wholeScreen.getHeight() - screen.getHeight() ));
                    //					transparentWindow.getWindow().setWidth(wholeScreen.getWidth() / 2);
                    //					transparentWindow.getWindow().setHeight(wholeScreen.getHeight() / 2);
                    //
                    //					transparentWindow.show();
                    //				}

                    // Aero Snap Left.
                    if (m.screenX <= screen.minX) {
                        transparentWindow.window.y = screen.minY
                        transparentWindow.window.height = screen.height
                        transparentWindow.window.x = screen.minX
                        if (screen.width / 2 < transparentWindow.window.minWidth) {
                            transparentWindow.window.width = transparentWindow.window.minWidth
                        } else {
                            transparentWindow.window.width = screen.width / 2
                        }
                        transparentWindow.show()
                    } else if (m.screenX >= screen.maxX - 1) {
                        transparentWindow.window.y = screen.minY
                        transparentWindow.window.height = screen.height
                        if (screen.width / 2 < transparentWindow.window.minWidth) {
                            transparentWindow.window.width = transparentWindow.window.minWidth
                        } else {
                            transparentWindow.window.width = screen.width / 2
                        }
                        transparentWindow.window.x = screen.maxX - transparentWindow.window.width
                        transparentWindow.show()
                    } else if (m.screenY <= screen.minY || m.screenY >= screen.maxY - 1) {
                        transparentWindow.window.x = screen.minX
                        transparentWindow.window.y = screen.minY
                        transparentWindow.window.width = screen.width
                        transparentWindow.window.height = screen.height
                        transparentWindow.show()
                    } else {
                        toCloseWindow = true
                    }

                    //				System.out.println("Mouse Position [ " + m.getScreenX() + "," + m.getScreenY() + " ]")
                    //				System.out.println(" " + screen.getMinX() + "," + screen.getMinY() + " ," + screen.getMaxX() + " ," + screen.getMaxY())
                    //				System.out.println()
                }
                if (toCloseWindow) {
                    transparentWindow.close()
                }
            }
        }

        // Maximize on double click.
        node.onMouseClicked = EventHandler { m: MouseEvent -> if (snap.get() && MouseButton.PRIMARY == m.button && m.clickCount == 2) maximize() }

        // Aero Snap on release.
        node.onMouseReleased = EventHandler { m: MouseEvent ->
            try {
                if (!snap.get()) {
                    return@EventHandler
                }
                if (MouseButton.PRIMARY == m.button && m.screenX != eventSource.x) {
                    val screen = Screen.getScreensForRectangle(m.screenX, m.screenY, 1.0, 1.0)[0].visualBounds

                    // Aero Snap Left.
                    if (m.screenX <= screen.minX) {
                        stage.y = screen.minY
                        stage.height = screen.height
                        stage.x = screen.minX
                        if (screen.width / 2 < stage.minWidth) {
                            stage.width = stage.minWidth
                        } else {
                            stage.width = screen.width / 2
                        }
                        snapped = true
                    } else if (m.screenX >= screen.maxX - 1) {
                        stage.y = screen.minY
                        stage.height = screen.height
                        if (screen.width / 2 < stage.minWidth) {
                            stage.width = stage.minWidth
                        } else {
                            stage.width = screen.width / 2
                        }
                        stage.x = screen.maxX - stage.width
                        snapped = true
                    } else if (m.screenY <= screen.minY || m.screenY >= screen.maxY - 1) {
                        if (!screen.contains(prevPos.x, prevPos.y)) {
                            if (prevSize.x > screen.width) prevSize.x = screen.width - 20
                            if (prevSize.y > screen.height) prevSize.y = screen.height - 20
                            prevPos.x = screen.minX + (screen.width - prevSize.x) / 2
                            prevPos.y = screen.minY + (screen.height - prevSize.y) / 2
                        }
                        stage.x = screen.minX
                        stage.y = screen.minY
                        stage.width = screen.width
                        stage.height = screen.height
                        setMaximized(true)
                    }

                    //				System.out.println("Mouse Position [ " + m.getScreenX() + "," + m.getScreenY() + " ]")
                    //				System.out.println(" " + screen.getMinX() + "," + screen.getMinY() + " ," + screen.getMaxX() + " ," + screen.getMaxY())
                    //				System.out.println()
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            //Hide the transparent window -- close this window no matter what
            transparentWindow.close()
        }
    }

    /**
     * Maximize on/off the application.
     */
    fun maximize() {
        val screen: Rectangle2D
        screen = try {
            if (Screen.getScreensForRectangle(stage.x, stage.y, stage.width / 2, stage.height / 2).isEmpty()) Screen.getScreensForRectangle(stage.x, stage.y, stage.width, stage.height)[0].visualBounds else Screen.getScreensForRectangle(stage.x, stage.y, stage.width / 2, stage.height / 2)[0].visualBounds
        } catch (ex: Exception) {
            ex.printStackTrace()
            return
        }
        if (maximized.get()) {
            stage.width = prevSize.x
            stage.height = prevSize.y
            stage.x = prevPos.x
            stage.y = prevPos.y
            setMaximized(false)
        } else {
            // Record position and size, and maximize.
            if (!snapped) {
                prevSize.x = stage.width
                prevSize.y = stage.height
                prevPos.x = stage.x
                prevPos.y = stage.y
            } else if (!screen.contains(prevPos.x, prevPos.y)) {
                if (prevSize.x > screen.width) prevSize.x = screen.width - 20
                if (prevSize.y > screen.height) prevSize.y = screen.height - 20
                prevPos.x = screen.minX + (screen.width - prevSize.x) / 2
                prevPos.y = screen.minY + (screen.height - prevSize.y) / 2
            }
            stage.x = screen.minX
            stage.y = screen.minY
            stage.width = screen.width
            stage.height = screen.height
            setMaximized(true)
        }
    }

    /**
     * Set pane to resize application when pressed and dragged.
     *
     * @param pane the pane the action is set to.
     * @param direction the resize direction. Diagonal: 'top' or 'bottom' + 'right' or 'left'. [[SuppressWarningsSpartan]]
     */
    fun setResizeControl(pane: Pane, direction: String) {

        //Record the previous size and previous point
        pane.onDragDetected = EventHandler { _ ->
            prevSize.x = stage.width
            prevSize.y = stage.height
            prevPos.x = stage.x
            prevPos.y = stage.y
        }
        pane.onMouseDragged = EventHandler { m: MouseEvent ->
            if (m.isPrimaryButtonDown) {
                val width = stage.width
                val height = stage.height

                // Horizontal resize.
                if (direction.endsWith("left")) {
                    val comingWidth = width - m.screenX + stage.x

                    //Check if it violates minimumWidth
                    if (comingWidth > 0 && comingWidth >= stage.minWidth) {
                        stage.width = stage.x - m.screenX + stage.width
                        stage.x = m.screenX
                    }
                } else if (direction.endsWith("right")) {
                    val comingWidth = width + m.x

                    //Check if it violates
                    if (comingWidth > 0 && comingWidth >= stage.minWidth) stage.width = m.sceneX
                }

                // Vertical resize.
                if (direction.startsWith("top")) {
                    if (snapped) {
                        stage.height = prevSize.y
                        snapped = false
                    } else if (height > stage.minHeight || m.y < 0) {
                        stage.height = stage.y - m.screenY + stage.height
                        stage.y = m.screenY
                    }
                } else if (direction.startsWith(bottom)) {
                    if (snapped) {
                        stage.y = prevPos.y
                        snapped = false
                    } else {
                        val comingHeight = height + m.y

                        //Check if it violates
                        if (comingHeight > 0 && comingHeight >= stage.minHeight) stage.height = m.sceneY
                    }
                }
            }
        }

        // Record application height and y position.
        pane.onMousePressed = EventHandler { m: MouseEvent ->
            if (m.isPrimaryButtonDown && !snapped) {
                prevSize.y = stage.height
                prevPos.y = stage.y
            }
        }

        // Aero Snap Resize.
        pane.onMouseReleased = EventHandler { m: MouseEvent ->
            if (MouseButton.PRIMARY == m.button && !snapped) {
                val screen = Screen.getScreensForRectangle(m.screenX, m.screenY, 1.0, 1.0)[0].visualBounds
                if (stage.y <= screen.minY && direction.startsWith("top")) {
                    stage.height = screen.height
                    stage.y = screen.minY
                    snapped = true
                }
                if (m.screenY >= screen.maxY && direction.startsWith(bottom)) {
                    stage.height = screen.height
                    stage.y = screen.minY
                    snapped = true
                }
            }
        }

        // Aero Snap resize on double click.
        pane.onMouseClicked = EventHandler { m: MouseEvent ->
            if (MouseButton.PRIMARY == m.button && m.clickCount == 2 && ("top" == direction || bottom == direction)) {
                val screen = Screen.getScreensForRectangle(stage.x, stage.y, stage.width / 2, stage.height / 2)[0].visualBounds
                if (snapped) {
                    stage.height = prevSize.y
                    stage.y = prevPos.y
                    snapped = false
                } else {
                    prevSize.y = stage.height
                    prevPos.y = stage.y
                    stage.height = screen.height
                    stage.y = screen.minY
                    snapped = true
                }
            }
        }
    }

    private fun snapOff() {
        stage.width = prevSize.x
        stage.height = prevSize.y
        snapped = false
    }

    /**
     * Creates the Transparent Window
     *
     * @param parentWindow The parentWindow of the TransparentWindow
     */
    fun createTransparentWindow(parentWindow: Stage) {
        transparentWindow = TransparentWindow().apply { window.initOwner(parentWindow) }
    }

    /**
     * Disable/enable the Aero Snap property of your stage. Enabled by default.
     *
     * @param bool false to disable, true to enable.
     */
    private fun setSnapEnabled(bool: Boolean) {
        snap.set(bool)
        if (!bool && snapped) {
            snapOff()
        }
    }

    /**
     * Determines if the Window is maximized.
     *
     * @param maximized the new maximized
     */
    private fun setMaximized(maximized: Boolean) {
        this.maximized.set(maximized)
        setResizable(!maximized)
        println("Set Maximized : $maximized")
    }

    /**
     * Disable/enable the resizing of your stage. Enabled by default.
     *
     * @param bool false to disable, true to enable.
     */
    private fun setResizable(bool: Boolean) {
        resizable.set(bool)
    }
}