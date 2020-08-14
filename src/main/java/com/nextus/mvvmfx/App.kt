package com.nextus.mvvmfx

import com.nextus.mvvmfx.ui.custom.BorderlessScene
import com.nextus.mvvmfx.ui.main.MainView
import com.nextus.mvvmfx.utils.ResizeHelper
import de.saxsys.mvvmfx.FluentViewLoader
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle

class App : MvvmfxCdiApplication() {

    override fun startMvvmfx(stage: Stage) {
        val main = FluentViewLoader.fxmlView(MainView::class.java).load()
        main.view.stylesheets.add(javaClass.getResource("/css/theme.css").toExternalForm())
        val borderlessScene = BorderlessScene(stage, main.view)

        //set Stage boundaries to visible bounds of the container screen
        stage.x = Screen.getPrimary().visualBounds.minX
        stage.y = Screen.getPrimary().visualBounds.minY
        stage.width = Screen.getPrimary().visualBounds.width
        stage.height = Screen.getPrimary().visualBounds.height

        with(stage) {
            title = "MvvmFX"
            initStyle(StageStyle.TRANSPARENT)
            scene = borderlessScene
            ResizeHelper.addResizeListener(this)
            show()
        }
    }

}