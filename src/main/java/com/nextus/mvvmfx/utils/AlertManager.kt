package com.nextus.mvvmfx.utils

import com.nextus.mvvmfx.App
import com.nextus.mvvmfx.scope.FxScope
import com.nextus.mvvmfx.ui.alertmgmt.animations.Animation
import com.nextus.mvvmfx.ui.alertmgmt.notification.Notifications
import com.nextus.mvvmfx.ui.alertmgmt.notification.TrayNotificationView
import de.saxsys.mvvmfx.FluentViewLoader
import javafx.collections.FXCollections
import javafx.geometry.Rectangle2D
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.TextInputDialog
import javafx.scene.image.Image
import javafx.stage.Screen
import javafx.stage.Stage

/**
 * Created by chosw1029 on 2017-04-27.
 *
 * 각 조건 별 다양한 알림창을 보여주는 클래스
 */
object AlertManager {
    /**
     * 버튼 클릭에 따른 이벤트를 처리하고자 할때 사용되는 알림창
     * @param header : ex) 데이터 로드 에러
     * @param text : 상세 안내
     * @return
     */
    fun showAndWaitAlert(header: String?, text: String?): Boolean {
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.title = "MIGAM"
        alert.headerText = header
        alert.contentText = text
        alert.dialogPane.scene.stylesheets.add(App::class.java.getResource("/css/font.css").toExternalForm())
        (alert.dialogPane.scene.window as Stage).icons.addAll(
                Image(App::class.java.getResourceAsStream("/images/Logo_16_D.png")),
                Image(App::class.java.getResourceAsStream("/images/Logo_32_D.png")),
                Image(App::class.java.getResourceAsStream("/images/Logo_48_D.png")),
                Image(App::class.java.getResourceAsStream("/images/Logo_64_D.png"))
        )
        val result = alert.showAndWait()
        return if (result.get() == ButtonType.OK) {
            true
        } else {
            alert.close()
            false
        }
    }

    /**
     * Confirm받기 위해 사용하는 알림창
     * @param header : ex) 데이터 로드 에러
     * @param text : 상세 안내
     */
    fun confirmationAlert(header: String?, text: String?) {
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.title = "MIGAM"
        alert.headerText = header
        alert.contentText = text
        alert.dialogPane.scene.stylesheets.add(App::class.java.getResource("/css/dialog.css").toExternalForm())
        (alert.dialogPane.scene.window as Stage).icons.addAll(
                Image(App::class.java.getResourceAsStream("/images/Logo_16_D.png")),
                Image(App::class.java.getResourceAsStream("/images/Logo_32_D.png")),
                Image(App::class.java.getResourceAsStream("/images/Logo_48_D.png")),
                Image(App::class.java.getResourceAsStream("/images/Logo_64_D.png"))
        )
        alert.show()
    }

    /**
     * 에러를 알려주기 위한 알림창
     * @param header : ex) 데이터 로드 에러
     * @param text :  상세 안내
     */
    fun errorAlert(header: String?, text: String?) {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = "MIGAM"
        alert.headerText = header
        alert.contentText = text
        /*alert.getDialogPane().getScene().getStylesheets().add(App.class.getResource("/css/dialog.css").toExternalForm());
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().addAll(
                new Image(App.class.getResourceAsStream("/images/Logo_16_D.png")),
                new Image(App.class.getResourceAsStream("/images/Logo_32_D.png")),
                new Image(App.class.getResourceAsStream("/images/Logo_48_D.png")),
                new Image(App.class.getResourceAsStream("/images/Logo_64_D.png"))
        );*/alert.show()
    }

    /**
     * 정보를 알려주기 위한 알림창
     * @param header : ex) 차후 버전
     * @param text :  상세 안내
     */
    fun infoAlert(header: String?, text: String?) {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "MIGAM"
        alert.headerText = header
        alert.contentText = text
        /* alert.getDialogPane().getScene().getStylesheets().add(App.class.getResource("/css/dialog.css").toExternalForm());
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().addAll(
                new Image(App.class.getResourceAsStream("/images/Logo_16_D.png")),
                new Image(App.class.getResourceAsStream("/images/Logo_32_D.png")),
                new Image(App.class.getResourceAsStream("/images/Logo_48_D.png")),
                new Image(App.class.getResourceAsStream("/images/Logo_64_D.png"))
        );*/alert.show()
    }

    val animationList = FXCollections.observableArrayList<Animation>()
    val positionList = FXCollections.observableArrayList<Double>()


    val bounds: Rectangle2D = Screen.getPrimary().visualBounds

    fun showNotification(message: String, stage: Stage, notification: Notifications, duration: Double, fxScope: FxScope) {
        if(positionList.isEmpty()) {
            for(i in 0 .. 15) {
                val y = bounds.minY + bounds.height - 2 - 90 * (i + 1) - 10
                positionList.add(y)
            }
        }

        val viewTuple = FluentViewLoader.fxmlView(TrayNotificationView::class.java).providedScopes(fxScope).load()
        viewTuple.viewModel.apply {
            setup(message, notification)
        }

        ViewUtils.createStageAndShowAlert(stage, viewTuple.view, viewTuple.codeBehind, duration)
    }

    /**
     * Text를 입력하고 그에 대한 처리를 원할때 사용하는 알림창
     * @param title
     * @param header
     * @param contentText
     * @param defaultText
     * @return
     */
    fun textInputDialog(title: String?, header: String?, contentText: String?, defaultText: String?): String {
        val dialog = TextInputDialog(defaultText)
        dialog.title = title
        dialog.headerText = header
        dialog.contentText = contentText

        // Traditional way to get the response value.
        val result = dialog.showAndWait()
        return if (result.isPresent) {
            result.get()
        } else ""
    }

    fun ConfirmationChooseButtonAlert() {
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.title = "Confirmation Dialog with Custom Actions"
        alert.headerText = "Look, a Confirmation Dialog with Custom Actions"
        alert.contentText = "Choose your option."
        val buttonTypeOne = ButtonType("OK")
        val buttonTypeTwo = ButtonType("Two")
        val buttonTypeThree = ButtonType("Three")
        val buttonTypeCancel = ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE)
        alert.buttonTypes.setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel)
        val result = alert.showAndWait()
        if (result.get() == buttonTypeOne) {
            // ... user chose "One"
        } else if (result.get() == buttonTypeTwo) {
            // ... user chose "Two"
        } else if (result.get() == buttonTypeThree) {
            // ... user chose "Three"
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
}