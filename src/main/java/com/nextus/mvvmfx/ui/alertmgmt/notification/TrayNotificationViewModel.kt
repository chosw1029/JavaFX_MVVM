package com.nextus.mvvmfx.ui.alertmgmt.notification

import com.nextus.mvvmfx.App
import com.nextus.mvvmfx.ui.base.BaseViewModel
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.image.Image

class TrayNotificationViewModel : BaseViewModel() {

    val msgProperty = SimpleStringProperty("")
    val msgStyleProperty = SimpleStringProperty("")
    val rootPaneStyleProperty = SimpleStringProperty("")
    val iconImageProperty = SimpleObjectProperty<Image>(Image(App::class.java.getResourceAsStream( "/images/icons8_ok_52px.png")))

    override fun initialize() {

    }

    fun setup(msg: String, notificationType: Notifications) {
        msgProperty.set(msg)

        when(notificationType) {
            Notifications.SUCCESS -> {
                iconImageProperty.set(Image(App::class.java.getResourceAsStream( "/images/icons8_ok_52px.png")))
                msgStyleProperty.set("-fx-text-fill: #1BC921;")
                rootPaneStyleProperty.set("-fx-background-color: #E8F9F1; -fx-border-color: #BBEFC4; -fx-border-radius: 12; -fx-background-radius: 12; -fx-border-width: 1;")
            }
            Notifications.WARNING -> {
                iconImageProperty.set(Image(App::class.java.getResourceAsStream( "/images/icons8_warning_shield_48px.png")))
                msgStyleProperty.set("-fx-text-fill: #FFC000;")
                rootPaneStyleProperty.set("-fx-background-color: #FFF8E4; -fx-border-color: #FFE79F; -fx-border-radius: 12; -fx-background-radius: 12; -fx-border-width: 1;")
            }
            Notifications.ERROR -> {
                iconImageProperty.set(Image(App::class.java.getResourceAsStream( "/images/icons8_cancel_96px.png")))
                msgStyleProperty.set("-fx-text-fill: #FF0005;")
                rootPaneStyleProperty.set("-fx-background-color: #FAEEEE; -fx-border-color: #FBC9CB; -fx-border-radius: 12; -fx-background-radius: 12; -fx-border-width: 1;")
            }
            Notifications.INFORMATION -> {
                iconImageProperty.set(Image(App::class.java.getResourceAsStream( "/images/icons8_info_48px.png")))
                msgStyleProperty.set("-fx-text-fill: #008CF5;")
                rootPaneStyleProperty.set("-fx-background-color: #ECF1F8; -fx-border-color: #C7E2F8; -fx-border-radius: 12; -fx-background-radius: 12; -fx-border-width: 1;")
            }
            else -> {

            }
        }
    }
}