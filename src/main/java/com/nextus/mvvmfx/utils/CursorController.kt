package com.nextus.mvvmfx.utils

import javafx.event.EventHandler
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javax.inject.Singleton

object CursorController {

    fun setMouseEnterExit(imageButton: ImageView, entered: String, exited: String) {
        imageButton.onMouseEntered = EventHandler {
            imageButton.cursor = Cursor.HAND
            imageButton.image = Image(javaClass.getResourceAsStream("/images/$entered"))
        }
        imageButton.onMouseExited = EventHandler {
            imageButton.cursor = Cursor.DEFAULT
            imageButton.image = Image(javaClass.getResourceAsStream("/images/$exited"))
        }
    }

    fun setMouseEnterExit(cont: Node, imageButton: ImageView, entered: String, exited: String) {
        cont.onMouseEntered = EventHandler {
            cont.cursor = Cursor.HAND
            imageButton.image = Image(javaClass.getResourceAsStream("/images/$entered"))
        }
        cont.onMouseExited = EventHandler {
            cont.cursor = Cursor.DEFAULT
            imageButton.image = Image(javaClass.getResourceAsStream("/images/$exited"))
        }
    }

    fun setMouseEnterExitOnlyCursor(node: Node) {
        node.onMouseEntered = EventHandler { node.cursor = Cursor.HAND }
        node.onMouseExited = EventHandler { node.cursor = Cursor.DEFAULT }
    }

}