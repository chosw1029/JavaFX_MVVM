package com.nextus.mvvmfx.utils

import com.nextus.mvvmfx.scope.ScreenScope
import javafx.scene.input.MouseEvent
import javafx.stage.Screen
import javafx.stage.Stage

object WindowController {

    private var xOffset = 0.0
    private var yOffset = 0.0

    fun setOnMouseClicked(primaryStage: Stage, event: MouseEvent, screenScope: ScreenScope) {
        if (event.clickCount == 2) {
            if (screenScope.isFullScreen()) {
                setRestoreMode(primaryStage, screenScope)
            } else {
                setFullScreenMode(primaryStage, screenScope)
            }
        }
    }

    fun setOnMousePressed(primaryStage: Stage, event: MouseEvent) {
        xOffset = primaryStage.x - event.screenX
        yOffset = primaryStage.y - event.screenY
    }

    fun setOnMouseDragged(primaryStage: Stage, event: MouseEvent) {
        primaryStage.x = event.screenX + xOffset
        primaryStage.y = event.screenY + yOffset
    }

    fun setFullScreenMode(primaryStage: Stage, screenScope: ScreenScope) {
        primaryStage.run {
            val screen = if (Screen.getScreensForRectangle(primaryStage.x, primaryStage.y, primaryStage.width / 2,
                            primaryStage.height / 2).size == 0) {
                (Screen.getScreensForRectangle(primaryStage.x, primaryStage.y,
                        primaryStage.width, primaryStage.height)[0] as Screen).visualBounds
            } else {
                (Screen.getScreensForRectangle(primaryStage.x, primaryStage.y,
                        primaryStage.width / 2, primaryStage.height / 2)[0] as Screen).visualBounds
            }

            x = screen.minX
            y = screen.minY
            width = screen.width
            height = screen.height
        }

        screenScope.fullScreenProperty.set(true)
    }

    fun setMaximizeMode(primaryStage: Stage, screenScope: ScreenScope) {
        primaryStage.run {
            this.isMaximized = true
        }

        screenScope.maximizeProperty.set(true)
    }

    fun setRestoreMode(primaryStage: Stage, screenScope: ScreenScope) {
        primaryStage.run {
            x = Screen.getPrimary().visualBounds.width * 0.1
            y = Screen.getPrimary().visualBounds.height * 0.1
            width = Screen.getPrimary().visualBounds.width * 0.75
            height = Screen.getPrimary().visualBounds.height * 0.8
        }

        screenScope.fullScreenProperty.set(false)
    }
}