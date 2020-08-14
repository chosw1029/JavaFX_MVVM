package com.nextus.mvvmfx.utils

import com.nextus.mvvmfx.App
import com.nextus.mvvmfx.ui.alertmgmt.notification.TrayNotificationView
import javafx.geometry.Insets
import javafx.geometry.Rectangle2D
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.effect.BlurType
import javafx.scene.effect.DropShadow
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.stage.Modality
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.util.Duration

object ViewUtils {

    fun createStageAndShow(owner: Stage, view: Parent) {
        with(Stage()) {
            initModality(Modality.WINDOW_MODAL)
            initOwner(owner.scene.window)
            scene = Scene(view).apply { fill = Color.TRANSPARENT }
            show()
        }
    }

    fun createStageAndShow(owner: Stage, view: Parent, isNoTitle: Boolean, withCss: Boolean) {
        with(Stage()) {
            initModality(Modality.WINDOW_MODAL)
            initOwner(owner.scene.window)

            if(withCss) {
                view.stylesheets.add(App::class.java.getResource("/css/theme.css").toExternalForm())
                view.stylesheets.add(App::class.java.getResource("/css/label.css").toExternalForm())
            }

            scene = Scene(BorderPane(view).apply {
                padding = Insets(0.0, 8.0, 8.0, 8.0)
                style = "-fx-background-color: transparent"
            })

            scene.fill = Color.TRANSPARENT

            if (isNoTitle) {
                initStyle(StageStyle.TRANSPARENT)
                view.effect = DropShadow().apply {
                    radius = 12.0
                    spread = 0.5
                    offsetX = 0.0
                    offsetY = 4.0
                    color = Color.rgb(0, 0, 0, 0.3)
                    blurType = BlurType.GAUSSIAN
                }
            }

            show()

           /* val bounds: Rectangle2D = Screen.getPrimary().visualBounds
            x = bounds.minX + (bounds.width - scene.width)
            y = bounds.minY + (bounds.height - scene.height)*/
        }
    }

    fun createStageAndShowAndWait(owner: Stage, view: Parent, isNoTitle: Boolean, withCss: Boolean) {
        with(Stage()) {
            initModality(Modality.WINDOW_MODAL)
            initOwner(owner.scene.window)

            if(withCss) {
                view.stylesheets.add(App::class.java.getResource("/css/theme.css").toExternalForm())
                view.stylesheets.add(App::class.java.getResource("/css/label.css").toExternalForm())
            }

            scene = Scene(BorderPane(view).apply {
                padding = Insets(0.0, 8.0, 8.0, 8.0)
                style = "-fx-background-color: transparent"
            })

            scene.fill = Color.TRANSPARENT

            if (isNoTitle) {
                initStyle(StageStyle.TRANSPARENT)
                view.effect = DropShadow().apply {
                    radius = 12.0
                    spread = 0.5
                    offsetX = 0.0
                    offsetY = 4.0
                    color = Color.rgb(0, 0, 0, 0.3)
                    blurType = BlurType.GAUSSIAN
                }
            }
            showAndWait()
        }
    }

     fun createStageAndShowAlert(owner: Stage, view: Parent, notificationView: TrayNotificationView, duration: Double) {
         val stage = Stage().apply {
             //isAlwaysOnTop = true
             //initModality(Modality.WINDOW_MODAL)
             initOwner(owner.scene.window)

             view.stylesheets.add(App::class.java.getResource("/css/theme.css").toExternalForm())
             view.stylesheets.add(App::class.java.getResource("/css/label.css").toExternalForm())

             scene = Scene(BorderPane(view).apply {
                 padding = Insets(0.0, 8.0, 8.0, 8.0)
                 style = "-fx-background-color: transparent"
             })

             scene.fill = Color.TRANSPARENT

             initStyle(StageStyle.TRANSPARENT)
             view.effect = DropShadow().apply {
                 radius = 12.0
                 spread = 0.5
                 offsetX = 0.0
                 offsetY = 4.0
                 color = Color.rgb(0, 0, 0, 0.3)
                 blurType = BlurType.GAUSSIAN
             }

             val bounds: Rectangle2D = Screen.getPrimary().visualBounds

             x = bounds.minX + bounds.width - scene.width - 2 - notificationView.rootNode.prefWidth - 30
             y = bounds.minY + bounds.height - scene.height - 2
         }

         notificationView.showAndDismiss(if(duration > 0) Duration.seconds(duration) else null, stage)
     }
}