package com.nextus.mvvmfx.utils

import javafx.scene.input.MouseEvent
import javafx.stage.Stage

object WindowController {

    private var xOffset = 0.0
    private var yOffset = 0.0

    fun setOnMousePressed(primaryStage: Stage, event: MouseEvent) {
        xOffset = primaryStage.x - event.screenX
        yOffset = primaryStage.y - event.screenY
    }

    fun setOnMouseDragged(primaryStage: Stage, event: MouseEvent) {
        primaryStage.x = event.screenX + xOffset
        primaryStage.y = event.screenY + yOffset
    }

}