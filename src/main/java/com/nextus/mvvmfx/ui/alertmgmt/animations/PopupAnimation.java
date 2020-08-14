package com.nextus.mvvmfx.ui.alertmgmt.animations;

import com.nextus.mvvmfx.utils.AlertManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PopupAnimation extends AbstractAnimation {

	Timeline moveLine = new Timeline();

	public PopupAnimation(Stage stage) {
		super(stage);
	}

	@Override
	public void move(int index) {
		moveLine.stop();

		KeyValue kv1 = new KeyValue(yLocationProperty(), AlertManager.INSTANCE.getPositionList().get(index));
		KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);

		moveLine.getKeyFrames().clear();
		moveLine.getKeyFrames().add(kf1);

		moveLine.play();
	}

	@Override
	protected Timeline setupDismissAnimation() {
		Timeline tl = new Timeline();

		KeyValue kv2 = new KeyValue(stage.opacityProperty(), 0.0);
		KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);

		tl.getKeyFrames().addAll(kf2);

		tl.setOnFinished(e -> {
			trayIsShowing = false;
			AlertManager.INSTANCE.getAnimationList().remove(this);
			stage.close();
		});

		return tl;
	}

	@Override
	protected Timeline setupShowAnimation() {
		Timeline tl = new Timeline();

		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		double y = bounds.getMinY() + bounds.getHeight() - 2 - 90 - 10;

		KeyValue kv0 = new KeyValue(yLocationProperty(), y);
		KeyFrame kf0 = new KeyFrame(Duration.ZERO, kv0);

		KeyValue kv1 = new KeyValue(yLocationProperty(), y);
		KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);

		KeyValue kv3 = new KeyValue(stage.opacityProperty(), 0.0);
		KeyFrame kf3 = new KeyFrame(Duration.ZERO, kv3);

		KeyValue kv4 = new KeyValue(stage.opacityProperty(), 1.0);
		KeyFrame kf4 = new KeyFrame(Duration.millis(500), kv4);

		tl.getKeyFrames().addAll(kf1, kf3, kf4);

		tl.setOnFinished(e -> trayIsShowing = true);

		return tl;
	}

}
