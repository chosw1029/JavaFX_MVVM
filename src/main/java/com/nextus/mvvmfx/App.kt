package com.nextus.mvvmfx

import com.nextus.mvvmfx.ui.custom.BorderlessScene
import com.nextus.mvvmfx.ui.main.MainView
import de.saxsys.mvvmfx.FluentViewLoader
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication
import javafx.stage.Stage
import javafx.stage.StageStyle

class App : MvvmfxCdiApplication() {

    override fun startMvvmfx(stage: Stage) {
        val main = FluentViewLoader.fxmlView(MainView::class.java).load()
        main.view.stylesheets.add(javaClass.getResource("/css/theme.css").toExternalForm())

        stage.initStyle(StageStyle.UNDECORATED)

        val borderlessScene = BorderlessScene(stage, stage.style, main.view, main.viewModel.fxScope, main.viewModel.screenScope)
        borderlessScene.setMoveControl(main.codeBehind.topBarViewController.root)

        with(stage) {
            title = "MvvmFX"
            scene = borderlessScene
            show()
        }
    }

}