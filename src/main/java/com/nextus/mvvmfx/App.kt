package com.nextus.mvvmfx

import com.nextus.mvvmfx.scope.FxScope
import com.nextus.mvvmfx.scope.ScreenScope
import com.nextus.mvvmfx.ui.custom.BorderlessScene
import com.nextus.mvvmfx.ui.main.MainView
import de.saxsys.mvvmfx.FluentViewLoader
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication
import javafx.stage.Stage
import javafx.stage.StageStyle
import javax.inject.Inject

class App : MvvmfxCdiApplication() {

    override fun startMvvmfx(stage: Stage) {

        val main = FluentViewLoader.fxmlView(MainView::class.java).load()
        main.view.stylesheets.add(javaClass.getResource("/css/theme.css").toExternalForm())

        stage.initStyle(StageStyle.UNDECORATED)

        val borderlessScene = BorderlessScene(stage, stage.style, main.view, main.viewModel.fxScope, main.viewModel.screenScope)
        borderlessScene.setMoveControl(main.codeBehind.topBarViewController.root)

        //set Stage boundaries to visible bounds of the container screen
        /*stage.x = Screen.getPrimary().visualBounds.minX
        stage.y = Screen.getPrimary().visualBounds.minY
        stage.width = Screen.getPrimary().visualBounds.width
        stage.height = Screen.getPrimary().visualBounds.height*/

        with(stage) {
            title = "MvvmFX"
            scene = borderlessScene
            show()
        }
    }

}