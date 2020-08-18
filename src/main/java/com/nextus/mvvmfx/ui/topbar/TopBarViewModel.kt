package com.nextus.mvvmfx.ui.topbar

import com.nextus.mvvmfx.scope.ScreenScope
import com.nextus.mvvmfx.ui.base.BaseViewModel
import de.saxsys.mvvmfx.InjectScope
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.image.Image

class TopBarViewModel : BaseViewModel() {

    val restoreImage: ObjectProperty<Image> = SimpleObjectProperty(Image(javaClass.getResourceAsStream("/images/maximize.png")))

    @InjectScope lateinit var screenScope: ScreenScope

    override fun initialize() {
        screenScope.maximizeIconProperty.addListener { _, _, newValue ->
            if (newValue)
                restoreImage.set(Image(javaClass.getResourceAsStream("/images/restore.png")))
            else
                restoreImage.set(Image(javaClass.getResourceAsStream("/images/maximize.png")))
        }
    }
}