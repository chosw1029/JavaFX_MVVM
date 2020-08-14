package com.nextus.mvvmfx.ui.alertmgmt.animations;

import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class AbstractAnimation implements Animation {

	protected final Stage stage;

	protected final Timeline showAnimation, dismissAnimation;
	protected final SequentialTransition sq;

	protected volatile boolean trayIsShowing;

	Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	double x;
	double y;


	protected AbstractAnimation(Stage stage) {
		this.stage = stage;

		x = screenBounds.getMinX() + screenBounds.getWidth() - stage.getScene().getWidth() - 2;
		y = screenBounds.getMinY() + screenBounds.getHeight() - stage.getScene().getHeight() - 2;

		showAnimation = setupShowAnimation();
		dismissAnimation = setupDismissAnimation();

		sq = new SequentialTransition(setupShowAnimation(), setupDismissAnimation());
	}

	protected abstract Timeline setupShowAnimation();

	protected abstract Timeline setupDismissAnimation();

	@Override
	public final Stage getStage() {
		return stage;
	}

	@Override
	public final void playSequential(Duration dismissDelay) {
		sq.getChildren().get(1).setDelay(dismissDelay);
		sq.play();
	}

	@Override
	public final void playShowAnimation() {
		showAnimation.play();
	}

	@Override
	public final void playDismissAnimation() {
		dismissAnimation.play();
	}

	@Override
	public final boolean isShowing() {
		return trayIsShowing;
	}


	private SimpleDoubleProperty xLocationProperty = new SimpleDoubleProperty() {
		@Override
		public void set(double newValue) {
			stage.setX(newValue);
		}

		@Override
		public double get() {
			return stage.getX();
		}
	};

	public SimpleDoubleProperty xLocationProperty() {
		return xLocationProperty;
	}

	private SimpleDoubleProperty yLocationProperty = new SimpleDoubleProperty() {
		@Override
		public void set(double newValue) {
			stage.setY(newValue);
		}

		@Override
		public double get() {
			return stage.getY();
		}
	};

	public SimpleDoubleProperty yLocationProperty() {
		return yLocationProperty;
	}

}
